/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.objectdata.model;

import ch.nolix.coreapi.attribute.mandatoryattribute.INameHolder;
import ch.nolix.coreapi.component.datamodelcomponent.IDatabaseComponent;
import ch.nolix.coreapi.component.datamodelcomponent.IEntityComponent;
import ch.nolix.coreapi.component.datamodelcomponent.ITableComponent;
import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.coreapi.datamodel.fieldrequest.MandatorynessRequestable;
import ch.nolix.coreapi.state.staterequest.EmptinessRequestable;
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
