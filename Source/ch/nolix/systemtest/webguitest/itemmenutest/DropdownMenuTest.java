//package declaration
package ch.nolix.systemtest.webguitest.itemmenutest;

//own imports
import ch.nolix.system.webgui.itemmenu.DropdownMenu;
import ch.nolix.systemapi.webguiapi.itemmenuapi.IDropdownMenu;
import ch.nolix.systemapi.webguiapi.itemmenuapi.IDropdownMenuStyle;

//class
public final class DropdownMenuTest extends ItemMenuTest<IDropdownMenu, IDropdownMenuStyle> {

  // method
  @Override
  protected IDropdownMenu createTestUnit() {
    return new DropdownMenu();
  }
}
