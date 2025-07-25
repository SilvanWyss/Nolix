package ch.nolix.systemtest.webgui.itemmenu;

import ch.nolix.system.webgui.itemmenu.dropdownmenu.DropdownMenu;
import ch.nolix.systemapi.webguiapi.itemmenuapi.dropdownmenuapi.IDropdownMenu;
import ch.nolix.systemapi.webguiapi.itemmenuapi.dropdownmenuapi.IDropdownMenuStyle;

final class DropdownMenuTest extends ItemMenuTest<IDropdownMenu, IDropdownMenuStyle> {

  @Override
  protected IDropdownMenu createTestUnit() {
    return new DropdownMenu();
  }
}
