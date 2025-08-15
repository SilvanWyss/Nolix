package ch.nolix.systemapi.objectdata.model;

/**
 * @author Silvan Wyss
 * @version 2021-11-13
 * @param <E> is the type of the {@link IEntity}s a {@link IBaseReference} can
 *            reference.
 */
public interface IBaseReference<E extends IEntity> extends IField {

  /**
   * @return the type of the {@link IEntity}s the current {@link IBaseReference}
   *         can reference.
   */
  Class<E> getReferanceableEntityType();

  /**
   * @return the id of the referenced {@link ITable} of the current
   *         {@link IBaseReference}.
   */
  String getReferencedTableId();

  /**
   * @return the name of the referenced {@link ITable} of the current
   *         {@link IBaseReference}.
   */
  String getReferencedTableName();

  /**
   * @return the referenced {@link ITable} of the current {@link IBaseReference}.
   */
  ITable<E> getStoredReferencedTable();
}
