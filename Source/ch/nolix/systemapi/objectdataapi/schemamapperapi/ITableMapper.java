package ch.nolix.systemapi.objectdataapi.schemamapperapi;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.systemapi.objectdataapi.schemaapi.ISchema;
import ch.nolix.systemapi.objectschemaapi.schemaapi.ITable;

public interface ITableMapper {

  IContainer<ITable> createEmptyTablesFromSchema(ISchema schema);

  IContainer<ITable> createTablesFrom(ISchema schema);
}
