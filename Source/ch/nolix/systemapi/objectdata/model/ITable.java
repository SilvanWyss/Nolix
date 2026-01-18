/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.objectdata.model;

import java.util.Optional;

import ch.nolix.coreapi.attribute.mandatoryattribute.IIdHolder;
import ch.nolix.coreapi.attribute.mandatoryattribute.INameHolder;
import ch.nolix.coreapi.component.datamodelcomponent.IDatabaseComponent;
import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.systemapi.databaseobject.model.IDatabaseObject;

/**
 * @author Silvan Wyss
 * @param <E> is the type of the {@link IEntity}s of a {@link ITable}.
 */
public interface ITable<E extends IEntity>
extends IDatabaseComponent<IDatabase>, IDatabaseObject, IIdHolder, INameHolder {
  boolean containsEntityWithId(String id);

  int getEntityCount();

  Class<E> getEntityType();

  Optional<E> getOptionalStoredEntityById(String id);

  IContainer<IColumn> getStoredColumns();

  IContainer<E> getStoredEntities();

  E getStoredEntityById(String id);

  ITable<E> insertEntity(E entity);

  IContainer<E> internalGetStoredEntitiesInLocalData();
}
