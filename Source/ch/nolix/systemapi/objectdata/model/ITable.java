/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.objectdata.model;

import java.util.Optional;

import ch.nolix.baseapi.attribute.mandatoryattribute.IdHolder;
import ch.nolix.baseapi.attribute.mandatoryattribute.NameHolder;
import ch.nolix.baseapi.component.datamodelcomponent.IDatabaseComponent;
import ch.nolix.baseapi.container.wellordercontainer.IWellOrderContainer;
import ch.nolix.systemapi.databaseobject.model.IDatabaseObject;

/**
 * @author Silvan Wyss
 * @param <E> is the type of the {@link IEntity}s of a {@link ITable}.
 */
public interface ITable<E extends IEntity>
extends IDatabaseComponent<IDatabase>, IDatabaseObject, IdHolder, NameHolder {
  boolean containsEntityWithId(String id);

  int getEntityCount();

  Class<E> getEntityType();

  Optional<E> getOptionalStoredEntityById(String id);

  IWellOrderContainer<IColumn> getStoredColumns();

  IWellOrderContainer<E> getStoredEntities();

  E getStoredEntityById(String id);

  ITable<E> insertEntity(E entity);

  IWellOrderContainer<E> internalGetStoredEntitiesInLocalData();
}
