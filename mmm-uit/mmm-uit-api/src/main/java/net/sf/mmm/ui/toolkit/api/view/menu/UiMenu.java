/* $Id: UIMenu.java 304 2007-06-13 23:58:29Z hohwille $
 * Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.ui.toolkit.api.view.menu;

import java.util.Iterator;

import net.sf.mmm.ui.toolkit.api.common.ButtonStyle;
import net.sf.mmm.ui.toolkit.api.event.UiEventListener;
import net.sf.mmm.ui.toolkit.api.feature.UiAction;

/**
 * This is the interface for a menu.<br>
 * Use {@link net.sf.mmm.ui.toolkit.api.view.menu.UiMenuBar#addMenu(String)} or {@link #addSubMenu(String)} to
 * create menus.
 * 
 * @author Joerg Hohwiller (hohwille at users.sourceforge.net)
 */
public interface UiMenu extends UiMenuItem {

  /** the type of this object */
  String TYPE = "Menu";

  /**
   * This method gets the number of items in this menu.
   * 
   * @return the item count.
   */
  int getItemCount();

  /**
   * This method gets the items of this menu.
   * 
   * @return an iterator of the items in this menu.
   */
  Iterator<? extends UiMenuItem> getItems();

  /**
   * This method adds a separator to this menu.
   */
  void addSeparator();

  /**
   * This method creates and adds a new {@link ButtonStyle#DEFAULT regular} item to this menu.
   * 
   * @see #addItem(String, UiEventListener, ButtonStyle)
   * 
   * @param name is the name of the item to add.
   * @param action is the action invoked by the item (may be <code>null</code> for a separator).
   * @return the created menu item.
   */
  UiMenuItem addItem(String name, UiEventListener action);

  /**
   * This method creates and adds a new item to this menu.
   * 
   * @see #addItem(String, UiEventListener, ButtonStyle)
   * 
   * @param name is the name of the item to add.
   * @param style is the style defining how the item is visualized and behaves.
   * @return the created menu item.
   */
  UiMenuItem addItem(String name, ButtonStyle style);

  /**
   * This method creates and adds a new item to this menu.
   * 
   * @param name is the name of the item to add.
   * @param action is the action invoked by the item (may be <code>null</code> for a separator).
   * @param style is the style defining how the item is visualized and behaves.
   * @return the created menu item.
   */
  UiMenuItem addItem(String name, UiEventListener action, ButtonStyle style);

  /**
   * This method creates and adds a new item to this menu.
   * 
   * @param uiAction is the action to be represented as button.
   * @return the created menu item.
   */
  UiMenuItem addItem(UiAction uiAction);

  /**
   * This method creates and adds a new submenu to this menu.
   * 
   * @param name is the name of the submenu to add.
   * @return the created submenu.
   */
  UiMenu addSubMenu(String name);

}
