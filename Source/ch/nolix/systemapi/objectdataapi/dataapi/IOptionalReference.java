package ch.nolix.systemapi.objectdataapi.dataapi;

import ch.nolix.coreapi.stateapi.statemutationapi.Clearable;

public interface IOptionalReference<E extends IEntity> extends Clearable, IAbstractReference<E> {

  E getReferencedEntity();

  String getReferencedEntityId();

  void setEntity(Object entity);

  void setEntityById(String id);
}
