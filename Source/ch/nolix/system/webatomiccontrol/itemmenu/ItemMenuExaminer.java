/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.webatomiccontrol.itemmenu;

import ch.nolix.systemapi.webatomiccontrol.itemmenu.IItemMenu;
import ch.nolix.systemapi.webatomiccontrol.itemmenu.IItemMenuExaminer;
import ch.nolix.systemapi.webatomiccontrol.itemmenu.IItemMenuItem;

/**
 * @author Silvan Wyss
 */
public final class ItemMenuExaminer implements IItemMenuExaminer {
  @Override
  public boolean canAddItem(final IItemMenu<?, ?> itemMenu, final IItemMenuItem<?> item) {
    return itemMenu != null
    && item != null
    && !itemMenu.containsItemWithId(item.getId())
    && !itemMenu.containsItemWithText(item.getText());
  }
}
