/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.ui.toolkit.impl.swt.view.widget;

import net.sf.mmm.ui.toolkit.api.model.data.UiListMvcModel;
import net.sf.mmm.ui.toolkit.api.view.widget.UiSpinBox;
import net.sf.mmm.ui.toolkit.impl.swt.UiFactorySwt;
import net.sf.mmm.ui.toolkit.impl.swt.custom.MySpinner;
import net.sf.mmm.ui.toolkit.impl.swt.view.sync.SyncMySpinnerAccess;

import org.eclipse.swt.SWT;

/**
 * This class is the implementation of the
 * {@link net.sf.mmm.ui.toolkit.api.view.widget.UiSpinBox} interface using SWT
 * as the UI toolkit.
 * 
 * @param <E> is the templated type of the list-elements.
 * 
 * @author Joerg Hohwiller (hohwille at users.sourceforge.net)
 */
public class UISpinBoxImpl<E> extends AbstractUiWidgetSwt<MySpinner> implements UiSpinBox<E> {

  /** the native SWT widget */
  private final SyncMySpinnerAccess syncAccess;

  /** the model */
  private UiListMvcModel<E> model;

  /**
   * The constructor.
   * 
   * @param uiFactory is the {@link #getFactory() factory} instance.
   * @param listModel is the model for the elements to select.
   */
  public UISpinBoxImpl(UiFactorySwt uiFactory, UiListMvcModel<E> listModel) {

    super(uiFactory);
    this.syncAccess = new SyncMySpinnerAccess(uiFactory, this, SWT.NONE, listModel);
    this.model = listModel;
    // this.spinBox.setMinimum(this.model.getMinWidthimumValue());
    // this.spinBox.setMaximum(this.model.getMaximumValue());
    // this.spinBox.setSelection(this.model.getMinimumValue());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public SyncMySpinnerAccess getAdapter() {

    return this.syncAccess;
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
  public UiListMvcModel<E> getModel() {

    return this.model;
  }

  /**
   * {@inheritDoc}
   */
  public void setSelectedIndex(int newIndex) {

    this.syncAccess.setSelectedIndex(newIndex);
  }

  /**
   * {@inheritDoc}
   */
  public int getSelectedIndex() {

    return this.syncAccess.getSelectedIndex();
  }

  /**
   * {@inheritDoc}
   */
  public void setSelectedValue(E newValue) {

    int index = this.model.getIndexOf(newValue);
    if (index > -1) {
      setSelectedIndex(index);
    }
  }

  /**
   * {@inheritDoc}
   */
  public E getSelectedValue() {

    int i = getSelectedIndex();
    if (i >= 0) {
      return this.model.getElement(i);
    }
    return null;
  }

  /**
   * {@inheritDoc}
   */
  public void setModel(UiListMvcModel<E> newModel) {

    this.syncAccess.setModel(newModel);
    this.model = newModel;
  }

  /**
   * {@inheritDoc}
   */
  public boolean isEditable() {

    return this.syncAccess.isEditable();
  }

  /**
   * {@inheritDoc}
   */
  public void setEditable(boolean editableFlag) {

    this.syncAccess.setEditable(editableFlag);
  }

}
