/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.objectdata.model;

import ch.nolix.baseapi.attribute.mandatoryattribute.INameHolder;
import ch.nolix.baseapi.component.datamodelcomponent.IDatabaseComponent;
import ch.nolix.baseapi.component.datamodelcomponent.IEntityComponent;
import ch.nolix.baseapi.component.datamodelcomponent.ITableComponent;
import ch.nolix.baseapi.container.base.IContainer;
import ch.nolix.baseapi.datamodel.fieldrequest.MandatorynessRequestable;
import ch.nolix.baseapi.state.staterequest.EmptinessRequestable;
import ch.nolix.systemapi.databaseobject.model.IDatabaseObject;
import ch.nolix.systemapi.midschema.fieldproperty.FieldType;

/**
 * @author Silvan Wyss
 */
public interface IField
extends
EmptinessRequestable,
IDatabaseComponent<IDatabase>,
IDatabaseObject,
IEntityComponent<IEntity>,
INameHolder,
ITableComponent<ITable<? extends IEntity>>,
MandatorynessRequestable {
  IContainer<IBaseBackReference> getStoredBaseBackReferencesWhoReferencesBackThis();

  IContainer<IBaseReference> getStoredBackReferencedBaseReferences();

  IColumn getStoredParentColumn();

  FieldType getType();

  void internalSetNullableValue(Object nullableValue, String nullableAdditionalValue);

  boolean knowsParentColumn();

  boolean referencesBackEntity(IEntity entity);

  boolean referencesBackEntityWithId(String id);

  boolean referencesBackField(IField field);

  boolean referencesEntity(IEntity entity);

  boolean referencesUninsertedEntity();

  void setUpdateAction(Runnable updateAction);
}
