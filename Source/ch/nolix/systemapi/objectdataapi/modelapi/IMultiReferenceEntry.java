package ch.nolix.systemapi.objectdataapi.modelapi;

import java.util.Optional;

import ch.nolix.systemapi.databaseobjectapi.modelapi.IDatabaseObject;

public interface IMultiReferenceEntry<E extends IEntity> extends IDatabaseObject {

  Optional<? extends IField> getOptionalStoredBackReferencingField();

  String getReferencedEntityId();

  IMultiReference<E> getStoredParentMultiReference();

  E getStoredReferencedEntity();
}
