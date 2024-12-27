package ch.nolix.system.objectdata.model;

import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.system.objectdata.datatool.EntityCreator;
import ch.nolix.system.objectdata.schemamapper.ColumnMapper;
import ch.nolix.systemapi.objectdataapi.fieldproperty.BaseContentType;
import ch.nolix.systemapi.objectdataapi.modelapi.IEntity;
import ch.nolix.systemapi.objectdataapi.schemaapi.ISchema;
import ch.nolix.systemapi.objectdataapi.schemamapperapi.IColumnMapper;
import ch.nolix.systemapi.objectdataapi.schemamapperapi.ITableMapper;
import ch.nolix.systemapi.objectschemaapi.modelapi.ITable;
import ch.nolix.systemapi.objectschemaapi.schemaadapterapi.ISchemaAdapter;

public final class SchemaInitializer {

  private static final ITableMapper TABLE_MAPPER = new ch.nolix.system.objectdata.schemamapper.TableMapper();

  private static final EntityCreator ENTITY_CREATOR = new EntityCreator();

  private static final IColumnMapper COLUMN_MAPPER = new ColumnMapper();

  public void initializeDatabaseFromSchemaUsingSchemaAdapterIfDatabaseIsEmpty(
    final ISchema schema,
    final ISchemaAdapter schemaAdapter) {
    if (databaseIsEmpty(schemaAdapter)) {
      initializeDatabaseToGivenSchemaUsingGivenSchemaAdapter(schema, schemaAdapter);
    }
  }

  private boolean databaseIsEmpty(final ISchemaAdapter schemaAdapter) {
    return (schemaAdapter.getTableCount() == 0);
  }

  private void initializeDatabaseToGivenSchemaUsingGivenSchemaAdapter(
    final ISchema schema,
    final ISchemaAdapter schemaAdapter) {

    final var tables = TABLE_MAPPER.mapSchemaToEmptyTables(schema);

    tables.forEach(schemaAdapter::addTable);

    addBaseValueColumnsToTablesFromSchema(tables, schema);
    addBaseReferenceColumnsToTablesFromSchema(tables, schema, tables);
    addBaseBackReferenceColumnsToTablesFromSchema(tables, schema, tables);

    schemaAdapter.saveChanges();
  }

  private void addBaseValueColumnsToTablesFromSchema(
    final IContainer<ITable> tables,
    final ISchema schema) {
    for (final var t : tables) {
      final var entityType = schema.getEntityTypeByName(t.getName());
      addBaseValueColumnsToTableFromEntityType(t, entityType);
    }
  }

  private void addBaseValueColumnsToTableFromEntityType(
    final ITable table,
    final Class<? extends IEntity> entityType) {

    final var entity = ENTITY_CREATOR.createEmptyEntityForEntityType(entityType);

    final var baseValues = entity
      .internalGetStoredFields()
      .getStoredSelected(p -> p.getType().getBaseType() == BaseContentType.BASE_VALUE);

    for (final var bv : baseValues) {

      final var column = COLUMN_MAPPER.mapFieldToColumn(bv, ImmutableList.createEmpty());

      table.addColumn(column);
    }
  }

  private void addBaseReferenceColumnsToTablesFromSchema(
    final IContainer<ITable> tables,
    final ISchema schema,
    final IContainer<ITable> referencableTables) {
    for (final var t : tables) {
      final var entityType = schema.getEntityTypeByName(t.getName());
      addBaseReferenceColumnsToTableFromEntityType(t, entityType, referencableTables);
    }
  }

  private void addBaseReferenceColumnsToTableFromEntityType(
    final ITable table,
    final Class<? extends IEntity> entityType,
    final IContainer<ITable> referencableTables) {

    final var entity = ENTITY_CREATOR.createEmptyEntityForEntityType(entityType);

    final var baseReferences = entity
      .internalGetStoredFields()
      .getStoredSelected(p -> p.getType().getBaseType() == BaseContentType.BASE_REFERENCE);

    for (final var br : baseReferences) {

      final var column = COLUMN_MAPPER.mapFieldToColumn(br, referencableTables);

      table.addColumn(column);
    }
  }

  private void addBaseBackReferenceColumnsToTablesFromSchema(
    final IContainer<ITable> tables,
    final ISchema schema,
    final IContainer<ITable> referencableTables) {
    for (final var t : tables) {
      final var entityType = schema.getEntityTypeByName(t.getName());
      addBaseBackReferenceColumnsToTableFromEntityType(t, entityType, referencableTables);
    }
  }

  private void addBaseBackReferenceColumnsToTableFromEntityType(
    final ITable table,
    final Class<? extends IEntity> entityType,
    final IContainer<ITable> referencableTables) {

    final var entity = ENTITY_CREATOR.createEmptyEntityForEntityType(entityType);

    final var baseBackReferences = entity
      .internalGetStoredFields()
      .getStoredSelected(p -> p.getType().getBaseType() == BaseContentType.BASE_BACK_REFERENCE);

    for (final var bbr : baseBackReferences) {

      final var column = COLUMN_MAPPER.mapFieldToColumn(bbr, referencableTables);

      table.addColumn(column);
    }
  }
}
