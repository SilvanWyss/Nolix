package ch.nolix.systemapi.webatomiccontrol.itemmenu;

/**
 * @author Silvan Wyss
 * @version 2025-02-10
 */
public interface IItemMenuSearcher {
  /**
   * @param itemMenu
   * @return the blank item of the given itemMenu.
   * @throws RuntimeException if the given itemMenu does not contain a blank item.
   */
  IItemMenuItem<?> getStoredBlankItem(IItemMenu<?, ?> itemMenu);

  /**
   * @param itemMenu
   * @return the first item of the given itemMenu.
   * @throws RuntimeException if the given itemMenu dies not contain an item.
   */
  IItemMenuItem<?> getStoredFirstItem(IItemMenu<?, ?> itemMenu);

  /**
   * @param itemMenu
   * @param id
   * @return the item with the given id from the given itemMenu.
   * @throws RuntimeException if the given itemMenu does not contain an item with
   *                          the given id.
   */
  IItemMenuItem<?> getStoredItemById(IItemMenu<?, ?> itemMenu, String id);

  /**
   * @param itemMenu
   * @param text
   * @return the item with the given text from the given itemMenu.
   * @throws RuntimeException if the given itemMenu does not contain an item with
   *                          the given text.
   */
  IItemMenuItem<?> getStoredItemByText(IItemMenu<?, ?> itemMenu, String text);
}
