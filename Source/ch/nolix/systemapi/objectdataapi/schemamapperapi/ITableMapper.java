package ch.nolix.systemapi.objectdataapi.schemamapperapi;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.systemapi.objectdataapi.modelapi.IEntity;
import ch.nolix.systemapi.objectdataapi.schemaapi.ISchema;
import ch.nolix.systemapi.objectschemaapi.modelapi.ITable;

public interface ITableMapper {

  ITable mapEntityTypeToEmptyTable(Class<? extends IEntity> entityType);

  IContainer<ITable> mapSchemaToEmptyTables(ISchema schema);

  IContainer<ITable> mapSchemaToTables(ISchema schema);
}
