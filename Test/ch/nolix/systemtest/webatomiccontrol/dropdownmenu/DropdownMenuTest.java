package ch.nolix.systemtest.webatomiccontrol.dropdownmenu;

import ch.nolix.system.webatomiccontrol.dropdownmenu.DropdownMenu;
import ch.nolix.systemapi.webatomiccontrol.dropdownmenu.IDropdownMenu;
import ch.nolix.systemapi.webatomiccontrol.dropdownmenu.IDropdownMenuStyle;
import ch.nolix.systemtest.webgui.itemmenu.ItemMenuTest;

final class DropdownMenuTest extends ItemMenuTest<IDropdownMenu, IDropdownMenuStyle> {
  @Override
  protected IDropdownMenu createTestUnit() {
    return new DropdownMenu();
  }
}
