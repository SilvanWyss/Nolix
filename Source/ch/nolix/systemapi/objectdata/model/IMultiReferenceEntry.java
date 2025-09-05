package ch.nolix.systemapi.objectdata.model;

import java.util.Optional;

import ch.nolix.coreapi.component.datamodelcomponent.IDatabaseComponent;
import ch.nolix.coreapi.component.datamodelcomponent.ITableComponent;
import ch.nolix.systemapi.databaseobject.model.IDatabaseObject;

public interface IMultiReferenceEntry<E extends IEntity>
extends IDatabaseComponent<IDatabase>, IDatabaseObject, ITableComponent<ITable<? extends IEntity>> {
  Optional<? extends IField> getOptionalStoredBaseBackReferenceWhoReferencesBackTheParentMultiReferenceOfThis();

  String getReferencedEntityId();

  String getReferencedTableId();

  String getReferencedTableName();

  IMultiReference<E> getStoredParentMultiReference();

  E getStoredReferencedEntity();

  ITable<E> getStoredReferencedTable();
}
