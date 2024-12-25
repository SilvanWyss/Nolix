package ch.nolix.systemapi.objectdataapi.dataapi;

public interface IReference<E extends IEntity> extends IAbstractReference<E> {

  String getReferencedEntityId();

  E getStoredReferencedEntity();

  void setEntity(Object entity);

  void setEntityById(String id);
}
