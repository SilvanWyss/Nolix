package ch.nolix.systemapi.objectdata.model;

public interface IBackReference<E extends IEntity> extends IBaseBackReference<E> {
  String getBackReferencedEntityId();

  E getStoredBackReferencedEntity();

  boolean referencesBackEntity();
}
