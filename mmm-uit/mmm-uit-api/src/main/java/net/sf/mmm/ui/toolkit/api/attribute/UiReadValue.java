/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.ui.toolkit.api.attribute;

/**
 * This interface gives read access to the {@link #getValue() value} of an
 * {@link net.sf.mmm.ui.toolkit.api.UiObject object}.
 * 
 * @param <V> is the generic type of the {@link #getValue() value}.
 * @author Joerg Hohwiller (hohwille at users.sourceforge.net)
 * @since 1.0.0
 */
public interface UiReadValue<V> {

  /**
   * This method gets the value of this object. This can be the text of a label or button, as well as the date
   * of a date-picker.
   * 
   * @return the value of this object.
   */
  V getValue();

}
