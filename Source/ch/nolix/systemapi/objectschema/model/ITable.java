/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.objectschema.model;

import ch.nolix.baseapi.attribute.fluentmutablemandatoryattribute.IFluentMutableNameHolder;
import ch.nolix.baseapi.attribute.mandatoryattribute.IIdHolder;
import ch.nolix.baseapi.component.datamodelcomponent.IDatabaseComponent;
import ch.nolix.baseapi.container.wellordercontainer.IWellOrderContainer;
import ch.nolix.baseapi.datamodel.fieldproperty.DataType;
import ch.nolix.systemapi.databaseobject.model.Deletable;
import ch.nolix.systemapi.databaseobject.model.IDatabaseObject;
import ch.nolix.systemapi.midschema.fieldproperty.FieldType;

/**
 * @author Silvan Wyss
 */
public interface ITable
extends
Deletable,
IDatabaseComponent<IDatabase>,
IDatabaseObject,
IFluentMutableNameHolder<ITable>,
IIdHolder {
  ITable addColumn(IColumn column);

  ITable addColumns(IWellOrderContainer<IColumn> columns);

  ITable addColumnWithNameAndContentModel(
    String name,
    FieldType fieldType,
    DataType dataType,
    IWellOrderContainer<? extends ITable> referenceableTables,
    IWellOrderContainer<? extends IColumn> backReferenceableColumns);

  IWellOrderContainer<? extends IColumn> getStoredColumns();
}
