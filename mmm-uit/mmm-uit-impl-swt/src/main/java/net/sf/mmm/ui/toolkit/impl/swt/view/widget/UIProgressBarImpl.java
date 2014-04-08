/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.ui.toolkit.impl.swt.view.widget;

import net.sf.mmm.ui.toolkit.api.view.widget.UiProgressBar;
import net.sf.mmm.ui.toolkit.impl.swt.UiFactorySwt;
import net.sf.mmm.ui.toolkit.impl.swt.view.sync.SyncProgressBarAccess;
import net.sf.mmm.util.lang.api.Orientation;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.ProgressBar;

/**
 * This class is the implementation of the
 * {@link net.sf.mmm.ui.toolkit.api.view.widget.UiProgressBar} interface using
 * SWT as the UI toolkit.
 * 
 * @author Joerg Hohwiller (hohwille at users.sourceforge.net)
 */
public class UIProgressBarImpl extends AbstractUiWidgetSwt<ProgressBar> implements UiProgressBar {

  /** the native SWT widget */
  private final SyncProgressBarAccess syncAccess;

  /**
   * The constructor.
   * 
   * @param uiFactory is the {@link #getFactory() factory} instance.
   * @param orientation is the orientation of the progress-bar.
   * @param indeterminate if <code>true</code> the progress-bar will be
   *        {@link #isIndeterminate() indeterminate}.
   */
  public UIProgressBarImpl(UiFactorySwt uiFactory, Orientation orientation, boolean indeterminate) {

    super(uiFactory);
    int style;
    if (orientation == Orientation.HORIZONTAL) {
      style = SWT.HORIZONTAL;
    } else {
      style = SWT.VERTICAL;
    }
    if (indeterminate) {
      style |= SWT.INDETERMINATE;
    }
    this.syncAccess = new SyncProgressBarAccess(uiFactory, this, style);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public SyncProgressBarAccess getAdapter() {

    return this.syncAccess;
  }

  /**
   * {@inheritDoc}
   */
  public int getProgress() {

    return this.syncAccess.getSelection();
  }

  /**
   * {@inheritDoc}
   */
  public void setProgress(int newProgress) {

    this.syncAccess.setSelection(newProgress);
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
  public boolean isIndeterminate() {

    return this.syncAccess.hasStyle(SWT.INDETERMINATE);
  }

  /**
   * {@inheritDoc}
   */
  public Orientation getOrientation() {

    if (this.syncAccess.hasStyle(SWT.HORIZONTAL)) {
      return Orientation.HORIZONTAL;
    } else {
      return Orientation.VERTICAL;
    }
  }

}
