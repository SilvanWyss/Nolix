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
  <E extends Entity> E getStoredEntityByTypeAndId(Class<E> type, String id);

  <E extends Entity> ITable<E> getStoredTableByEntityType(Class<E> entityType);

  DataAdapter insertEntity(Entity entity);
}
