package ch.nolix.systemapi.objectdata.model;

public interface IReference<E extends IEntity> extends IAbstractReference<E> {

  String getReferencedEntityId();

  E getStoredReferencedEntity();

  void setEntity(Object entity);

  void setEntityById(String id);
}
