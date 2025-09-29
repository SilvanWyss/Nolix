package ch.nolix.systemapi.objectdata.model;

public interface IOptionalBackReference<E extends IEntity> extends IBaseBackReference<E> {
  String getBackReferencedEntityId();

  String getBackReferencedTableId();

  E getStoredBackReferencedEntity();
}
