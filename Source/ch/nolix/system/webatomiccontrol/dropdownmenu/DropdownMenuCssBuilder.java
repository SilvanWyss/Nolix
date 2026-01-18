/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.webatomiccontrol.dropdownmenu;

import ch.nolix.coreapi.container.list.ILinkedList;
import ch.nolix.coreapi.web.cssmodel.ICssProperty;
import ch.nolix.system.webatomiccontrol.itemmenu.AbstractItemMenuCssBuilder;
import ch.nolix.systemapi.webatomiccontrol.dropdownmenu.IDropdownMenu;
import ch.nolix.systemapi.webatomiccontrol.dropdownmenu.IDropdownMenuStyle;
import ch.nolix.systemapi.webgui.main.ControlState;

/**
 * @author Silvan Wyss
 */
public final class DropdownMenuCssBuilder extends AbstractItemMenuCssBuilder<IDropdownMenu, IDropdownMenuStyle> {
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
