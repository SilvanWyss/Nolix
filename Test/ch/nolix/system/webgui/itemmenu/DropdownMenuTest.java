package ch.nolix.system.webgui.itemmenu;

import ch.nolix.systemapi.webguiapi.itemmenuapi.IDropdownMenu;
import ch.nolix.systemapi.webguiapi.itemmenuapi.IDropdownMenuStyle;

final class DropdownMenuTest extends ItemMenuTest<IDropdownMenu, IDropdownMenuStyle> {

  @Override
  protected IDropdownMenu createTestUnit() {
    return new DropdownMenu();
  }
}
