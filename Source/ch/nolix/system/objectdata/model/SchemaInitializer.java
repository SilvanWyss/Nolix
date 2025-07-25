package ch.nolix.system.objectdata.model;

import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.system.objectdata.datatool.EntityCreator;
import ch.nolix.system.objectdata.schemamapper.ColumnMapper;
import ch.nolix.system.objectdata.schemasearcher.SchemaSearcher;
import ch.nolix.systemapi.midschemaapi.fieldproperty.BaseContentType;
import ch.nolix.systemapi.objectdataapi.modelapi.IEntity;
import ch.nolix.systemapi.objectdataapi.modelapi.IEntityTypeSet;
import ch.nolix.systemapi.objectdataapi.schemamapperapi.IColumnMapper;
import ch.nolix.systemapi.objectdataapi.schemamapperapi.ITableMapper;
import ch.nolix.systemapi.objectdataapi.schemamodelsearcherapi.ISchemaSearcher;
import ch.nolix.systemapi.objectschemaapi.modelapi.ITable;
import ch.nolix.systemapi.objectschemaapi.schemaadapterapi.ISchemaAdapter;

public final class SchemaInitializer {

  private static final ISchemaSearcher SCHEMA_SEARCHER = new SchemaSearcher();

  private static final ITableMapper TABLE_MAPPER = new ch.nolix.system.objectdata.schemamapper.TableMapper();

  private static final EntityCreator ENTITY_CREATOR = new EntityCreator();

  private static final IColumnMapper COLUMN_MAPPER = new ColumnMapper();

  public void initializeDatabaseFromSchemaUsingSchemaAdapterIfDatabaseIsEmpty(
    final IEntityTypeSet entityTypeSet,
    final ISchemaAdapter schemaAdapter) {
    if (databaseIsEmpty(schemaAdapter)) {
      initializeDatabaseToGivenSchemaUsingGivenSchemaAdapter(entityTypeSet, schemaAdapter);
    }
  }

  private boolean databaseIsEmpty(final ISchemaAdapter schemaAdapter) {
    return (schemaAdapter.getTableCount() == 0);
  }

  private void initializeDatabaseToGivenSchemaUsingGivenSchemaAdapter(
    final IEntityTypeSet entityTypeSet,
    final ISchemaAdapter schemaAdapter) {

    final var tables = TABLE_MAPPER.mapSchemaToEmptyTables(entityTypeSet);

    tables.forEach(schemaAdapter::addTable);

    addBaseValueColumnsToTablesFromSchema(tables, entityTypeSet);
    addBaseReferenceColumnsToTablesFromSchema(tables, entityTypeSet, tables);
    addBaseBackReferenceColumnsToTablesFromSchema(tables, entityTypeSet, tables);

    schemaAdapter.saveChanges();
  }

  private void addBaseValueColumnsToTablesFromSchema(
    final IContainer<ITable> tables,
    final IEntityTypeSet entityTypeSet) {
    for (final var t : tables) {
      final var entityType = SCHEMA_SEARCHER.getEntityTypeByName(entityTypeSet, t.getName());
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
    final IEntityTypeSet entityTypeSet,
    final IContainer<ITable> referencableTables) {
    for (final var t : tables) {
      final var entityType = SCHEMA_SEARCHER.getEntityTypeByName(entityTypeSet, t.getName());
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
    final IEntityTypeSet entityTypeSet,
    final IContainer<ITable> referencableTables) {
    for (final var t : tables) {
      final var entityType = SCHEMA_SEARCHER.getEntityTypeByName(entityTypeSet, t.getName());
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
