/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemtest.control.dropdownmenu;

import ch.nolix.system.control.dropdownmenu.DropdownMenu;
import ch.nolix.system.control.dropdownmenu.DropdownMenuHtmlBuilder;
import ch.nolix.systemapi.control.dropdownmenu.IDropdownMenu;
import ch.nolix.systemtest.webgui.basecontroltool.ControlHtmlBuilderTest;

/**
 * @author Silvan Wyss
 */
final class DropdownMenuHtmlBuilderTest extends ControlHtmlBuilderTest<DropdownMenuHtmlBuilder, IDropdownMenu> {
  /**
   * {@inheritDoc}
   */
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
