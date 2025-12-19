package ch.nolix.systemapi.objectdata.model;

import ch.nolix.coreapi.container.base.IContainer;

/**
 * @author Silvan Wyss
 */
public interface IBaseBackReference extends IField {
  /**
   * @return the names of the {@link ITable}s the current
   *         {@link IBaseBackReference} can reference back.
   */
  IContainer<String> getBackReferenceableTableNames();

  /**
   * @return the name of the field the current {@link IBaseBackReference} can
   *         reference back.
   */
  String getBackReferencedFieldName();
}
