/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.upnp.ssdp.impl;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.concurrent.Executor;

import javax.inject.Inject;

import net.sf.mmm.upnp.ssdp.api.SsdpRequest;
import net.sf.mmm.upnp.ssdp.base.AbstractSsdpReceiver;
import net.sf.mmm.util.concurrent.base.SimpleExecutor;
import net.sf.mmm.util.http.HttpParser;
import net.sf.mmm.util.io.api.IoMode;
import net.sf.mmm.util.io.api.RuntimeIoException;

/**
 * This is the basic implementation of the {@link net.sf.mmm.upnp.ssdp.api.SsdpReceiver} interface.
 * 
 * @author Joerg Hohwiller (hohwille at users.sourceforge.net)
 */
public abstract class BasicSsdpReceiver extends AbstractSsdpReceiver {

  /** @see #setThreadPool(Executor) */
  private Executor threadPool;

  /** @see #connect() */
  private MulticastListener listener;

  /**
   * The constructor.
   */
  public BasicSsdpReceiver() {

    super();
    this.threadPool = null;
    this.listener = null;
  }

  /**
   * This method connects the multicast-socket.
   * 
   * @throws RuntimeIoException if the operation failed.
   */
  protected void connect() throws RuntimeIoException {

    try {
      if (this.listener != null) {
        throw new IllegalStateException("Connect called twice without disconnect!");
      }
      if (this.threadPool == null) {
        this.threadPool = new SimpleExecutor();
      }
      this.listener = new MulticastListener();
      this.threadPool.execute(this.listener);
    } catch (IOException e) {
      throw new RuntimeIoException(e, IoMode.READ);
    }
  }

  /**
   * This method determines if this receiver is currently {@link #connect() connected}.
   * 
   * @return <code>true</code> if connected, <code>false</code> otherwise.
   */
  protected boolean isConnected() {

    return (this.listener != null);
  }

  /**
   * This method disconnects the multicast-socket.
   * 
   * @throws IOException if the operation failed.
   */
  protected void disconnect() throws IOException {

    if (this.listener != null) {
      this.listener.disconnect();
      this.listener = null;
    }
  }

  /**
   * This method sets the thread-pool used to create the listener thread.
   * 
   * @param executor the threadPool to set.
   */
  @Inject
  public void setThreadPool(Executor executor) {

    this.threadPool = executor;
  }

  /**
   * This inner class is the actual listener implementation.
   */
  private class MulticastListener implements Runnable {

    /** the multicast socket to listen to */
    private MulticastSocket socket;

    /** @see #run() */
    private transient boolean listening;

    /** @see #run() */
    private transient boolean stopped;

    /** the internet address to listen to */
    private InetAddress address;

    /**
     * The constructor.
     * 
     * @throws IOException if the operation failed with an I/O problem.
     */
    public MulticastListener() throws IOException {

      this(InetAddress.getByName(SsdpRequest.MULTICAST_ADDRESS));
    }

    /**
     * The constructor.
     * 
     * @param mutlicastAddress the multicast address to listen to.
     * @throws IOException if the operation failed with an I/O problem.
     */
    public MulticastListener(InetAddress mutlicastAddress) throws IOException {

      super();
      this.listening = true;
      this.stopped = false;
      this.address = mutlicastAddress;
      this.socket = new MulticastSocket(SsdpRequest.MULTICAST_PORT);
      this.socket.joinGroup(this.address);
    }

    /**
     * Stops listening by resetting the <code>listening</code> flag.
     * 
     * @return <code>true</code> if stopped, <code>false</code> if it has already been stopped.
     */
    private synchronized boolean stopListening() {

      if (this.listening) {
        this.listening = false;
        return true;
      }
      return false;
    }

    /**
     * This method disconnects the multicast-socket.
     * 
     * @throws IOException if the operation failed.
     */
    protected void disconnect() throws IOException {

      boolean stoppedListening = stopListening();
      if (!stoppedListening) {
        return;
      }
      try {
        this.socket.leaveGroup(this.address);
        // receive blocks with synchronization lock so disconnect stalls for
        // ever if nothing is received...
        // this.socket.disconnect();
        for (int retry = 0; retry < 3; retry++) {
          if (this.stopped) {
            getLogger().info("SSDP-Multicast-Listener has been stopped.");
            break;
          } else {
            try {
              Thread.sleep(100);
            } catch (InterruptedException e) {
              // ignore?
            }
          }
        }
      } finally {
        this.socket.close();
        this.socket = null;
      }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void run() {

      byte[] buffer = new byte[1024];
      DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
      while (this.listening) {
        try {
          // TODO: this API sucks! The method blocks until datagram was received,
          // if nothing is received and the socket is closed, an IO-Exception is
          // thrown.
          this.socket.receive(packet);
          byte[] data = packet.getData();
          ByteArrayInputStream stream = new ByteArrayInputStream(data);
          SsdpRequest request = new SsdpRequest();
          HttpParser.parseRequest(stream, request);
          notifyListeners(request);
        } catch (IOException e) {
          throw new RuntimeIoException(e, IoMode.READ);
        }
      }
      this.stopped = true;
    }
  }

}
