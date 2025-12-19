package ch.nolix.system.webatomiccontrol.itemmenu;

import ch.nolix.systemapi.webatomiccontrol.itemmenu.IItemMenu;
import ch.nolix.systemapi.webatomiccontrol.itemmenu.IItemMenuItem;
import ch.nolix.systemapi.webatomiccontrol.itemmenu.IItemMenuSearcher;

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
