/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.ui.toolkit.impl.swt.view.composite;

import net.sf.mmm.ui.toolkit.api.view.composite.UiSplitPanel;
import net.sf.mmm.ui.toolkit.base.view.AbstractUiElement;
import net.sf.mmm.ui.toolkit.base.view.composite.AbstractUiComposite;
import net.sf.mmm.ui.toolkit.impl.swt.UiFactorySwt;
import net.sf.mmm.ui.toolkit.impl.swt.view.sync.AbstractSyncControlAccess;
import net.sf.mmm.ui.toolkit.impl.swt.view.sync.SyncCompositeAccess;
import net.sf.mmm.ui.toolkit.impl.swt.view.sync.SyncSashFormAccess;
import net.sf.mmm.util.lang.api.Orientation;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Control;

/**
 * This class is the implementation of the
 * {@link net.sf.mmm.ui.toolkit.api.view.composite.UiSplitPanel} interface using
 * SWT as the UI toolkit.
 * 
 * @author Joerg Hohwiller (hohwille at users.sourceforge.net)
 * @param <CHILD> is the generic type of the {@link #getChild(int) children}.
 * @since 1.0.0
 */
public class UiSplitPanelImpl<CHILD extends AbstractUiElement<? extends Control>> extends
    AbstractUiComposite<SashForm, CHILD> implements UiSplitPanel<CHILD> {

  /** @see #getAdapter() */
  private final SyncSashFormAccess syncAccess;

  /** the synchronous access to the top or left composite */
  private final SyncCompositeAccess syncTopLeft;

  /** the synchronous access to the bottom or right composite */
  private final SyncCompositeAccess syncBottomRight;

  /** the component top or left */
  private CHILD componentTopOrLeft;

  /** the component bottom or right */
  private CHILD componentBottomOrRight;

  /**
   * The constructor.
   * 
   * @param uiFactory is the {@link #getFactory() factory} instance.
   * @param orientation is the {@link #getOrientation() orientation} of the two
   *        child-components in this split-pane.
   */
  public UiSplitPanelImpl(UiFactorySwt uiFactory, Orientation orientation) {

    super(uiFactory);
    int style;
    if (orientation == Orientation.HORIZONTAL) {
      style = SWT.HORIZONTAL;
    } else {
      style = SWT.VERTICAL;
    }
    style |= SWT.BORDER;
    // style |= SWT.LEFT_TO_RIGHT;
    this.syncAccess = new SyncSashFormAccess(uiFactory, this, style);
    this.syncTopLeft = new SyncCompositeAccess(uiFactory, this, SWT.NONE);
    this.syncTopLeft.setLayout(new FillLayout());
    this.syncTopLeft.setParentAccess(this.syncAccess);
    this.syncBottomRight = new SyncCompositeAccess(uiFactory, this, SWT.NONE);
    this.syncBottomRight.setLayout(new FillLayout());
    this.syncBottomRight.setParentAccess(this.syncAccess);
    /*
     * final int sashStyle = style; getFactory().invokeSynchron(new Runnable() {
     * 
     * public void run() {
     * 
     * UISplitPanelImpl.this.splitPanel = new SashForm(parent, sashStyle);
     * UISplitPanelImpl.this.compositeTopLeft = new
     * Composite(UISplitPanelImpl.this.splitPanel, SWT.NONE);
     * UISplitPanelImpl.this.compositeTopLeft.setLayout(new FillLayout());
     * UISplitPanelImpl.this.compositeBottomRight = new Composite(
     * UISplitPanelImpl.this.splitPanel, SWT.NONE);
     * UISplitPanelImpl.this.compositeBottomRight.setLayout(new FillLayout()); }
     * });
     */
    this.componentTopOrLeft = null;
    this.componentBottomOrRight = null;
  }

  /**
   * {@inheritDoc}
   */
  protected void createChildren() {

    this.syncTopLeft.create();
    this.syncBottomRight.create();
    // super.createChildren();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public SyncSashFormAccess getAdapter() {

    return this.syncAccess;
  }

  /**
   * {@inheritDoc}
   */
  public void setOrientation(Orientation orientation) {

    if (orientation == Orientation.HORIZONTAL) {
      this.syncAccess.setOrientation(SWT.HORIZONTAL);
    } else {
      this.syncAccess.setOrientation(SWT.VERTICAL);
    }
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

  /**
   * {@inheritDoc}
   */
  public void setTopOrLeftComponent(CHILD component) {

    if (this.componentTopOrLeft != null) {
      // TODO
    }
    this.componentTopOrLeft = component;
    ((AbstractSyncControlAccess<? extends Control>) this.componentTopOrLeft.getAdapter())
        .setParentAccess(this.syncTopLeft);
    this.componentTopOrLeft.setParent(this);

  }

  /**
   * {@inheritDoc}
   */
  public void setBottomOrRightComponent(CHILD component) {

    if (this.componentBottomOrRight != null) {
      // TODO
    }
    this.componentBottomOrRight = component;
    ((AbstractSyncControlAccess<? extends Control>) this.componentBottomOrRight.getAdapter())
        .setParentAccess(this.syncBottomRight);
    this.componentBottomOrRight.setParent(this);
  }

  /**
   * {@inheritDoc}
   */
  public void setDividerPosition(double proportion) {

    final int percent = (int) (proportion * 100);
    this.syncAccess.setWeights(percent);
    /*
     * if ((this.componentBottomOrRight != null) && (this.componentTopOrLeft !=
     * null)) { } getFactory().invokeSynchron(new Runnable() {
     * 
     * public void run() { UISplitPanelImpl.this.splitPanel.setWeights(new int[]
     * {percent, 100 - percent}); } });
     */
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
  public CHILD getTopOrLeftComponent() {

    return this.componentTopOrLeft;
  }

  /**
   * {@inheritDoc}
   */
  public CHILD getBottomOrRightComponent() {

    return this.componentBottomOrRight;
  }

  /**
   * {@inheritDoc}
   */
  public CHILD getChild(int index) {

    if (index == 0) {
      return getTopOrLeftComponent();
    } else if (index == 1) {
      return getBottomOrRightComponent();
    } else {
      throw new IndexOutOfBoundsException("Illegal index (" + index + ") must be 0 or 1!");
    }
  }

  /**
   * {@inheritDoc}
   */
  public int getChildCount() {

    return 2;
  }

  /**
   * {@inheritDoc}
   */
  public boolean isAttachToActiveAccess() {

    return false;
  }

}
