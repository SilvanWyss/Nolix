package ch.nolix.systemapi.objectdataapi.dataapi;

public interface IBackReference<E extends IEntity> extends IAbstractBackReference<E> {

  String getBackReferencedEntityId();

  E getStoredBackReferencedEntity();

  boolean referencesBackEntity();
}
