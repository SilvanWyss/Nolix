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
  /**
   * @return the {@link BaseReference}s the current {@link Field} references back
   */
  ExtendedIterable<BaseReference> getStoredBackReferencedBaseReferences();

  /**
   * @return the {@link BaseBackReference}s that reference back the current
   *         {@link Field}
   */
  ExtendedIterable<BaseBackReference> getStoredBaseBackReferencesWhoReferencesBackThis();

  /**
   * @return the parent {@link IColumn} of the current {@link Field}
   * @throws RuntimeException if the current {@link Field} does not know its
   *                          parent {@link IColumn}
   */
  IColumn getStoredParentColumn();

  /**
   * @return the {@link FieldType} of the current {@link Field}
   */
  FieldType getType();

  /**
   * Sets the given nullableValue and nullableAdditionalValue to the current
   * {@link Field}.
   *
   * @param nullableValue
   * @param nullableAdditionalValue
   */
  void internalSetNullableValue(Object nullableValue, String nullableAdditionalValue);

  /**
   * Sets the given parentColumn to the current {@link Field}.
   *
   * @param parentColumn
   * @throws RuntimeException if the given parentColumn is null
   */
  void internalSetParentColumn(IColumn parentColumn);

  /**
   * Sets the given parentEntity to the current {@link Field}.
   *
   * @param parentEntity
   * @throws RuntimeException if the given parentEntity is null
   * @throws RuntimeException if the current {@link Field} belongs already to a
   *                          {@link Entity}
   */
  void internalSetParentEntity(Entity parentEntity);

  /**
   * @return true if the current {@link Field} knows its parent {@link IColumn},
   *         false otherwise
   */
  boolean knowsParentColumn();

  /**
   * @param entity
   * @return true if the current {@link Field} references back the given entity,
   *         false otherwise
   */
  boolean referencesBackEntity(Entity entity);

  /**
   * @param id
   * @return true if the current {@link Field} references back the {@link Entity}
   *         with the given id, false otherwise
   */
  boolean referencesBackEntityWithId(String id);

  /**
   * @param field
   * @return true if the current {@link Field} references back the given field,
   *         false otherwise
   */
  boolean referencesBackField(Field field);

  /**
   * @param entity
   * @return true if the current {@link Field} references the given entity, false
   *         otherwise
   */
  boolean referencesEntity(Entity entity);

  /**
   * @return true if the current {@link Field} references a {@link Entity} that is
   *         not inserted into a {@link IDatabase}, false otherwise
   */
  boolean referencesUninsertedEntity();

  /**
   * Sets the given updateAction to the current {@link Field}. The given
   * updateAction is run when the current {@link Field} is updated.
   *
   * @param updateAction
   */
  void setUpdateAction(Runnable updateAction);
}
