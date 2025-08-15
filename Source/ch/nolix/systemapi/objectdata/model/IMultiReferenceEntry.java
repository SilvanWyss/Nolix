package ch.nolix.systemapi.objectdata.model;

import java.util.Optional;

import ch.nolix.systemapi.databaseobject.model.IDatabaseObject;

public interface IMultiReferenceEntry<E extends IEntity> extends IDatabaseObject {

  Optional<? extends IField> getOptionalStoredBaseBackReferenceWhoReferencesBackTheParentMultiReferenceOfThis();

  String getReferencedEntityId();

  IMultiReference<E> getStoredParentMultiReference();

  E getStoredReferencedEntity();
}
