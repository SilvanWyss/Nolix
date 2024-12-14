package ch.nolix.systemapi.objectschemaapi.schemaapi;

import ch.nolix.coreapi.attributeapi.fluentmutablemandatoryattributeapi.IFluentMutableNameHolder;
import ch.nolix.coreapi.attributeapi.mandatoryattributeapi.IIdHolder;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.systemapi.databaseobjectapi.databaseobjectapi.Deletable;
import ch.nolix.systemapi.databaseobjectapi.databaseobjectapi.IDatabaseObject;
import ch.nolix.systemapi.rawschemaapi.flatschemadtoapi.IFlatTableDto;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.ITableDto;

public interface ITable
extends
Deletable,
IDatabaseObject,
IFluentMutableNameHolder<ITable>,
IIdHolder {

  ITable addColumn(IColumn column);

  boolean belongsToDatabase();

  ITable createColumnWithNameAndParameterizedFieldType(
    String name,
    IParameterizedFieldType parameterizedFieldType);

  IFlatTableDto getFlatDto();

  IDatabase getStoredParentDatabase();

  IContainer<IColumn> getStoredColumns();

  ITableDto toDto();
}
