/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.upnp.ssdp.impl;

import org.junit.Ignore;
import org.junit.Test;

import net.sf.mmm.upnp.ssdp.api.SsdpListener;
import net.sf.mmm.upnp.ssdp.api.SsdpRequest;
import net.sf.mmm.upnp.ssdp.api.SsdpSender;

import static org.junit.Assert.*;

/**
 * This is a {@link TestCase test-case} for {@link SimpleSsdpReceiver}.
 * 
 * @author Joerg Hohwiller (hohwille at users.sourceforge.net)
 */
@SuppressWarnings("all")
public class SimpleSsdpReceiverTest {

  public void testDummy() {

  // this test-case is currently disabled...
  }

  @Ignore
  @Test
  public void disableTestReceive() throws Exception {

    SimpleSsdpReceiver receiverImpl = new SimpleSsdpReceiver();
    receiverImpl.initialize();
    Listener listener = new Listener();
    receiverImpl.addListener(listener);
    SsdpSenderImpl senderImpl = new SsdpSenderImpl();
    senderImpl.initialize();
    SsdpSender sender = senderImpl;
    SsdpRequest request = SsdpSenderImplTest.createRequest();
    // send the message
    sender.send(request);
    senderImpl.dispose();
    Thread.sleep(100);
    // check received
    SsdpSenderImplTest.checkRequest(listener.message);
    receiverImpl.dispose();
  }

  private static class Listener implements SsdpListener {

    private SsdpRequest message;

    /**
     * {@inheritDoc}
     */
    public void receive(SsdpRequest request) {

      if (this.message != null) {
        fail("TODO");
      }
      this.message = request;
    }

  }

}
