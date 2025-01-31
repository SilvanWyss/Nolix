package ch.nolix.system.webgui.itemmenu;

import ch.nolix.system.webgui.basecontroltool.ControlHtmlBuilderTest;
import ch.nolix.systemapi.webguiapi.itemmenuapi.IDropdownMenu;

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
