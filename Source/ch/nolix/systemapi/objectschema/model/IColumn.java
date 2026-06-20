/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.objectschema.model;

import ch.nolix.baseapi.attribute.fluentmutablemandatoryattribute.FluentMutableNameHolder;
import ch.nolix.baseapi.attribute.mandatoryattribute.IdHolder;
import ch.nolix.baseapi.component.datamodelcomponent.DatabaseComponent;
import ch.nolix.baseapi.component.datamodelcomponent.TableComponent;
import ch.nolix.baseapi.container.wellordercontainer.IWellOrderContainer;
import ch.nolix.baseapi.datamodel.fieldproperty.DataType;
import ch.nolix.baseapi.state.staterequest.EmptinessRequestable;
import ch.nolix.systemapi.databaseobject.model.Deletable;
import ch.nolix.systemapi.databaseobject.model.IDatabaseObject;
import ch.nolix.systemapi.midschema.fieldproperty.FieldType;

/**
 * @author Silvan Wyss
 */
public interface IColumn
extends
Deletable,
EmptinessRequestable,
DatabaseComponent<IDatabase>,
IDatabaseObject,
FluentMutableNameHolder<IColumn>,
IdHolder,
TableComponent<ITable> {
  DataType getDataType();

  FieldType getFieldType();

  IWellOrderContainer<? extends IColumn> getStoredBackReferenceableColumns();

  IWellOrderContainer<? extends ITable> getStoredReferenceableTables();

  boolean isBackReferenced();

  boolean referencesBackColumn(IColumn column);

  boolean referencesTable(ITable table);

  IColumn setContentModel(
    FieldType fieldType,
    DataType dataType,
    IWellOrderContainer<? extends ITable> referenceableTables,
    IWellOrderContainer<? extends IColumn> backReferenceableColumns);
}
