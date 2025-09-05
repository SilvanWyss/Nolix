package ch.nolix.coreapi.component.datamodelcomponent;

/**
 * A {@link ITableComponent} can belong to a table.
 * 
 * @author Silvan Wyss
 * @version 2024-12-14
 * @param <T> is the type of the table a {@link ITableComponent} can belong to.
 */
public interface ITableComponent<T> {
  /**
   * @return true if the current {@link ITableComponent} belongs to a table, false
   *         otherwise.
   */
  boolean belongsToTable();

  /**
   * @return the table of the current {@link ITableComponent}.
   * @throws RuntimeException if the current {@link ITableComponent} does not
   *                          belong to a table.
   */
  T getStoredParentTable();
}
