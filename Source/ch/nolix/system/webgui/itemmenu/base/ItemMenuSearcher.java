package ch.nolix.system.webgui.itemmenu.base;

import ch.nolix.systemapi.webgui.itemmenu.baseapi.IItemMenu;
import ch.nolix.systemapi.webgui.itemmenu.baseapi.IItemMenuItem;
import ch.nolix.systemapi.webgui.itemmenu.baseapi.IItemMenuSearcher;

/**
 * @author Silvan Wyss
 * @version 2025-02-10
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
