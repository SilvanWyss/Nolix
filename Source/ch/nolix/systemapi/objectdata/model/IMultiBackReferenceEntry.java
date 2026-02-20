/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.objectdata.model;

import ch.nolix.baseapi.component.datamodelcomponent.IDatabaseComponent;
import ch.nolix.baseapi.component.datamodelcomponent.ITableComponent;
import ch.nolix.systemapi.databaseobject.model.IDatabaseObject;

/**
 * @author Silvan Wyss
 * @param <E> is the type of the {@link IEntity} a
 *            {@link IMultiBackReferenceEntry} references back.
 */
public interface IMultiBackReferenceEntry<E extends IEntity>
extends IDatabaseComponent<IDatabase>, IDatabaseObject, ITableComponent<ITable<? extends IEntity>> {
  String getBackReferencedEntityId();

  String getBackReferencedTableId();

  E getStoredBackReferencedEntity();

  ITable<E> getStoredBackReferencedTable();

  IMultiBackReference<E> getStoredParentMultiBackReference();
}
