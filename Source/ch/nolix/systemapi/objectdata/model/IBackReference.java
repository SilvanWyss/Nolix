package ch.nolix.systemapi.objectdata.model;

public interface IBackReference<E extends IEntity> extends IAbstractBackReference<E> {

  String getBackReferencedEntityId();

  E getStoredBackReferencedEntity();

  boolean referencesBackEntity();
}
