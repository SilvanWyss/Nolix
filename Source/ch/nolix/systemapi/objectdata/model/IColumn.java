package ch.nolix.systemapi.objectdata.model;

import ch.nolix.coreapi.attribute.mandatoryattribute.IIdHolder;
import ch.nolix.coreapi.attribute.mandatoryattribute.INameHolder;
import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.systemapi.databaseobject.model.IDatabaseObject;
import ch.nolix.systemapi.midschema.fieldproperty.FieldType;

public interface IColumn extends IDatabaseObject, IIdHolder, INameHolder {
  boolean containsValueInPersistedData(String value);

  boolean containsValueInPersistedDataIgnoringEntities(String value, IContainer<String> entitiesToIgnoreIds);

  Class<Object> getDataTypeClass();

  FieldType getFieldType();

  IContainer<? extends IColumn> getStoredBackReferenceableColumns();

  ITable<IEntity> getStoredParentTable();

  IContainer<? extends ITable<IEntity>> getStoredReferenceableTables();
}
