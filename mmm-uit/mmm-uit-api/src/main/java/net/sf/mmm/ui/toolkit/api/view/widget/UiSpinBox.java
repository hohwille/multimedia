/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.ui.toolkit.api.view.widget;

import net.sf.mmm.client.ui.api.attribute.AttributeWriteEditable;

/**
 * This is the interface for a spin-box. It is an iteractive widget used to specify an value out of an ordered
 * list. The widget is visualized like a {@link net.sf.mmm.ui.toolkit.api.view.widget.UiTextField text-field}
 * for the integer value but with additional buttons to increment and decrement the current value within the
 * allowed range. The decrement button should have an arrow-down icon or the text "-". The increment button
 * should have an arrow-up icon or the text "+". The buttons should also be associated with the arrow-up/down
 * keys on the keyboard.
 * 
 * @param <E> is the templated type of the elements that can be selected with this widget.
 * 
 * @author Joerg Hohwiller (hohwille at users.sourceforge.net)
 * @since 1.0.0
 */
public interface UiSpinBox<E> extends UiListWidget<E>, AttributeWriteEditable {

  /** the type of this object */
  String TYPE = "SpinBox";

}
