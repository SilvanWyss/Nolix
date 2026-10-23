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
  /**
   * @return the name of the parent {@link ITable} of the current {@link Entity}
   * @throws RuntimeException if the current {@link Entity} does not belong to a
   *                          {@link ITable}
   */
  String getParentTableName();

  /**
   * @return a short description of the current {@link Entity}
   */
  String getShortDescription();

  /**
   * Closes the current {@link Entity}.
   */
  void internalClose();

  /**
   * @return the {@link Field}s of the current {@link Entity}
   */
  ExtendedIterable<? extends Field> internalGetStoredFields();

  /**
   * Sets the current {@link Entity} as edited.
   *
   * @throws RuntimeException if the current {@link Entity} is deleted or closed
   */
  void internalSetEdited();

  /**
   * Sets the current {@link Entity} as loaded and sets the given loadedId and
   * saveStamp to the current {@link Entity}.
   *
   * @param loadedId
   * @param saveStamp
   * @throws RuntimeException if the current {@link Entity} is not new
   * @throws RuntimeException if the given loadedId is null or blank
   * @throws RuntimeException if the given saveStamp is null or blank
   */
  void internalSetLoadedAndIdAndSaveStamp(String loadedId, String saveStamp);

  /**
   * Sets the given parentTable to the current {@link Entity}.
   *
   * @param parentTable
   * @throws RuntimeException if the current {@link Entity} cannot set the given
   *                          parentTable
   */
  void internalSetParentTable(ITable<? extends Entity> parentTable);

  /**
   * @return true if the current {@link Entity} is referenced in the persisted
   *         data, false otherwise
   */
  boolean isReferencedInPersistedData();

  /**
   * @param entitiesToIgnoreIds
   * @return true if the current {@link Entity} is referenced in the persisted
   *         data, ignoring the {@link Entity}s with the given
   *         entitiesToIgnoreIds, false otherwise
   */
  boolean isReferencedInPersistedDataIgnoringGivenEntities(ExtendedIterable<String> entitiesToIgnoreIds);
}
