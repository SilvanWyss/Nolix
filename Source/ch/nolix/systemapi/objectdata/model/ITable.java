/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.objectdata.model;

import java.util.Optional;

import ch.nolix.baseapi.attribute.mandatoryattribute.IdHolder;
import ch.nolix.baseapi.attribute.mandatoryattribute.NameHolder;
import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.baseapi.objectcomposition.datamodelcomponent.DatabaseComponent;
import ch.nolix.systemapi.database.databaseobject.DatabaseObject;

/**
 * @author Silvan Wyss
 * @param <E> the type of the {@link IEntity}s of a {@link ITable}.
 */
public interface ITable<E extends IEntity>
extends DatabaseComponent<IDatabase>, DatabaseObject, IdHolder, NameHolder {
  boolean containsEntityWithId(String id);

  int getEntityCount();

  Class<E> getEntityType();

  Optional<E> getOptionalStoredEntityById(String id);

  ExtendedIterable<IColumn> getStoredColumns();

  ExtendedIterable<E> getStoredEntities();

  E getStoredEntityById(String id);

  ITable<E> insertEntity(E entity);

  ExtendedIterable<E> internalGetStoredEntitiesInLocalData();
}
