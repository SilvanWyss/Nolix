/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.objectdata.model;

import ch.nolix.baseapi.attribute.mandatoryattribute.IIdHolder;
import ch.nolix.baseapi.attribute.mandatoryattribute.INameHolder;
import ch.nolix.baseapi.container.base.IContainer;
import ch.nolix.systemapi.databaseobject.model.IDatabaseObject;
import ch.nolix.systemapi.midschema.fieldproperty.FieldType;

/**
 * @author Silvan Wyss
 */
public interface IColumn extends IDatabaseObject, IIdHolder, INameHolder {
  boolean containsValueInPersistedData(String value);

  boolean containsValueInPersistedDataIgnoringEntities(String value, IContainer<String> entitiesToIgnoreIds);

  Class<?> getDataTypeClass();

  FieldType getFieldType();

  IContainer<? extends IColumn> getStoredBackReferenceableColumns();

  ITable<IEntity> getStoredParentTable();

  IContainer<? extends ITable<IEntity>> getStoredReferenceableTables();
}
