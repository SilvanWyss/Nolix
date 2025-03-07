package ch.nolix.systemapi.objectdataapi.modelapi;

import ch.nolix.coreapi.attributeapi.mandatoryattributeapi.INameHolder;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.resourcecontrolapi.resourceclosingapi.GroupCloseable;
import ch.nolix.systemapi.databaseobjectapi.modelapi.IDatabaseObject;
import ch.nolix.systemapi.timeapi.momentapi.ITime;

public interface IDatabase extends GroupCloseable, IDatabaseObject, INameHolder {

  IEntityTypeSet getEntityTypeSet();

  ITime getSchemaTimestamp();

  <E extends IEntity> IContainer<E> getStoredEntitiesByType(Class<E> type);

  <E extends IEntity> ITable<E> getStoredTableByEntityType(Class<E> entityType);

  ITable<IEntity> getStoredTableByName(String name);

  IContainer<? extends ITable<IEntity>> getStoredTables();

  <E extends IEntity> IDatabase insertEntity(E entity);
}
