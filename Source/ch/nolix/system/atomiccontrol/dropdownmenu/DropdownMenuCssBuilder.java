/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.atomiccontrol.dropdownmenu;

import ch.nolix.baseapi.css.cssmodel.ICssProperty;
import ch.nolix.baseapi.datastructure.list.ILinkedList;
import ch.nolix.system.atomiccontrol.itemmenu.AbstractItemMenuCssBuilder;
import ch.nolix.systemapi.atomiccontrol.dropdownmenu.IDropdownMenu;
import ch.nolix.systemapi.atomiccontrol.dropdownmenu.IDropdownMenuStyle;
import ch.nolix.systemapi.webgui.main.ControlState;

/**
 * @author Silvan Wyss
 */
public final class DropdownMenuCssBuilder extends AbstractItemMenuCssBuilder<IDropdownMenu, IDropdownMenuStyle> {
  /**
   * {@inheritDoc}
   */
  @Override
  protected void fillUpCssPropertiesForControlAndAllStatesIntoList(
    final IDropdownMenu control,
    final ILinkedList<ICssProperty> list) {
    //Does nothing.
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void fillUpCssPropertiesForControlAndStateIntoList(
    final IDropdownMenu dropdownMenu,
    final ControlState state,
    final ILinkedList<ICssProperty> list) {
    //Does nothing.
  }
}
