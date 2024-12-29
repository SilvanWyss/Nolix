package ch.nolix.systemapi.objectdataapi.modelapi;

public interface IOptionalBackReference<E extends IEntity> extends IAbstractBackReference<E> {

  String getBackReferencedEntityId();

  E getStoredBackReferencedEntity();
}
