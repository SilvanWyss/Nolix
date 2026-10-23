/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.objectschema.model;

import ch.nolix.baseapi.attribute.fluentmutablemandatoryattribute.FluentMutableNameHolder;
import ch.nolix.baseapi.attribute.mandatoryattribute.IdHolder;
import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.baseapi.objectcomposition.datamodelcomponent.DatabaseComponent;
import ch.nolix.systemapi.database.databaseobject.DatabaseObject;
import ch.nolix.systemapi.database.databaseobject.Deletable;
import ch.nolix.systemapi.database.databaseproperty.DataType;
import ch.nolix.systemapi.midschema.fieldproperty.FieldType;

/**
 * @author Silvan Wyss
 */
public interface ITable
extends
Deletable,
DatabaseComponent<IDatabase>,
DatabaseObject,
FluentMutableNameHolder<ITable>,
IdHolder {
  /**
   * Adds the given column to the current {@link ITable}.
   * 
   * @param column
   * @return the current {@link ITable}
   */
  ITable addColumn(IColumn column);

  /**
   * Adds the given columns to the current {@link ITable}.
   * 
   * @param columns
   * @return the current {@link ITable}
   */
  ITable addColumns(ExtendedIterable<IColumn> columns);

  /**
   * Adds a new {@link IColumn} with the given name and the given fieldType,
   * dataType, referenceableTables and backReferenceableColumns as content model
   * to the current {@link ITable}.
   * 
   * @param name
   * @param fieldType
   * @param dataType
   * @param referenceableTables
   * @param backReferenceableColumns
   * @return the current {@link ITable}
   */
  ITable addColumnWithNameAndContentModel(
    String name,
    FieldType fieldType,
    DataType dataType,
    ExtendedIterable<? extends ITable> referenceableTables,
    ExtendedIterable<? extends IColumn> backReferenceableColumns);

  /**
   * @return the number of {@link IColumn}s of the current {@link ITable}
   */
  int getColumnCount();

  /**
   * @return the {@link IColumn}s of the current {@link ITable}
   */
  ExtendedIterable<? extends IColumn> getStoredColumns();
}
