package ch.nolix.systemtest.webatomiccontrol.dropdownmenu;

import ch.nolix.system.webatomiccontrol.dropdownmenu.DropdownMenu;
import ch.nolix.systemapi.webatomiccontrol.dropdownmenu.IDropdownMenu;
import ch.nolix.systemapi.webatomiccontrol.dropdownmenu.IDropdownMenuStyle;
import ch.nolix.systemtest.webatomiccontrol.itemmenu.ItemMenuTest;

/**
 * @author Silvan Wyss
 */
final class DropdownMenuTest extends ItemMenuTest<IDropdownMenu, IDropdownMenuStyle> {
  @Override
  protected IDropdownMenu createTestUnit() {
    return new DropdownMenu();
  }
}
