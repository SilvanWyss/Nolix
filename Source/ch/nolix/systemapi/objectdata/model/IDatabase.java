/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.objectdata.model;

import ch.nolix.baseapi.attribute.mandatoryattribute.NameHolder;
import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.baseapi.resourcecontrol.closecontroller.GroupCloseable;
import ch.nolix.systemapi.database.databaseobject.DatabaseObject;
import ch.nolix.systemapi.time.main.ITime;

/**
 * @author Silvan Wyss
 */
public interface IDatabase extends GroupCloseable, DatabaseObject, NameHolder {
  /**
   * @return the {@link IEntityTypeSet} of the current {@link IDatabase}
   */
  IEntityTypeSet getEntityTypeSet();

  /**
   * @return the timestamp of the schema of the current {@link IDatabase}
   */
  ITime getSchemaTimestamp();

  /**
   * @param type
   * @param <E>  the type of the {@link Entity}s to return
   * @return the {@link Entity}s of the given type from the current
   *         {@link IDatabase}
   * @throws RuntimeException if the current {@link IDatabase} does not contain a
   *                          {@link ITable} for the given type
   */
  <E extends Entity> ExtendedIterable<E> getStoredEntitiesByType(Class<E> type);

  /**
   * @param entityType
   * @param <E>        the type of the {@link Entity}s of the {@link ITable} to
   *                   return
   * @return the {@link ITable} for the given entityType from the current
   *         {@link IDatabase}
   * @throws RuntimeException if the current {@link IDatabase} does not contain a
   *                          {@link ITable} for the given entityType
   */
  <E extends Entity> ITable<E> getStoredTableByEntityType(Class<E> entityType);

  /**
   * @param name
   * @return the {@link ITable} with the given name from the current
   *         {@link IDatabase}
   * @throws RuntimeException if the current {@link IDatabase} does not contain a
   *                          {@link ITable} with the given name
   */
  ITable<Entity> getStoredTableByName(String name);

  /**
   * @return the {@link ITable}s of the current {@link IDatabase}
   */
  ExtendedIterable<? extends ITable<Entity>> getStoredTables();

  /**
   * Inserts the given entity into the according {@link ITable} of the current
   * {@link IDatabase}.
   *
   * @param entity
   * @param <E>    the type of the given entity
   * @return the current {@link IDatabase}
   * @throws RuntimeException if the given entity is null
   * @throws RuntimeException if the current {@link IDatabase} does not contain a
   *                          {@link ITable} for the given entity
   * @throws RuntimeException if the according {@link ITable} cannot insert the
   *                          given entity
   */
  <E extends Entity> IDatabase insertEntity(E entity);
}
