package ch.nolix.systemapi.objectdata.model;

public interface IReference<E extends IEntity> extends IBaseReference<E> {
  String getReferencedEntityId();

  String getReferencedTableId();

  E getStoredReferencedEntity();

  void setEntity(Object entity);

  void setEntityById(String id);
}
