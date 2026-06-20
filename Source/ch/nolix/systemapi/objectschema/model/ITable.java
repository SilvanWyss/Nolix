/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.objectschema.model;

import ch.nolix.baseapi.attribute.fluentmutablemandatoryattribute.FluentMutableNameHolder;
import ch.nolix.baseapi.attribute.mandatoryattribute.IdHolder;
import ch.nolix.baseapi.component.datamodelcomponent.DatabaseComponent;
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
DatabaseComponent<IDatabase>,
IDatabaseObject,
FluentMutableNameHolder<ITable>,
IdHolder {
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
