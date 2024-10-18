package ch.nolix.systemapi.objectdataapi.dataapi;

import ch.nolix.systemapi.databaseobjectapi.databaseobjectapi.IDatabaseObject;

public interface IMultiBackReferenceEntry<E extends IEntity> extends IDatabaseObject {

  String getBackReferencedEntityId();

  E getStoredBackReferencedEntity();

  IMultiBackReference<E> getStoredParentMultiBackReference();
}
