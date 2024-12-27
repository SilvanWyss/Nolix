package ch.nolix.systemapi.objectdataapi.modelapi;

public interface IOptionalBackReference<E extends IEntity> extends IAbstractBackReference<E> {

  E getBackReferencedEntity();

  String getBackReferencedEntityId();
}
