package ch.nolix.systemapi.objectdataapi.fieldapi;

import ch.nolix.systemapi.objectdataapi.modelapi.IAbstractBackReference;
import ch.nolix.systemapi.objectdataapi.modelapi.IEntity;

public interface IOptionalBackReference<E extends IEntity> extends IAbstractBackReference<E> {

  String getBackReferencedEntityId();

  E getStoredBackReferencedEntity();
}
