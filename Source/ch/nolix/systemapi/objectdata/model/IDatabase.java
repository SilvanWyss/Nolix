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
  IEntityTypeSet getEntityTypeSet();

  ITime getSchemaTimestamp();

  <E extends Entity> ExtendedIterable<E> getStoredEntitiesByType(Class<E> type);

  <E extends Entity> ITable<E> getStoredTableByEntityType(Class<E> entityType);

  ITable<Entity> getStoredTableByName(String name);

  ExtendedIterable<? extends ITable<Entity>> getStoredTables();

  <E extends Entity> IDatabase insertEntity(E entity);
}
