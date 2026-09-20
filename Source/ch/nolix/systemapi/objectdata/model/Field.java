/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.objectdata.model;

import ch.nolix.baseapi.attribute.mandatoryattribute.NameHolder;
import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.baseapi.generalstate.staterequest.EmptinessRequestable;
import ch.nolix.baseapi.objectcomposition.datamodelcomponent.DatabaseComponent;
import ch.nolix.baseapi.objectcomposition.datamodelcomponent.EntityComponent;
import ch.nolix.baseapi.objectcomposition.datamodelcomponent.TableComponent;
import ch.nolix.systemapi.database.databaseobject.DatabaseObject;
import ch.nolix.systemapi.database.databaserequest.MandatorynessRequestable;
import ch.nolix.systemapi.midschema.fieldproperty.FieldType;

/**
 * @author Silvan Wyss
 */
public interface Field
extends
EmptinessRequestable,
DatabaseComponent<IDatabase>,
DatabaseObject,
EntityComponent<Entity>,
NameHolder,
TableComponent<ITable<? extends Entity>>,
MandatorynessRequestable {
  ExtendedIterable<BaseBackReference> getStoredBaseBackReferencesWhoReferencesBackThis();

  ExtendedIterable<BaseReference> getStoredBackReferencedBaseReferences();

  IColumn getStoredParentColumn();

  FieldType getType();

  void internalSetNullableValue(Object nullableValue, String nullableAdditionalValue);

  void internalSetParentColumn(IColumn parentColumn);

  void internalSetParentEntity(Entity parentEntity);

  boolean knowsParentColumn();

  boolean referencesBackEntity(Entity entity);

  boolean referencesBackEntityWithId(String id);

  boolean referencesBackField(Field field);

  boolean referencesEntity(Entity entity);

  boolean referencesUninsertedEntity();

  void setUpdateAction(Runnable updateAction);
}
