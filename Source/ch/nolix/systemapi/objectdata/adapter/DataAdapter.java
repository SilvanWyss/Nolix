/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.objectdata.adapter;

import ch.nolix.baseapi.attribute.mandatoryattribute.DatabaseNameHolder;
import ch.nolix.baseapi.programcontrol.copy.EmptyCopyable;
import ch.nolix.baseapi.resourcecontrol.savecontrol.IResettableChangeSaver;
import ch.nolix.systemapi.objectdata.model.Entity;
import ch.nolix.systemapi.objectdata.model.ITable;

/**
 * @author Silvan Wyss
 */
public interface DataAdapter extends EmptyCopyable<DataAdapter>, DatabaseNameHolder, IResettableChangeSaver {
  /**
   * @param type
   * @param id
   * @param <E>  the type of the {@link Entity} to return
   * @return the {@link Entity} of the given type with the given id from the
   *         current {@link DataAdapter}
   * @throws RuntimeException if the current {@link DataAdapter} does not contain
   *                          a {@link Entity} of the given type with the given id
   */
  <E extends Entity> E getStoredEntityByTypeAndId(Class<E> type, String id);

  /**
   * @param entityType
   * @param <E>        the type of the {@link Entity}s of the {@link ITable} to
   *                   return
   * @return the {@link ITable} for the given entityType from the current
   *         {@link DataAdapter}
   * @throws RuntimeException if the current {@link DataAdapter} does not contain
   *                          a {@link ITable} for the given entityType
   */
  <E extends Entity> ITable<E> getStoredTableByEntityType(Class<E> entityType);

  /**
   * Inserts the given entity into the current {@link DataAdapter}.
   * 
   * @param entity
   * @return the current {@link DataAdapter}
   * @throws RuntimeException if the given entity is null
   * @throws RuntimeException if the current {@link DataAdapter} cannot insert the
   *                          given entity
   */
  DataAdapter insertEntity(Entity entity);
}
