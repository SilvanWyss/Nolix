package ch.nolix.systemapi.webatomiccontrol.itemmenu;

/**
 * @author Silvan Wyss
 */
public interface IItemMenuValidator {
  /**
   * @param itemMenu
   * @param item
   * @throws RuntimeException if the given itemMenu cannot add the given item.
   */
  void assertCanAddItem(IItemMenu<?, ?> itemMenu, IItemMenuItem<?> item);
}
