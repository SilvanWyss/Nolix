package ch.nolix.systemapi.objectschemaapi.modelapi;

import ch.nolix.coreapi.attributeapi.fluentmutablemandatoryattributeapi.IFluentMutableNameHolder;
import ch.nolix.coreapi.attributeapi.mandatoryattributeapi.IIdHolder;
import ch.nolix.coreapi.componentapi.datamodelcomponentapi.IDatabaseComponent;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.systemapi.databaseobjectapi.modelapi.Deletable;
import ch.nolix.systemapi.databaseobjectapi.modelapi.IDatabaseObject;
import ch.nolix.systemapi.rawschemaapi.flatdto.FlatTableDto;

public interface ITable
extends
Deletable,
IDatabaseComponent<IDatabase>,
IDatabaseObject,
IFluentMutableNameHolder<ITable>,
IIdHolder {

  ITable addColumn(IColumn column);

  ITable addColumns(IContainer<IColumn> columns);

  ITable createColumnWithNameAndParameterizedFieldType(String name, IContentModel contentModel);

  FlatTableDto getFlatDto();

  IContainer<IColumn> getStoredColumns();
}
