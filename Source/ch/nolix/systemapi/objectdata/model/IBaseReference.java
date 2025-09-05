package ch.nolix.systemapi.objectdata.model;

import ch.nolix.coreapi.container.base.IContainer;

/**
 * @author Silvan Wyss
 * @version 2021-11-13
 * @param <E> is the type of the {@link IEntity}s a {@link IBaseReference} can
 *            potentially reference.
 */
public interface IBaseReference<E extends IEntity> extends IField {
  /**
   * @return the names of the {@link ITable}s the current {@link IBaseReference}
   *         can reference.
   */
  IContainer<String> getReferenceableTableNames();

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
