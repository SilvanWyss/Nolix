/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemtest.control.dropdownmenu;

import ch.nolix.system.control.dropdownmenu.DropdownMenu;
import ch.nolix.systemapi.control.dropdownmenu.IDropdownMenu;
import ch.nolix.systemapi.control.dropdownmenu.IDropdownMenuStyle;
import ch.nolix.systemtest.control.itemmenu.ItemMenuTest;

/**
 * @author Silvan Wyss
 */
final class DropdownMenuTest extends ItemMenuTest<IDropdownMenu, IDropdownMenuStyle> {
  /**
   * {@inheritDoc}
   */
  @Override
  protected IDropdownMenu createTestUnit() {
    return new DropdownMenu();
  }
}
