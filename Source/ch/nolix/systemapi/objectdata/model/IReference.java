package ch.nolix.systemapi.objectdata.model;

public interface IReference<E extends IEntity> extends IBaseReference<E> {
  String getReferencedEntityId();

  String getReferencedTableId();

  String getReferencedTableName();

  E getStoredReferencedEntity();

  ITable<E> getStoredReferencedTable();

  void setEntity(Object entity);

  void setEntityById(String id);
}
