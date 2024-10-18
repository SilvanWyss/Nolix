package ch.nolix.systemapi.objectdataapi.dataapi;

public interface IBaseParameterizedReferenceType<E extends IEntity> extends IParameterizedFieldType {

  ITable<E> getStoredencedTable();
}
