package ch.nolix.coreapi.componentapi.datamodelcomponentapi;

/**
 * A {@link IDatabaseComponent} can belong to a database.
 * 
 * @author Silvan Wyss
 * @version 2024-12-14
 * @param <D> is the type of the database a {@link IDatabaseComponent} can
 *            belong to.
 */
public interface IDatabaseComponent<D> {

  /**
   * @return true if the current {@link IDatabaseComponent} belongs to a database,
   *         false otherwise.
   */
  boolean belongsToDatabase();

  /**
   * @return the database of the current {@link IDatabaseComponent}.
   * @throws RuntimeException if the current {@link IDatabaseComponent} does not
   *                          belong to a database.
   */
  D getStoredParentDatabase();
}
