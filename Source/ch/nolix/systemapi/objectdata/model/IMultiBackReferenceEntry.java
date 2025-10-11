package ch.nolix.systemapi.objectdata.model;

import ch.nolix.coreapi.component.datamodelcomponent.IDatabaseComponent;
import ch.nolix.coreapi.component.datamodelcomponent.ITableComponent;
import ch.nolix.systemapi.databaseobject.model.IDatabaseObject;

public interface IMultiBackReferenceEntry<E extends IEntity>
extends IDatabaseComponent<IDatabase>, IDatabaseObject, ITableComponent<ITable<? extends IEntity>> {
  String getBackReferencedEntityId();

  String getBackReferencedTableId();

  E getStoredBackReferencedEntity();

  ITable<E> getStoredBackReferencedTable();

  IMultiBackReference<E> getStoredParentMultiBackReference();
}
