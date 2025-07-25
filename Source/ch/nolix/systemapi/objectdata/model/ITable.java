package ch.nolix.systemapi.objectdata.model;

import java.util.Optional;

import ch.nolix.coreapi.attribute.mandatoryattribute.IIdHolder;
import ch.nolix.coreapi.attribute.mandatoryattribute.INameHolder;
import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.systemapi.databaseobject.model.IDatabaseObject;
import ch.nolix.systemapi.objectdata.schemaview.IColumnView;

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
