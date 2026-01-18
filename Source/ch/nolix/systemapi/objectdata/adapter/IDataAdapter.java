/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.objectdata.adapter;

import ch.nolix.coreapi.attribute.mandatoryattribute.IDatabaseNameHolder;
import ch.nolix.coreapi.objectcreation.copier.EmptyCopyable;
import ch.nolix.coreapi.resourcecontrol.savecontrol.IResettableChangeSaver;
import ch.nolix.systemapi.objectdata.model.IEntity;
import ch.nolix.systemapi.objectdata.model.ITable;

/**
 * @author Silvan Wyss
 */
public interface IDataAdapter extends EmptyCopyable<IDataAdapter>, IDatabaseNameHolder, IResettableChangeSaver {
  <E extends IEntity> ITable<E> getStoredTableByEntityType(Class<E> entityType);

  IDataAdapter insertEntity(IEntity entity);
}
