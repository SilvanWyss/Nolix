package ch.nolix.systemapi.objectschemaapi.modelapi;

import ch.nolix.coreapi.attribute.fluentmutablemandatoryattribute.IFluentMutableNameHolder;
import ch.nolix.coreapi.attribute.mandatoryattribute.IIdHolder;
import ch.nolix.coreapi.component.datamodelcomponent.IDatabaseComponent;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.systemapi.databaseobjectapi.modelapi.Deletable;
import ch.nolix.systemapi.databaseobjectapi.modelapi.IDatabaseObject;

public interface ITable
extends
Deletable,
IDatabaseComponent<IDatabase>,
IDatabaseObject,
IFluentMutableNameHolder<ITable>,
IIdHolder {

  ITable addColumn(IColumn column);

  ITable addColumns(IContainer<IColumn> columns);

  ITable addColumnWithNameAndContentModel(String name, IContentModel contentModel);

  IContainer<? extends IColumn> getStoredColumns();
}
