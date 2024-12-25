package ch.nolix.systemapi.objectdataapi.dataapi;

public interface IOptionalBackReference<E extends IEntity> extends IAbstractBackReference<E> {

  E getBackReferencedEntity();

  String getBackReferencedEntityId();
}
