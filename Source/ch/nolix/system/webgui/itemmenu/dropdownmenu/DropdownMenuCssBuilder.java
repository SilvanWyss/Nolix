package ch.nolix.system.webgui.itemmenu.dropdownmenu;

import ch.nolix.coreapi.containerapi.listapi.ILinkedList;
import ch.nolix.coreapi.webapi.cssapi.ICssProperty;
import ch.nolix.system.webgui.itemmenu.base.ItemMenuCssBuilder;
import ch.nolix.systemapi.webguiapi.itemmenuapi.dropdownmenuapi.IDropdownMenu;
import ch.nolix.systemapi.webguiapi.itemmenuapi.dropdownmenuapi.IDropdownMenuStyle;
import ch.nolix.systemapi.webguiapi.mainapi.ControlState;

public final class DropdownMenuCssBuilder extends ItemMenuCssBuilder<IDropdownMenu, IDropdownMenuStyle> {

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
