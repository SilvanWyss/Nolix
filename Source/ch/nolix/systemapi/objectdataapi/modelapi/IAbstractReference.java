package ch.nolix.systemapi.objectdataapi.modelapi;

public interface IAbstractReference<E extends IEntity> extends IField {

  ITable<E> getReferencedTable();

  String getReferencedTableName();
}
