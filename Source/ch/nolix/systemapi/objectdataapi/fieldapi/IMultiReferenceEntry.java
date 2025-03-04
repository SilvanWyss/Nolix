package ch.nolix.systemapi.objectdataapi.fieldapi;

import java.util.Optional;

import ch.nolix.systemapi.databaseobjectapi.modelapi.IDatabaseObject;
import ch.nolix.systemapi.objectdataapi.modelapi.IEntity;
import ch.nolix.systemapi.objectdataapi.modelapi.IField;

public interface IMultiReferenceEntry<E extends IEntity> extends IDatabaseObject {

  Optional<? extends IField> getOptionalStoredBackReferencingField();

  String getReferencedEntityId();

  IMultiReference<E> getStoredParentMultiReference();

  E getStoredReferencedEntity();
}
