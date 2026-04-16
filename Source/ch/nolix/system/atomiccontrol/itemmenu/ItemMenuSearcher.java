/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.atomiccontrol.itemmenu;

import ch.nolix.systemapi.atomiccontrol.itemmenu.IItemMenu;
import ch.nolix.systemapi.atomiccontrol.itemmenu.IItemMenuItem;
import ch.nolix.systemapi.atomiccontrol.itemmenu.IItemMenuSearcher;

/**
 * @author Silvan Wyss
 */
public final class ItemMenuSearcher implements IItemMenuSearcher {
  /**
   * {@inheritDoc}
   */
  @Override
  public IItemMenuItem<?> getStoredBlankItem(final IItemMenu<?, ?> itemMenu) {
    return itemMenu.getStoredItems().getStoredFirst(IItemMenuItem::isBlank);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IItemMenuItem<?> getStoredFirstItem(final IItemMenu<?, ?> itemMenu) {
    return itemMenu.getStoredItems().getStoredFirst();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IItemMenuItem<?> getStoredItemById(final IItemMenu<?, ?> itemMenu, final String itemId) {
    return itemMenu.getStoredItems().getStoredFirst(i -> i.hasId(itemId));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IItemMenuItem<?> getStoredItemByText(final IItemMenu<?, ?> itemMenu, final String itemText) {
    return itemMenu.getStoredItems().getStoredFirst(i -> i.getText().equals(itemText));
  }
}
