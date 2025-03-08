package ch.nolix.systemapi.objectdataapi.modelapi;

import ch.nolix.systemapi.databaseobjectapi.modelapi.IDatabaseObject;

public interface IMultiBackReferenceEntry<E extends IEntity> extends IDatabaseObject {

  String getBackReferencedEntityId();

  E getStoredBackReferencedEntity();

  IMultiBackReference<E> getStoredParentMultiBackReference();
}
