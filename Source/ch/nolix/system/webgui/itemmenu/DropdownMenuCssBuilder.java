//package declaration
package ch.nolix.system.webgui.itemmenu;

//own imports
import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.web.css.CssProperty;
import ch.nolix.coreapi.webapi.cssapi.ICssProperty;
import ch.nolix.systemapi.webguiapi.itemmenuapi.IDropdownMenu;
import ch.nolix.systemapi.webguiapi.itemmenuapi.IDropdownMenuStyle;
import ch.nolix.systemapi.webguiapi.mainapi.ControlState;

//class
public final class DropdownMenuCssBuilder extends ItemMenuCssBuilder<IDropdownMenu, IDropdownMenuStyle> {

  // method
  @Override
  protected void fillUpCssPropertiesForControlAndAllStatesIntoList(
      final IDropdownMenu control,
      final LinkedList<CssProperty> list) {
    // Does nothing.
  }

  // method
  @Override
  protected void fillUpCssPropertiesForControlAndStateIntoList(
      final IDropdownMenu dropdownMenu,
      final ControlState state,
      final LinkedList<ICssProperty> list) {
    // Does nothing.
  }
}
