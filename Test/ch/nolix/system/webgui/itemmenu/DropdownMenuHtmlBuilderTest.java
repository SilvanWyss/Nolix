package ch.nolix.system.webgui.itemmenu;

import ch.nolix.system.webgui.basecontroltool.ControlHtmlBuilderTest;
import ch.nolix.system.webgui.itemmenu.dropdownmenu.DropdownMenu;
import ch.nolix.system.webgui.itemmenu.dropdownmenu.DropdownMenuHtmlBuilder;
import ch.nolix.systemapi.webguiapi.itemmenuapi.dropdownmenuapi.IDropdownMenu;

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
