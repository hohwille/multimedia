/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.ui.toolkit.api;

import net.sf.mmm.client.ui.api.attribute.AttributeReadHtmlId;

/**
 * This is the abstract interface for a UI object. A UI object is any object of the user interface within this
 * UI toolkit. <br>
 * An instance of this interface may be casted to a sub-interface of this API (net.sf.mmm.ui.api.*) according
 * to the result of the {@link UiObject#getType()} method.
 * 
 * @author Joerg Hohwiller (hohwille at users.sourceforge.net)
 * @since 1.0.0
 */
public abstract interface UiObject extends AttributeReadHtmlId {

  /**
   * This method gets the UI factory that created this component.
   * 
   * @return the ui instance.
   */
  UiFactory getFactory();

  /**
   * This method returns the type of the component. This must be a TYPE constant defined in a sub-interface.
   * It is legal to use the == operator to compare the result with the TYPE constant of a sub-interface.
   * Further it is legal to cast this instance to a sub-interface if the result of this method is the same as
   * the TYPE constant of that sub-interface. <br>
   * An example usage of this method may be:
   * 
   * <pre>
   * UIObject uiObject = ...;
   * if (uiObject.getType() == UIButton.TYPE) {
   *   UIButton button = (UIButton) uiObject;
   *   ...
   * }
   * </pre>
   * 
   * @return the type of this component.
   */
  String getType();

}
