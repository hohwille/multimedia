/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.ui.toolkit.impl.gwt.view.composite;

import net.sf.mmm.ui.toolkit.api.view.composite.UiSingleComposite;
import net.sf.mmm.ui.toolkit.base.gwt.view.AbstractUiElementGwt;
import net.sf.mmm.ui.toolkit.impl.gwt.UiFactoryGwt;

/**
 * This is the abstract base implementation of a
 * {@link net.sf.mmm.ui.toolkit.api.view.composite.UiComposite} that has exactly
 * one child (that may be <code>null</code>).
 * 
 * @param <E> is the generic type of the {@link #getChild(int) children}.
 * @author Joerg Hohwiller (hohwille at users.sourceforge.net)
 * @since 1.0.0
 */
public abstract class AbstractUiSingleComposite<E extends AbstractUiElementGwt> extends
    AbstractUiComposite<E> implements UiSingleComposite<E> {

  /** @see #getChild() */
  private E child;

  /**
   * The constructor.
   * 
   * @param uiFactory is the {@link #getFactory() factory} instance.
   */
  public AbstractUiSingleComposite(UiFactoryGwt uiFactory) {

    super(uiFactory);
  }

  /**
   * {@inheritDoc}
   */
  public int getChildCount() {

    if (this.child == null) {
      return 0;
    } else {
      return 1;
    }
  }

  /**
   * {@inheritDoc}
   */
  public E getChild() {

    return this.child;
  }

  /**
   * {@inheritDoc}
   */
  public E getChild(int index) {

    if (index == 0) {
      if (this.child != null) {
        return this.child;
      }
    }
    throw new IndexOutOfBoundsException(Integer.toString(index));
  }

  /**
   * {@inheritDoc}
   */
  public void setChild(E child) {

    if (this.child != null) {
      this.child.setParent(null);
    }
    this.child = child;
  }

}
