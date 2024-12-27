package ch.nolix.systemapi.objectdataapi.modelapi;

public interface IAbstractReferenceModel<E extends IEntity> extends IContentModel {

  ITable<E> getStoredencedTable();
}
