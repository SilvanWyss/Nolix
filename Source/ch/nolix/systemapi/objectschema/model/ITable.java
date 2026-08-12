/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.objectschema.model;

import ch.nolix.baseapi.attribute.fluentmutablemandatoryattribute.FluentMutableNameHolder;
import ch.nolix.baseapi.attribute.mandatoryattribute.IdHolder;
import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.baseapi.objectcomposition.datamodelcomponent.DatabaseComponent;
import ch.nolix.systemapi.database.databaseproperty.DataType;
import ch.nolix.systemapi.databaseobject.model.DatabaseObject;
import ch.nolix.systemapi.databaseobject.model.Deletable;
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
  ITable addColumn(IColumn column);

  ITable addColumns(ExtendedIterable<IColumn> columns);

  ITable addColumnWithNameAndContentModel(
    String name,
    FieldType fieldType,
    DataType dataType,
    ExtendedIterable<? extends ITable> referenceableTables,
    ExtendedIterable<? extends IColumn> backReferenceableColumns);

  int getColumnCount();

  ExtendedIterable<? extends IColumn> getStoredColumns();
}
