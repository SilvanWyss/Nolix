package ch.nolix.systemapi.objectdataapi.schemamapperapi;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.systemapi.objectdataapi.modelapi.IEntity;
import ch.nolix.systemapi.objectdataapi.schemaapi.ISchema;
import ch.nolix.systemapi.objectschemaapi.modelapi.ITable;

public interface ITableMapper {

  <E extends IEntity> ITable createEmptyTableFromEntityType(Class<E> entityType);

  IContainer<ITable> createEmptyTablesFromSchema(ISchema schema);

  IContainer<ITable> createTablesFromSchema(ISchema schema);
}
