package ch.nolix.systemapi.objectdataapi.modelapi;

import ch.nolix.coreapi.state.statemutation.Clearable;

public interface IOptionalReference<E extends IEntity> extends Clearable, IAbstractReference<E> {

  String getReferencedEntityId();

  E getStoredReferencedEntity();

  void setEntity(Object entity);

  void setEntityById(String id);
}
