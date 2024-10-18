package ch.nolix.systemtest.webguitest.itemmenutest;

import ch.nolix.system.webgui.itemmenu.DropdownMenu;
import ch.nolix.system.webgui.itemmenu.DropdownMenuHtmlBuilder;
import ch.nolix.systemapi.webguiapi.itemmenuapi.IDropdownMenu;
import ch.nolix.systemtest.webguitest.basecontroltooltest.ControlHtmlBuilderTest;

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
