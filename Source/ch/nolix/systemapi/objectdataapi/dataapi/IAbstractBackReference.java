package ch.nolix.systemapi.objectdataapi.dataapi;

public interface IAbstractBackReference<E extends IEntity> extends IField {

  String getBackReferencedFieldName();

  String getBackReferencedTableName();

  ITable<E> getStoredBackReferencedTable();
}
