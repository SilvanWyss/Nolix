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
 * @param <E> the type of the {@link Entity}s of a {@link ITable}.
 */
public interface ITable<E extends Entity>
extends DatabaseComponent<IDatabase>, DatabaseObject, IdHolder, NameHolder {
  /**
   * @param id
   * @return true if the current {@link ITable} contains a {@link Entity} with the
   *         given id, false otherwise
   */
  boolean containsEntityWithId(String id);

  /**
   * @return the number of {@link Entity}s of the current {@link ITable}
   */
  int getEntityCount();

  /**
   * @return the type of the {@link Entity}s of the current {@link ITable}
   */
  Class<E> getEntityType();

  /**
   * @param id
   * @return a {@link Optional} with the {@link Entity} with the given id from the
   *         current {@link ITable} if the current {@link ITable} contains such a
   *         {@link Entity}, an empty {@link Optional} otherwise
   */
  Optional<E> getOptionalStoredEntityById(String id);

  /**
   * @return the {@link IColumn}s of the current {@link ITable}
   */
  ExtendedIterable<IColumn> getStoredColumns();

  /**
   * @return the {@link IColumn}s that reference the current {@link ITable}
   */
  ExtendedIterable<IColumn> getStoredColumnsThatReferenceMe();

  /**
   * @return the {@link Entity}s of the current {@link ITable}
   */
  ExtendedIterable<E> getStoredEntities();

  /**
   * @param id
   * @return the {@link Entity} with the given id from the current {@link ITable}
   * @throws RuntimeException if the current {@link ITable} does not contain a
   *                          {@link Entity} with the given id
   */
  E getStoredEntityById(String id);

  /**
   * Inserts the given entity into the current {@link ITable}.
   *
   * @param entity
   * @return the current {@link ITable}
   * @throws RuntimeException if the given entity is null
   * @throws RuntimeException if the current {@link ITable} cannot insert the
   *                          given entity
   */
  ITable<E> insertEntity(E entity);

  /**
   * Closes the {@link Entity}s of the current {@link ITable} that are in the
   * local data.
   */
  void internalClose();

  /**
   * @return the {@link Entity}s of the current {@link ITable} that are in the
   *         local data
   */
  ExtendedIterable<E> internalGetStoredEntitiesInLocalData();
}
