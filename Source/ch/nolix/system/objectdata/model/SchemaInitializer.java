package ch.nolix.system.objectdata.model;

import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.system.objectdata.entitytool.EntityCreator;
import ch.nolix.system.objectdata.schemamapper.ColumnMapper;
import ch.nolix.system.objectdata.schemasearcher.SchemaSearcher;
import ch.nolix.systemapi.midschema.fieldproperty.BaseFieldType;
import ch.nolix.systemapi.objectdata.model.IEntity;
import ch.nolix.systemapi.objectdata.model.IEntityTypeSet;
import ch.nolix.systemapi.objectdata.schemamapper.IColumnMapper;
import ch.nolix.systemapi.objectdata.schemamapper.ITableMapper;
import ch.nolix.systemapi.objectdata.schemamodelsearcher.ISchemaSearcher;
import ch.nolix.systemapi.objectschema.model.ITable;
import ch.nolix.systemapi.objectschema.schemaadapter.ISchemaAdapter;

/**
 * @author Silvan Wyss
 */
public final class SchemaInitializer {
  private static final ISchemaSearcher SCHEMA_SEARCHER = new SchemaSearcher();

  private static final ITableMapper TABLE_MAPPER = new ch.nolix.system.objectdata.schemamapper.TableMapper();

  private static final EntityCreator ENTITY_CREATOR = new EntityCreator();

  private static final IColumnMapper COLUMN_MAPPER = new ColumnMapper();

  public void initializeDatabaseFromSchemaUsingSchemaAdapterIfDatabaseIsEmpty(
    final IEntityTypeSet entityTypeSet,
    final ISchemaAdapter schemaAdapter) {
    if (schemaAdapter.databaseIsEmpty()) {
      initializeDatabaseToGivenSchemaUsingGivenSchemaAdapter(entityTypeSet, schemaAdapter);
    }
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

    final var baseValuesView = //
    entity
      .internalGetStoredFields()
      .getViewOfStoredSelected(p -> p.getType().getBaseType() == BaseFieldType.BASE_VALUE_FIELD);

    for (final var v : baseValuesView) {
      final var column = COLUMN_MAPPER.mapFieldToColumn(v, ImmutableList.createEmpty());

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

    final var baseReferencesView = //
    entity
      .internalGetStoredFields()
      .getViewOfStoredSelected(p -> p.getType().getBaseType() == BaseFieldType.BASE_REFERENCE);

    for (final var r : baseReferencesView) {
      final var column = COLUMN_MAPPER.mapFieldToColumn(r, referencableTables);

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

    final var baseBackReferencesView = //
    entity
      .internalGetStoredFields()
      .getViewOfStoredSelected(p -> p.getType().getBaseType() == BaseFieldType.BASE_BACK_REFERENCE);

    for (final var b : baseBackReferencesView) {
      final var column = COLUMN_MAPPER.mapFieldToColumn(b, referencableTables);

      table.addColumn(column);
    }
  }
}
