package ch.nolix.systemapi.objectdataapi.dataapi;

public interface IAbstractReferenceModel<E extends IEntity> extends IContentModel {

  ITable<E> getStoredencedTable();
}
