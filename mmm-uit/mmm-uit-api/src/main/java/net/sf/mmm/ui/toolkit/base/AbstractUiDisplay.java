/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.ui.toolkit.base;

import java.util.concurrent.Callable;

import net.sf.mmm.ui.toolkit.api.UiDevice;
import net.sf.mmm.ui.toolkit.api.UiDisplay;

/**
 * This is the abstract base implementation of the {@link UiDisplay} interface.
 * 
 * @author Joerg Hohwiller (hohwille at users.sourceforge.net)
 * @since 1.0.0
 */
public abstract class AbstractUiDisplay extends AbstractUiObject implements UiDisplay {

  /** the UI device this display belongs to */
  private UiDevice device;

  /**
   * The constructor.
   * 
   * @param uiFactory is the {@link #getFactory() factory} instance.
   * @param uiDevice is the device the display belongs to.
   */
  public AbstractUiDisplay(AbstractUiFactory uiFactory, UiDevice uiDevice) {

    super(uiFactory);
    this.device = uiDevice;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getType() {

    return TYPE;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String toString() {

    return getDevice().toString() + "[" + getWidthInPixel() + "*" + getHeightInPixel() + "]";
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public UiDevice getDevice() {

    return this.device;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public <T> T invokeSynchron(Callable<T> task) throws Exception {

    if (isDispatchThread()) {
      return task.call();
    } else {
      CallableRunner<T> runner = new CallableRunner<T>(task);
      doInvokeSynchron(runner);
      return runner.getResult();
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void invokeSynchron(Runnable task) {

    if (isDispatchThread()) {
      task.run();
    } else {
      doInvokeSynchron(task);
    }
  }

  /**
   * This method handles the {@link #invokeSynchron(Runnable)} if called from another thread.
   * 
   * @param task is the task to invoke.
   */
  protected abstract void doInvokeSynchron(Runnable task);

}
