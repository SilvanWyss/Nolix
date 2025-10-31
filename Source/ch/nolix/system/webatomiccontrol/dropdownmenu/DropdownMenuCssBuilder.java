package ch.nolix.system.webatomiccontrol.dropdownmenu;

import ch.nolix.coreapi.container.list.ILinkedList;
import ch.nolix.coreapi.web.cssmodel.ICssProperty;
import ch.nolix.system.webgui.itemmenu.base.AbstractItemMenuCssBuilder;
import ch.nolix.systemapi.webatomiccontrol.dropdownmenu.IDropdownMenu;
import ch.nolix.systemapi.webatomiccontrol.dropdownmenu.IDropdownMenuStyle;
import ch.nolix.systemapi.webgui.main.ControlState;

public final class DropdownMenuCssBuilder extends AbstractItemMenuCssBuilder<IDropdownMenu, IDropdownMenuStyle> {
  @Override
  protected void fillUpCssPropertiesForControlAndAllStatesIntoList(
    final IDropdownMenu control,
    final ILinkedList<ICssProperty> list) {
    //Does nothing.
  }

  @Override
  protected void fillUpCssPropertiesForControlAndStateIntoList(
    final IDropdownMenu dropdownMenu,
    final ControlState state,
    final ILinkedList<ICssProperty> list) {
    //Does nothing.
  }
}
