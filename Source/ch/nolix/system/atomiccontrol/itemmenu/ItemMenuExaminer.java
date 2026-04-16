/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.atomiccontrol.itemmenu;

import ch.nolix.systemapi.atomiccontrol.itemmenu.IItemMenu;
import ch.nolix.systemapi.atomiccontrol.itemmenu.IItemMenuExaminer;
import ch.nolix.systemapi.atomiccontrol.itemmenu.IItemMenuItem;

/**
 * @author Silvan Wyss
 */
public final class ItemMenuExaminer implements IItemMenuExaminer {
  /**
   * {@inheritDoc}
   */
  @Override
  public boolean canAddItem(final IItemMenu<?, ?> itemMenu, final IItemMenuItem<?> item) {
    return itemMenu != null
    && item != null
    && !itemMenu.containsItemWithId(item.getId())
    && !itemMenu.containsItemWithText(item.getText());
  }
}
