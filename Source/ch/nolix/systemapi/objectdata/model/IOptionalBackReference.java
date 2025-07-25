package ch.nolix.systemapi.objectdata.model;

public interface IOptionalBackReference<E extends IEntity> extends IAbstractBackReference<E> {

  String getBackReferencedEntityId();

  E getStoredBackReferencedEntity();
}
