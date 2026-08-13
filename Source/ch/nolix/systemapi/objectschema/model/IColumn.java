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
  DataType getDataType();

  FieldType getFieldType();

  ExtendedIterable<? extends IColumn> getStoredBackReferenceableColumns();

  ExtendedIterable<? extends ITable> getStoredReferenceableTables();

  boolean isBackReferenced();

  boolean referencesBackColumn(IColumn column);

  boolean referencesTable(ITable table);

  IColumn setContentModel(
    FieldType fieldType,
    DataType dataType,
    ExtendedIterable<? extends ITable> referenceableTables,
    ExtendedIterable<? extends IColumn> backReferenceableColumns);
}
