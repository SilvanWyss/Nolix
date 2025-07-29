package ch.nolix.systemapi.objectdata.model;

import ch.nolix.coreapi.attribute.mandatoryattribute.INameHolder;
import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.coreapi.resourcecontrol.closecontroller.GroupCloseable;
import ch.nolix.systemapi.databaseobject.model.IDatabaseObject;
import ch.nolix.systemapi.time.moment.ITime;

public interface IDatabase extends GroupCloseable, IDatabaseObject, INameHolder {

  IEntityTypeSet getEntityTypeSet();

  ITime getSchemaTimestamp();

  <E extends IEntity> IContainer<E> getStoredEntitiesByType(Class<E> type);

  <E extends IEntity> ITable<E> getStoredTableByEntityType(Class<E> entityType);

  ITable<IEntity> getStoredTableByName(String name);

  IContainer<? extends ITable<IEntity>> getStoredTables();

  <E extends IEntity> IDatabase insertEntity(E entity);
}
