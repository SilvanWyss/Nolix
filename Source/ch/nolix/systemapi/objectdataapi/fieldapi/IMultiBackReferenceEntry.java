package ch.nolix.systemapi.objectdataapi.fieldapi;

import ch.nolix.systemapi.databaseobjectapi.modelapi.IDatabaseObject;
import ch.nolix.systemapi.objectdataapi.modelapi.IEntity;

public interface IMultiBackReferenceEntry<E extends IEntity> extends IDatabaseObject {

  String getBackReferencedEntityId();

  E getStoredBackReferencedEntity();

  IMultiBackReference<E> getStoredParentMultiBackReference();
}
