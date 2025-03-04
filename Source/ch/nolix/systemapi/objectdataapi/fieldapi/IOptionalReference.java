package ch.nolix.systemapi.objectdataapi.fieldapi;

import ch.nolix.coreapi.stateapi.statemutationapi.Clearable;
import ch.nolix.systemapi.objectdataapi.modelapi.IAbstractReference;
import ch.nolix.systemapi.objectdataapi.modelapi.IEntity;

public interface IOptionalReference<E extends IEntity> extends Clearable, IAbstractReference<E> {

  String getReferencedEntityId();

  E getStoredReferencedEntity();

  void setEntity(Object entity);

  void setEntityById(String id);
}
