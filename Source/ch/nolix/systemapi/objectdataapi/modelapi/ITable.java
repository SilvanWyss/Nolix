package ch.nolix.systemapi.objectdataapi.modelapi;

import java.util.Optional;

import ch.nolix.coreapi.attributeapi.mandatoryattributeapi.IIdHolder;
import ch.nolix.coreapi.attributeapi.mandatoryattributeapi.INameHolder;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.systemapi.databaseobjectapi.databaseobjectapi.IDatabaseObject;

public interface ITable<E extends IEntity> extends IDatabaseObject, IIdHolder, INameHolder {

  boolean containsEntityWithId(String id);

  int getEntityCount();

  Class<E> getEntityType();

  Optional<E> getOptionalStoredEntityById(String id);

  IContainer<IColumn> getStoredColumns();

  IContainer<E> getStoredEntities();

  E getStoredEntityById(String id);

  IDatabase getStoredParentDatabase();

  ITable<E> insertEntity(E entity);

  IContainer<E> internalGetStoredEntitiesInLocalData();
}
