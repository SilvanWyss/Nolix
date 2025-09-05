package ch.nolix.systemapi.objectdata.model;

import java.util.Optional;

import ch.nolix.coreapi.component.datamodelcomponent.ITableComponent;
import ch.nolix.systemapi.databaseobject.model.IDatabaseObject;

public interface IMultiReferenceEntry<E extends IEntity>
extends IDatabaseObject, ITableComponent<ITable<? extends IEntity>> {
  Optional<? extends IField> getOptionalStoredBaseBackReferenceWhoReferencesBackTheParentMultiReferenceOfThis();

  String getReferencedEntityId();

  String getReferencedTableId();

  IMultiReference<E> getStoredParentMultiReference();

  E getStoredReferencedEntity();

  ITable<E> getStoredReferencedTable();
}
