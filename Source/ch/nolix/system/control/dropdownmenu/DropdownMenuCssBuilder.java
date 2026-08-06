/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.control.dropdownmenu;

import ch.nolix.baseapi.datastructure.list.ILinkedList;
import ch.nolix.baseapi.web.cssmodel.ICssProperty;
import ch.nolix.system.control.itemmenu.AbstractItemMenuCssBuilder;
import ch.nolix.systemapi.control.dropdownmenu.IDropdownMenu;
import ch.nolix.systemapi.control.dropdownmenu.IDropdownMenuStyle;
import ch.nolix.systemapi.webgui.webguiproperty.ControlState;

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
    // Does nothing.
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void fillUpCssPropertiesForControlAndStateIntoList(
    final IDropdownMenu dropdownMenu,
    final ControlState state,
    final ILinkedList<ICssProperty> list) {
    // Does nothing.
  }
}
