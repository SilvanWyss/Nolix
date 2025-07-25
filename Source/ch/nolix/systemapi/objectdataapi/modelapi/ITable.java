package ch.nolix.systemapi.objectdataapi.modelapi;

import java.util.Optional;

import ch.nolix.coreapi.attribute.mandatoryattribute.IIdHolder;
import ch.nolix.coreapi.attribute.mandatoryattribute.INameHolder;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.systemapi.databaseobjectapi.modelapi.IDatabaseObject;
import ch.nolix.systemapi.objectdataapi.schemaviewapi.IColumnView;

public interface ITable<E extends IEntity> extends IDatabaseObject, IIdHolder, INameHolder {

  boolean containsEntityWithId(String id);

  int getEntityCount();

  Class<E> getEntityType();

  Optional<E> getOptionalStoredEntityById(String id);

  IContainer<IColumnView<ITable<IEntity>>> getStoredColumns();

  IContainer<E> getStoredEntities();

  E getStoredEntityById(String id);

  IDatabase getStoredParentDatabase();

  ITable<E> insertEntity(E entity);

  IContainer<E> internalGetStoredEntitiesInLocalData();
}
