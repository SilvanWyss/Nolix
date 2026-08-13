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
  boolean containsValueInPersistedData(String value);

  boolean containsValueInPersistedDataIgnoringEntities(String value, ExtendedIterable<String> entitiesToIgnoreIds);

  Class<?> getDataTypeClass();

  FieldType getFieldType();

  ExtendedIterable<? extends IColumn> getStoredBackReferenceableColumns();

  ITable<IEntity> getStoredParentTable();

  ExtendedIterable<? extends ITable<IEntity>> getStoredReferenceableTables();
}
