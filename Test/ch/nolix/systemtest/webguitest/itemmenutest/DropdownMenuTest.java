package ch.nolix.systemtest.webguitest.itemmenutest;

import ch.nolix.system.webgui.itemmenu.DropdownMenu;
import ch.nolix.systemapi.webguiapi.itemmenuapi.IDropdownMenu;
import ch.nolix.systemapi.webguiapi.itemmenuapi.IDropdownMenuStyle;

final class DropdownMenuTest extends ItemMenuTest<IDropdownMenu, IDropdownMenuStyle> {

  @Override
  protected IDropdownMenu createTestUnit() {
    return new DropdownMenu();
  }
}
