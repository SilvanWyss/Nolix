package ch.nolix.systemapi.objectdataapi.dataapi;

import java.util.Optional;

import ch.nolix.systemapi.databaseobjectapi.databaseobjectapi.IDatabaseObject;

public interface IMultiReferenceEntry<E extends IEntity> extends IDatabaseObject {

  Optional<? extends IField> getOptionalStoredBackReferencingField();

  String getReferencedEntityId();

  IMultiReference<E> getStoredParentMultiReference();

  E getStoredReferencedEntity();
}
