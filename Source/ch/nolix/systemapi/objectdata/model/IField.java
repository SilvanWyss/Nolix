/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.objectdata.model;

import ch.nolix.baseapi.attribute.mandatoryattribute.NameHolder;
import ch.nolix.baseapi.component.datamodelcomponent.DatabaseComponent;
import ch.nolix.baseapi.component.datamodelcomponent.EntityComponent;
import ch.nolix.baseapi.component.datamodelcomponent.TableComponent;
import ch.nolix.baseapi.datamodel.fieldrequest.MandatorynessRequestable;
import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.baseapi.generalstate.staterequest.EmptinessRequestable;
import ch.nolix.systemapi.databaseobject.model.DatabaseObject;
import ch.nolix.systemapi.midschema.fieldproperty.FieldType;

/**
 * @author Silvan Wyss
 */
public interface IField
extends
EmptinessRequestable,
DatabaseComponent<IDatabase>,
DatabaseObject,
EntityComponent<IEntity>,
NameHolder,
TableComponent<ITable<? extends IEntity>>,
MandatorynessRequestable {
  ExtendedIterable<IBaseBackReference> getStoredBaseBackReferencesWhoReferencesBackThis();

  ExtendedIterable<IBaseReference> getStoredBackReferencedBaseReferences();

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
