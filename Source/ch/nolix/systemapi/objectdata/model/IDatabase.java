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

  <E extends IEntity> ExtendedIterable<E> getStoredEntitiesByType(Class<E> type);

  <E extends IEntity> ITable<E> getStoredTableByEntityType(Class<E> entityType);

  ITable<IEntity> getStoredTableByName(String name);

  ExtendedIterable<? extends ITable<IEntity>> getStoredTables();

  <E extends IEntity> IDatabase insertEntity(E entity);
}
