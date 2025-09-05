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
}
