/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.objectdata.adapter;

import ch.nolix.baseapi.attribute.mandatoryattribute.DatabaseNameHolder;
import ch.nolix.baseapi.programcontrol.copy.EmptyCopyable;
import ch.nolix.baseapi.resourcecontrol.savecontrol.IResettableChangeSaver;
import ch.nolix.systemapi.objectdata.model.IEntity;
import ch.nolix.systemapi.objectdata.model.ITable;

/**
 * @author Silvan Wyss
 */
public interface IDataAdapter extends EmptyCopyable<IDataAdapter>, DatabaseNameHolder, IResettableChangeSaver {
  <E extends IEntity> E getStoredEntityByTypeAndId(Class<E> type, String id);

  <E extends IEntity> ITable<E> getStoredTableByEntityType(Class<E> entityType);

  IDataAdapter insertEntity(IEntity entity);
}
