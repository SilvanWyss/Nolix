package ch.nolix.systemapi.objectdataapi.dataapi;

public interface IAbstractReference<E extends IEntity> extends IField {

  ITable<E> getReferencedTable();

  String getReferencedTableName();
}
