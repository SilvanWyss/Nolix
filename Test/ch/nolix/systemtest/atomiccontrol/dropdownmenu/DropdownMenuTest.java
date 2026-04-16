/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemtest.atomiccontrol.dropdownmenu;

import ch.nolix.system.atomiccontrol.dropdownmenu.DropdownMenu;
import ch.nolix.systemapi.atomiccontrol.dropdownmenu.IDropdownMenu;
import ch.nolix.systemapi.atomiccontrol.dropdownmenu.IDropdownMenuStyle;
import ch.nolix.systemtest.atomiccontrol.itemmenu.ItemMenuTest;

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
