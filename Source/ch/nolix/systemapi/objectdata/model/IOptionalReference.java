package ch.nolix.systemapi.objectdata.model;

import ch.nolix.coreapi.state.statemutation.Clearable;

public interface IOptionalReference<E extends IEntity> extends Clearable, IBaseReference<E> {

  String getReferencedEntityId();

  E getStoredReferencedEntity();

  void setEntity(Object entity);

  void setEntityById(String id);
}
