package ch.nolix.systemapi.objectdata.model;

public interface IBaseBackReference<E extends IEntity> extends IField {
  String getBackReferencedFieldName();

  String getBackReferencedTableId();

  String getBackReferencedTableName();

  ITable<E> getStoredBackReferencedTable();
}
