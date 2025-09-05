package ch.nolix.systemapi.objectdata.model;

import ch.nolix.coreapi.state.statemutation.Clearable;

public interface IOptionalReference<E extends IEntity> extends Clearable, IBaseReference {
  String getReferencedEntityId();

  String getReferencedTableId();

  String getReferencedTableName();

  E getStoredReferencedEntity();

  ITable<E> getStoredReferencedTable();

  void setEntity(Object entity);

  void setEntityById(String id);
}
