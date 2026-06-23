/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.control.itemmenu;

import ch.nolix.systemapi.control.itemmenu.IItemMenu;
import ch.nolix.systemapi.control.itemmenu.IItemMenuExaminer;
import ch.nolix.systemapi.control.itemmenu.IItemMenuItem;

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
