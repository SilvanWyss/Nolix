package ch.nolix.systemapi.objectdataapi.dataapi;

public interface IOptionalBackReference<E extends IEntity> extends IBaseBackReference<E> {

  E getBackReferencedEntity();

  String getBackReferencedEntityId();
}
