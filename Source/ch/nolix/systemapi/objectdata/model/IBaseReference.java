package ch.nolix.systemapi.objectdata.model;

import ch.nolix.coreapi.container.base.IContainer;

/**
 * @author Silvan Wyss
 * @version 2021-11-13
 */
public interface IBaseReference extends IField {
  /**
   * @return the names of the {@link ITable}s the current {@link IBaseReference}
   *         can reference.
   */
  IContainer<String> getReferenceableTableNames();
}
