package ch.nolix.systemapi.webguiapi.itemmenuapi;

/**
 * @author Silvan Wyss
 * @version 2025-02-09
 */
public interface IItemMenuExaminer {

  /**
   * @param itemMenu
   * @param item
   * @return true if the given itemMenu can add the given item, false otherwise.
   */
  boolean canAddItem(IItemMenu<?, ?> itemMenu, IItemMenuItem<?> item);
}
