/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.objectdata.model;

import ch.nolix.baseapi.attribute.mandatoryattribute.IdHolder;
import ch.nolix.baseapi.attribute.optionalattribute.OptionalSaveStampHolder;
import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.baseapi.objectcomposition.datamodelcomponent.DatabaseComponent;
import ch.nolix.baseapi.objectcomposition.datamodelcomponent.TableComponent;
import ch.nolix.systemapi.database.databaseobject.DatabaseObject;
import ch.nolix.systemapi.database.databaseobject.Deletable;

/**
 * @author Silvan Wyss
 */
public interface Entity
extends
Deletable,
DatabaseComponent<IDatabase>,
DatabaseObject,
IdHolder,
OptionalSaveStampHolder,
TableComponent<ITable<? extends Entity>> {
  String getParentTableName();

  String getShortDescription();

  ExtendedIterable<? extends Field> internalGetStoredFields();

  void internalClose();

  void internalSetLoadedAndIdAndSaveStamp(String loadedId, String saveStamp);

  void internalSetParentTable(ITable<? extends Entity> parentTable);

  boolean isReferencedInPersistedData();

  boolean isReferencedInPersistedDataIgnoringGivenEntities(ExtendedIterable<String> entitiesToIgnoreIds);
}
