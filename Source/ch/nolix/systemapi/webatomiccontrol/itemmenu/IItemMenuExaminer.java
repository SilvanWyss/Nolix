package ch.nolix.systemapi.webatomiccontrol.itemmenu;

/**
 * @author Silvan Wyss
 */
public interface IItemMenuExaminer {
  /**
   * @param itemMenu
   * @param item
   * @return true if the given itemMenu can add the given item, false otherwise.
   */
  boolean canAddItem(IItemMenu<?, ?> itemMenu, IItemMenuItem<?> item);
}
