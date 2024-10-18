package ch.nolix.systemapi.objectdataapi.dataapi;

public interface IBackReference<E extends IEntity> extends IBaseBackReference<E> {

  String getBackReferencedEntityId();

  E getStoredBackReferencedEntity();

  boolean referencesBackEntity();
}
