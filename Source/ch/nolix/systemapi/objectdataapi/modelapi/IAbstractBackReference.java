package ch.nolix.systemapi.objectdataapi.modelapi;

public interface IAbstractBackReference<E extends IEntity> extends IField {

  String getBackReferencedFieldName();

  String getBackReferencedTableName();

  ITable<E> getStoredBackReferencedTable();
}
