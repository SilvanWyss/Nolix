/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemtest.control.dropdownmenu;

import ch.nolix.system.control.dropdownmenu.DropdownMenu;
import ch.nolix.system.control.dropdownmenu.DropdownMenuCssBuilder;
import ch.nolix.systemapi.control.dropdownmenu.IDropdownMenu;
import ch.nolix.systemtest.webgui.basecontroltool.ControlCssBuilderTest;

/**
 * @author Silvan Wyss
 */
final class DropdownMenuCssBuilderTest extends ControlCssBuilderTest<DropdownMenuCssBuilder, IDropdownMenu> {
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
  protected DropdownMenuCssBuilder createTestUnit() {
    return new DropdownMenuCssBuilder();
  }
}
