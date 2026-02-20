/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.objectschema.model;

import ch.nolix.baseapi.attribute.fluentmutablemandatoryattribute.IFluentMutableNameHolder;
import ch.nolix.baseapi.attribute.mandatoryattribute.IIdHolder;
import ch.nolix.baseapi.component.datamodelcomponent.IDatabaseComponent;
import ch.nolix.baseapi.container.base.IContainer;
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

  ITable addColumns(IContainer<IColumn> columns);

  ITable addColumnWithNameAndContentModel(
    String name,
    FieldType fieldType,
    DataType dataType,
    IContainer<? extends ITable> referenceableTables,
    IContainer<? extends IColumn> backReferenceableColumns);

  IContainer<? extends IColumn> getStoredColumns();
}
