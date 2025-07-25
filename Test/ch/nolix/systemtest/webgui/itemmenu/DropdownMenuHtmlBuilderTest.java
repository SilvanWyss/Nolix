package ch.nolix.systemtest.webgui.itemmenu;

import ch.nolix.system.webgui.itemmenu.dropdownmenu.DropdownMenu;
import ch.nolix.system.webgui.itemmenu.dropdownmenu.DropdownMenuHtmlBuilder;
import ch.nolix.systemapi.webguiapi.itemmenuapi.dropdownmenuapi.IDropdownMenu;
import ch.nolix.systemtest.webgui.basecontroltool.ControlHtmlBuilderTest;

final class DropdownMenuHtmlBuilderTest extends ControlHtmlBuilderTest<DropdownMenuHtmlBuilder, IDropdownMenu> {

  @Override
  protected IDropdownMenu createControl() {
    return new DropdownMenu();
  }

  @Override
  protected DropdownMenuHtmlBuilder createTestUnit() {
    return new DropdownMenuHtmlBuilder();
  }

  @Override
  protected String getExpectedStringRepresentationOfCreatedHtmlElementForNewControl() {
    return "<select />";
  }
}
