package ch.nolix.systemapi.objectdataapi.dataapi;

public interface IBaseReference<E extends IEntity> extends IField {

  ITable<E> getReferencedTable();

  String getReferencedTableName();
}
