package ch.nolix.systemapi.objectdataapi.modelapi;

public interface IOptionalBackReference<E extends IEntity> extends IAbstractBackReference<E> {

  //TODO: Rename this method
  E getBackReferencedEntity();

  String getBackReferencedEntityId();
}
