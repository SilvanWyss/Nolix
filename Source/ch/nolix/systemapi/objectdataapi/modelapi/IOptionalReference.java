package ch.nolix.systemapi.objectdataapi.modelapi;

import ch.nolix.coreapi.stateapi.statemutationapi.Clearable;

public interface IOptionalReference<E extends IEntity> extends Clearable, IAbstractReference<E> {

  String getReferencedEntityId();

  E getStoredReferencedEntity();

  void setEntity(Object entity);

  void setEntityById(String id);
}
