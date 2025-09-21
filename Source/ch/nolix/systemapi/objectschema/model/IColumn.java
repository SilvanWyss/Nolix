package ch.nolix.systemapi.objectschema.model;

import ch.nolix.coreapi.attribute.fluentmutablemandatoryattribute.IFluentMutableNameHolder;
import ch.nolix.coreapi.attribute.mandatoryattribute.IIdHolder;
import ch.nolix.coreapi.component.datamodelcomponent.IDatabaseComponent;
import ch.nolix.coreapi.component.datamodelcomponent.ITableComponent;
import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.coreapi.datamodel.fieldproperty.DataType;
import ch.nolix.coreapi.state.staterequest.EmptinessRequestable;
import ch.nolix.systemapi.databaseobject.model.Deletable;
import ch.nolix.systemapi.databaseobject.model.IDatabaseObject;
import ch.nolix.systemapi.midschema.fieldproperty.FieldType;

public interface IColumn
extends
Deletable,
EmptinessRequestable,
IDatabaseComponent<IDatabase>,
IDatabaseObject,
IFluentMutableNameHolder<IColumn>,
IIdHolder,
ITableComponent<ITable> {
  IContentModel getContentModel();

  DataType getDataType();

  FieldType getFieldType();

  IContainer<? extends ITable> getStoredReferenceableTables();

  boolean isBackReferenced();

  IColumn setContentModel(
    FieldType fieldType,
    DataType dataType,
    IContainer<? extends ITable> referenceableTables,
    IContentModel contentModel);
}
