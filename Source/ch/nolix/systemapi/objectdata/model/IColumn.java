/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.objectdata.model;

import ch.nolix.baseapi.attribute.mandatoryattribute.IdHolder;
import ch.nolix.baseapi.attribute.mandatoryattribute.NameHolder;
import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.systemapi.database.databaseobject.DatabaseObject;
import ch.nolix.systemapi.midschema.fieldproperty.FieldType;

/**
 * @author Silvan Wyss
 */
public interface IColumn extends DatabaseObject, IdHolder, NameHolder {
  /**
   * @param value
   * @return true if the current {@link IColumn} contains the given value in the
   *         persisted data, false otherwise
   */
  boolean containsValueInPersistedData(String value);

  /**
   * @param value
   * @param entitiesToIgnoreIds
   * @return true if the current {@link IColumn} contains the given value in the
   *         persisted data, ignoring the {@link Entity}s with the given
   *         entitiesToIgnoreIds, false otherwise
   */
  boolean containsValueInPersistedDataIgnoringEntities(String value, ExtendedIterable<String> entitiesToIgnoreIds);

  /**
   * @return the class of the data type of the current {@link IColumn}
   */
  Class<?> getDataTypeClass();

  /**
   * @return the {@link FieldType} of the current {@link IColumn}
   */
  FieldType getFieldType();

  /**
   * @return the {@link IColumn}s the current {@link IColumn} can reference back
   */
  ExtendedIterable<? extends IColumn> getStoredBackReferenceableColumns();

  /**
   * @return the parent {@link ITable} of the current {@link IColumn}
   */
  ITable<Entity> getStoredParentTable();

  /**
   * @return the {@link ITable}s the current {@link IColumn} can reference
   */
  ExtendedIterable<? extends ITable<Entity>> getStoredReferenceableTables();
}
