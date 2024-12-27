package ch.nolix.systemapi.objectdataapi.modelapi;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.systemapi.databaseobjectapi.databaseobjectapi.IDatabaseObject;
import ch.nolix.systemapi.timeapi.momentapi.ITime;

public interface IDatabase extends IDatabaseObject {

  <E extends IEntity> IContainer<E> getStoredEntitiesByType(Class<E> type);

  <E extends IEntity> ITable<E> getStoredTableByEntityType(Class<E> entityClass);

  ITable<IEntity> getStoredTableByName(String name);

  IContainer<? extends ITable<IEntity>> getStoredTables();

  ITime getSchemaTimestamp();

  <E extends IEntity> IDatabase insertEntity(E entity);
}
