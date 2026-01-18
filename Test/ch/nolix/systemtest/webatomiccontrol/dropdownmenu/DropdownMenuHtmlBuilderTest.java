package ch.nolix.systemtest.webatomiccontrol.dropdownmenu;

import ch.nolix.system.webatomiccontrol.dropdownmenu.DropdownMenu;
import ch.nolix.system.webatomiccontrol.dropdownmenu.DropdownMenuHtmlBuilder;
import ch.nolix.systemapi.webatomiccontrol.dropdownmenu.IDropdownMenu;
import ch.nolix.systemtest.webgui.basecontroltool.ControlHtmlBuilderTest;

/**
 * @author Silvan Wyss
 */
final class DropdownMenuHtmlBuilderTest extends ControlHtmlBuilderTest<DropdownMenuHtmlBuilder, IDropdownMenu> {
  @Override
  protected IDropdownMenu createControl() {
    return new DropdownMenu();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected DropdownMenuHtmlBuilder createTestUnit() {
    return new DropdownMenuHtmlBuilder();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected String getExpectedStringRepresentationOfCreatedHtmlElementForNewControl() {
    return "<select />";
  }
}
