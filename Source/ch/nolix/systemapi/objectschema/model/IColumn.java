/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.objectschema.model;

import ch.nolix.baseapi.attribute.fluentmutablemandatoryattribute.FluentMutableNameHolder;
import ch.nolix.baseapi.attribute.mandatoryattribute.IdHolder;
import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.baseapi.generalstate.staterequest.EmptinessRequestable;
import ch.nolix.baseapi.objectcomposition.datamodelcomponent.DatabaseComponent;
import ch.nolix.baseapi.objectcomposition.datamodelcomponent.TableComponent;
import ch.nolix.systemapi.database.databaseobject.DatabaseObject;
import ch.nolix.systemapi.database.databaseobject.Deletable;
import ch.nolix.systemapi.database.databaseproperty.DataType;
import ch.nolix.systemapi.midschema.fieldproperty.FieldType;

/**
 * @author Silvan Wyss
 */
public interface IColumn
extends
Deletable,
EmptinessRequestable,
DatabaseComponent<IDatabase>,
DatabaseObject,
FluentMutableNameHolder<IColumn>,
IdHolder,
TableComponent<ITable> {
  /**
   * @return the {@link DataType} of the current {@link IColumn}
   */
  DataType getDataType();

  /**
   * @return the {@link FieldType} of the current {@link IColumn}
   */
  FieldType getFieldType();

  /**
   * @return the {@link IColumn}s the current {@link IColumn} can reference back
   */
  ExtendedIterable<? extends IColumn> getStoredBackReferenceableColumns();

  /**
   * @return the {@link ITable}s the current {@link IColumn} can reference
   */
  ExtendedIterable<? extends ITable> getStoredReferenceableTables();

  /**
   * @return true if the current {@link IColumn} is referenced back, false
   *         otherwise
   */
  boolean isBackReferenced();

  /**
   * @param column
   * @return true if the current {@link IColumn} references back the given column,
   *         false otherwise
   */
  boolean referencesBackColumn(IColumn column);

  /**
   * @param table
   * @return true if the current {@link IColumn} references the given table, false
   *         otherwise
   */
  boolean referencesTable(ITable table);

  /**
   * Sets the given fieldType, dataType, referenceableTables and
   * backReferenceableColumns as content model to the current {@link IColumn}.
   * 
   * @param fieldType
   * @param dataType
   * @param referenceableTables
   * @param backReferenceableColumns
   * @return the current {@link IColumn}
   */
  IColumn setContentModel(
    FieldType fieldType,
    DataType dataType,
    ExtendedIterable<? extends ITable> referenceableTables,
    ExtendedIterable<? extends IColumn> backReferenceableColumns);
}
