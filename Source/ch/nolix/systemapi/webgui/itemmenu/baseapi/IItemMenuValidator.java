package ch.nolix.systemapi.webgui.itemmenu.baseapi;

/**
 * @author Silvan Wyss
 * @version 2025-02-09
 */
public interface IItemMenuValidator {
  /**
   * @param itemMenu
   * @param item
   * @throws RuntimeException if the given itemMenu cannot add the given item.
   */
  void assertCanAddItem(IItemMenu<?, ?> itemMenu, IItemMenuItem<?> item);
}
