/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.objectdata.model;

import ch.nolix.baseapi.attribute.mandatoryattribute.IdHolder;
import ch.nolix.baseapi.attribute.mandatoryattribute.ShortDescriptionHolder;
import ch.nolix.baseapi.attribute.optionalattribute.OptionalSaveStampHolder;
import ch.nolix.baseapi.component.datamodelcomponent.DatabaseComponent;
import ch.nolix.baseapi.component.datamodelcomponent.TableComponent;
import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.systemapi.databaseobject.model.Deletable;
import ch.nolix.systemapi.databaseobject.model.IDatabaseObject;

/**
 * @author Silvan Wyss
 */
public interface IEntity
extends
Deletable,
DatabaseComponent<IDatabase>,
IDatabaseObject,
IdHolder,
OptionalSaveStampHolder,
ShortDescriptionHolder,
TableComponent<ITable<? extends IEntity>> {
  String getParentTableName();

  ExtendedIterable<? extends IField> internalGetStoredFields();

  void internalSetLoadedAndIdAndSaveStamp(String loadedId, String saveStamp);

  void internalSetParentTable(ITable<? extends IEntity> parentTable);

  boolean isReferencedInPersistedData();

  boolean isReferencedInPersistedDataIgnoringGivenEntities(ExtendedIterable<String> entitiesToIgnoreIds);
}
