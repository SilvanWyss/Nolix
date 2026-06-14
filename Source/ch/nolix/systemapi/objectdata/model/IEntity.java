/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.objectdata.model;

import ch.nolix.baseapi.attribute.mandatoryattribute.IIdHolder;
import ch.nolix.baseapi.attribute.mandatoryattribute.IShortDescriptionHolder;
import ch.nolix.baseapi.attribute.optionalattribute.IOptionalSaveStampHolder;
import ch.nolix.baseapi.component.datamodelcomponent.IDatabaseComponent;
import ch.nolix.baseapi.component.datamodelcomponent.ITableComponent;
import ch.nolix.baseapi.container.wellordercontainer.IWellOrderContainer;
import ch.nolix.systemapi.databaseobject.model.Deletable;
import ch.nolix.systemapi.databaseobject.model.IDatabaseObject;

/**
 * @author Silvan Wyss
 */
public interface IEntity
extends
Deletable,
IDatabaseComponent<IDatabase>,
IDatabaseObject,
IIdHolder,
IOptionalSaveStampHolder,
IShortDescriptionHolder,
ITableComponent<ITable<? extends IEntity>> {
  String getParentTableName();

  IWellOrderContainer<? extends IField> internalGetStoredFields();

  void internalSetLoadedAndIdAndSaveStamp(String loadedId, String saveStamp);

  void internalSetParentTable(ITable<? extends IEntity> parentTable);

  boolean isReferencedInPersistedData();

  boolean isReferencedInPersistedDataIgnoringGivenEntities(IWellOrderContainer<String> entitiesToIgnoreIds);
}
