//package declaration
package ch.nolix.system.webgui.itemmenu;

import ch.nolix.coreapi.containerapi.listapi.ILinkedList;
import ch.nolix.coreapi.webapi.cssapi.ICssProperty;
import ch.nolix.systemapi.webguiapi.itemmenuapi.IDropdownMenu;
import ch.nolix.systemapi.webguiapi.itemmenuapi.IDropdownMenuStyle;
import ch.nolix.systemapi.webguiapi.mainapi.ControlState;

//class
public final class DropdownMenuCssBuilder extends ItemMenuCssBuilder<IDropdownMenu, IDropdownMenuStyle> {

  //method
  @Override
  protected void fillUpCssPropertiesForControlAndAllStatesIntoList(
    final IDropdownMenu control,
    final ILinkedList<ICssProperty> list) {
    //Does nothing.
  }

  //method
  @Override
  protected void fillUpCssPropertiesForControlAndStateIntoList(
    final IDropdownMenu dropdownMenu,
    final ControlState state,
    final ILinkedList<ICssProperty> list) {
    //Does nothing.
  }
}
