package ch.nolix.system.objectdata.schemamapper;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.system.objectschema.schema.Table;
import ch.nolix.systemapi.objectdataapi.dataapi.IEntity;
import ch.nolix.systemapi.objectdataapi.schemaapi.ISchema;
import ch.nolix.systemapi.objectdataapi.schemamapperapi.IColumnMapper;
import ch.nolix.systemapi.objectdataapi.schemamapperapi.ITableMapper;
import ch.nolix.systemapi.objectschemaapi.schemaapi.ITable;

public final class TableMapper implements ITableMapper {

  private static final IColumnMapper COLUMN_MAPPER = new ColumnMapper();

  @Override
  public IContainer<ITable> createTablesFromSchema(final ISchema schema) {

    final var tables = createEmptyTablesFromSchema(schema);
    final var entitTypes = schema.getEntityTypes();

    for (final var t : tables) {

      final var entityType = entitTypes.getStoredFirst(et -> t.hasName(et.getSimpleName()));

      final var columns = //
      COLUMN_MAPPER.createColumnsFromGivenEntityTypeUsingGivenReferencableTables(entityType, tables);

      for (final var c : columns) {
        t.addColumn(c);
      }
    }

    return tables;
  }

  @Override
  public IContainer<ITable> createEmptyTablesFromSchema(final ISchema schema) {

    final var entityTypes = schema.getEntityTypes();

    return entityTypes.to(this::createEmptyTableFromEntityType);
  }

  @Override
  public <E extends IEntity> ITable createEmptyTableFromEntityType(final Class<E> entityType) {

    final var name = entityType.getSimpleName();

    return new Table(name);
  }
}
