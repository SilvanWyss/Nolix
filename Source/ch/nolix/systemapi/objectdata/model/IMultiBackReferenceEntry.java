/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.objectdata.model;

import ch.nolix.baseapi.objectcomposition.datamodelcomponent.DatabaseComponent;
import ch.nolix.baseapi.objectcomposition.datamodelcomponent.TableComponent;
import ch.nolix.systemapi.database.databaseobject.DatabaseObject;

/**
 * @author Silvan Wyss
 * @param <E> the type of the {@link Entity} a {@link IMultiBackReferenceEntry}
 *            references back.
 */
public interface IMultiBackReferenceEntry<E extends Entity>
extends DatabaseComponent<IDatabase>, DatabaseObject, TableComponent<ITable<? extends Entity>> {
  String getBackReferencedEntityId();

  String getBackReferencedTableId();

  E getStoredBackReferencedEntity();

  ITable<E> getStoredBackReferencedTable();

  IMultiBackReference<E> getStoredParentMultiBackReference();
}
