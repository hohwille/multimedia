/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.ui.toolkit.base.gwt.view.window;

import net.sf.mmm.ui.toolkit.api.view.window.UiFrame;
import net.sf.mmm.ui.toolkit.api.view.window.UiWorkbench;
import net.sf.mmm.ui.toolkit.base.gwt.AbstractUiFactoryGwt;
import net.sf.mmm.ui.toolkit.base.gwt.JavaScriptUtil;
import net.sf.mmm.ui.toolkit.base.view.menu.AbstractUiMenuBar;
import net.sf.mmm.ui.toolkit.base.view.window.AbstractUiWindow;

import com.google.gwt.user.client.Window;

/**
 * This is the implementation of the {@link UiWorkbench} interface using GWT.
 * 
 * @author Joerg Hohwiller (hohwille at users.sourceforge.net)
 * @since 1.0.0
 */
public abstract class AbstractUiWorkbenchGwt extends AbstractUiWindow implements UiWorkbench {

  /** @see #isMaximized() */
  private boolean maximized;

  /** @see #isMinimized() */
  private boolean minimized;

  /** @see #restoreWindow() */
  private int restoreX;

  /** @see #restoreWindow() */
  private int restoreY;

  /** @see #restoreWindow() */
  private int restoreWidth;

  /** @see #restoreWindow() */
  private int restoreHeight;

  /** @see #getMenuBar() */
  private AbstractUiMenuBar menuBar;

  /**
   * The constructor.
   * 
   * @param uiFactory is the {@link #getFactory() factory} instance.
   */
  public AbstractUiWorkbenchGwt(AbstractUiFactoryGwt uiFactory) {

    super(uiFactory);
  }

  /**
   * {@inheritDoc}
   */
  public String getType() {

    return TYPE;
  }

  /**
   * {@inheritDoc}
   */
  public void setSize(int width, int height) {

    int windowWith = (JavaScriptUtil.getWidthOfBrowserWindow() - JavaScriptUtil
        .getWidthOfBrowserViewport()) + width;
    int windowHeight = (JavaScriptUtil.getHeightOfBrowserWindow() - JavaScriptUtil
        .getHeightOfBrowserViewport()) + height;
    JavaScriptUtil.setSizeOfBrowserWindow(windowWith, windowHeight);
  }

  /**
   * {@inheritDoc}
   */
  public boolean isResizable() {

    return true;
  }

  /**
   * {@inheritDoc}
   */
  public int getWidth() {

    return JavaScriptUtil.getWidthOfBrowserViewport();
  }

  /**
   * {@inheritDoc}
   */
  public int getHeight() {

    return JavaScriptUtil.getHeightOfBrowserViewport();
  }

  /**
   * {@inheritDoc}
   */
  public void setTitle(String title) {

    Window.setTitle(title);
  }

  /**
   * {@inheritDoc}
   */
  public String getTitle() {

    return Window.getTitle();
  }

  /**
   * {@inheritDoc}
   */
  public void setPosition(int x, int y) {

    Window.moveTo(x, y);
  }

  /**
   * {@inheritDoc}
   */
  public int getX() {

    return JavaScriptUtil.getXOfBrowserWindow();
  }

  /**
   * {@inheritDoc}
   */
  public int getY() {

    return JavaScriptUtil.getYOfBrowserWindow();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void doSetVisible(boolean visible) {

    // TODO
    super.doSetVisible(visible);
  }

  /**
   * This method stores the current window position and size.
   * 
   * @see #restoreWindow()
   */
  private void storeWindow() {

    this.restoreX = getX();
    this.restoreY = getY();
    this.restoreWidth = getWidth();
    this.restoreHeight = getHeight();
  }

  /**
   * This method restores the window position and size.
   * 
   * @see #storeWindow()
   */
  private void restoreWindow() {

    setPosition(this.restoreX, this.restoreY);
    setSize(this.restoreWidth, this.restoreHeight);
  }

  /**
   * {@inheritDoc}
   */
  public void setMaximized(boolean maximize) {

    // The real maximize/un-maximize function is not available for the browser
    // window. We emulate this by (re)storing position and size.
    if (this.maximized != maximize) {
      if (maximize) {
        if (!this.minimized) {
          storeWindow();
        } else {
          this.minimized = false;
          setVisible(true);
        }
        setPosition(0, 0);
        setSize(JavaScriptUtil.getWidthOfScreen(), JavaScriptUtil.getHeightOfScreen());
      } else {
        restoreWindow();
      }
      this.maximized = maximize;
    }
  }

  /**
   * {@inheritDoc}
   */
  public boolean isMaximized() {

    return this.maximized;
  }

  /**
   * {@inheritDoc}
   */
  public void setMinimized(boolean minimize) {

    // The real minimize/un-minimize function is not available for the browser
    // window. We emulate this by (re)storing position and size.
    if (this.minimized != minimize) {
      if (minimize) {
        if (!this.maximized) {
          storeWindow();
        } else {
          this.maximized = false;
        }
        // stupid hack, but there seems no other way...
        JavaScriptUtil.setSizeOfBrowserWindow(0, 0);
        setPosition(0, 3000);
        setVisible(false);
      } else {
        restoreWindow();
      }
      this.minimized = minimize;
    }
  }

  /**
   * {@inheritDoc}
   */
  public boolean isMinimized() {

    return this.minimized;
  }

  /**
   * {@inheritDoc}
   */
  public AbstractUiMenuBar getMenuBar() {

    if (this.menuBar == null) {
      this.menuBar = createMenuBar();
    }
    return this.menuBar;
  }

  /**
   * This method creates the
   * {@link net.sf.mmm.ui.toolkit.api.view.menu.UiMenuBar}. It is invoked from
   * {@link #getMenuBar()} on the first call.
   * 
   * @return the new {@link net.sf.mmm.ui.toolkit.api.view.menu.UiMenuBar}.
   */
  protected abstract AbstractUiMenuBar createMenuBar();

  /**
   * {@inheritDoc}
   */
  @Override
  public UiFrame getParent() {

    return null;
  }

  /**
   * {@inheritDoc}
   */
  public UiFrame createFrame(String title, boolean resizable) {

    return getFactory().createFrame(this, title, resizable);
  }

}
