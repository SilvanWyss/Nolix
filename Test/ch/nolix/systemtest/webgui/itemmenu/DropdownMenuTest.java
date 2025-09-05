package ch.nolix.systemtest.webgui.itemmenu;

import ch.nolix.system.webgui.itemmenu.dropdownmenu.DropdownMenu;
import ch.nolix.systemapi.webgui.itemmenu.dropdownmenuapi.IDropdownMenu;
import ch.nolix.systemapi.webgui.itemmenu.dropdownmenuapi.IDropdownMenuStyle;

final class DropdownMenuTest extends ItemMenuTest<IDropdownMenu, IDropdownMenuStyle> {
  @Override
  protected IDropdownMenu createTestUnit() {
    return new DropdownMenu();
  }
}
