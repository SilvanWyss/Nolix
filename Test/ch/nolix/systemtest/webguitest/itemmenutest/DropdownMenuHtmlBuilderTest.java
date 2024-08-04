//package declaration
package ch.nolix.systemtest.webguitest.itemmenutest;

//own imports
import ch.nolix.system.webgui.itemmenu.DropdownMenu;
import ch.nolix.system.webgui.itemmenu.DropdownMenuHtmlBuilder;
import ch.nolix.systemapi.webguiapi.itemmenuapi.IDropdownMenu;
import ch.nolix.systemtest.webguitest.basecontroltooltest.ControlHtmlBuilderTest;

//class
final class DropdownMenuHtmlBuilderTest extends ControlHtmlBuilderTest<DropdownMenuHtmlBuilder, IDropdownMenu> {

  //method
  @Override
  protected IDropdownMenu createControl() {
    return new DropdownMenu();
  }

  //method
  @Override
  protected DropdownMenuHtmlBuilder createTestUnit() {
    return new DropdownMenuHtmlBuilder();
  }

  //method
  @Override
  protected String getExpectedStringRepresentationOfCreatedHtmlElementForNewControl() {
    return "<select />";
  }
}
