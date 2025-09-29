package ch.nolix.systemapi.objectdata.model;

import ch.nolix.coreapi.container.base.IContainer;

public interface IBaseBackReference<E extends IEntity> extends IField {
  IContainer<String> getBackReferenceableTableNames();

  String getBackReferencedFieldName();

  String getBackReferencedTableName();

  ITable<E> getStoredBackReferencedTable();
}
