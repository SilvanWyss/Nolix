/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.objectdata.model;

import java.util.Optional;

import ch.nolix.baseapi.objectcomposition.datamodelcomponent.DatabaseComponent;
import ch.nolix.baseapi.objectcomposition.datamodelcomponent.TableComponent;
import ch.nolix.systemapi.database.databaseobject.DatabaseObject;

/**
 * @author Silvan Wyss
 * @param <E> the type of the {@link Entity} a {@link IMultiReferenceEntry}
 *            references.
 */
public interface IMultiReferenceEntry<E extends Entity>
extends DatabaseComponent<IDatabase>, DatabaseObject, TableComponent<ITable<? extends Entity>> {
  Optional<? extends Field> getOptionalStoredBaseBackReferenceWhoReferencesBackTheParentMultiReferenceOfThis();

  String getReferencedEntityId();

  String getReferencedTableId();

  String getReferencedTableName();

  IMultiReference<E> getStoredParentMultiReference();

  E getStoredReferencedEntity();

  ITable<E> getStoredReferencedTable();
}
