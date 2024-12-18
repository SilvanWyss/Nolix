package ch.nolix.systemapi.objectschemaapi.schemaapi;

import ch.nolix.coreapi.attributeapi.fluentmutablemandatoryattributeapi.IFluentMutableNameHolder;
import ch.nolix.coreapi.attributeapi.mandatoryattributeapi.IIdHolder;
import ch.nolix.coreapi.componentapi.datamodelcomponentapi.IDatabaseComponent;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.systemapi.databaseobjectapi.databaseobjectapi.Deletable;
import ch.nolix.systemapi.databaseobjectapi.databaseobjectapi.IDatabaseObject;
import ch.nolix.systemapi.rawschemaapi.flatschemadto.FlatTableDto;
import ch.nolix.systemapi.rawschemaapi.schemadto.TableDto;

public interface ITable
extends
Deletable,
IDatabaseComponent<IDatabase>,
IDatabaseObject,
IFluentMutableNameHolder<ITable>,
IIdHolder {

  ITable addColumn(IColumn column);

  ITable createColumnWithNameAndParameterizedFieldType(
    String name,
    IContentModel contentModel);

  FlatTableDto getFlatDto();

  IContainer<IColumn> getStoredColumns();

  TableDto toDto();
}
