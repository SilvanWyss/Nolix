package ch.nolix.system.objectdata.model;

import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.system.objectdata.entitytool.EntityCreator;
import ch.nolix.system.objectdata.schemamapper.ColumnMapper;
import ch.nolix.system.objectdata.schemasearcher.SchemaSearcher;
import ch.nolix.systemapi.midschema.fieldproperty.BaseFieldType;
import ch.nolix.systemapi.objectdata.entitytool.IEntityCreator;
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

  private static final IEntityCreator ENTITY_CREATOR = new EntityCreator();

  private static final IColumnMapper COLUMN_MAPPER = new ColumnMapper();

  private SchemaInitializer() {
  }

  public static void initializeDatabaseIfDatabaseIsEmpty(
    final IEntityTypeSet entityTypeSet,
    final ISchemaAdapter schemaAdapter) {
    if (schemaAdapter.databaseIsEmpty()) {
      initializeDatabase(entityTypeSet, schemaAdapter);
    }
  }

  private static void initializeDatabase(
    final IEntityTypeSet entityTypeSet,
    final ISchemaAdapter schemaAdapter) {
    final var tables = TABLE_MAPPER.mapSchemaToEmptyTables(entityTypeSet);

    tables.forEach(schemaAdapter::addTable);

    addBaseValueColumnsToTables(tables, entityTypeSet);
    addBaseReferenceColumnsToTables(tables, entityTypeSet, tables);
    addBaseBackReferenceColumnsToTables(tables, entityTypeSet, tables);

    schemaAdapter.saveChanges();
  }

  private static void addBaseValueColumnsToTables(
    final IContainer<ITable> tables,
    final IEntityTypeSet entityTypeSet) {
    for (final var t : tables) {
      final var entityType = SCHEMA_SEARCHER.getEntityTypeByName(entityTypeSet, t.getName());
      addBaseValueColumnsToTable(t, entityType);
    }
  }

  private static void addBaseValueColumnsToTable(
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

  private static void addBaseReferenceColumnsToTables(
    final IContainer<ITable> tables,
    final IEntityTypeSet entityTypeSet,
    final IContainer<ITable> referencableTables) {
    for (final var t : tables) {
      final var entityType = SCHEMA_SEARCHER.getEntityTypeByName(entityTypeSet, t.getName());
      addBaseReferenceColumnsToTable(t, entityType, referencableTables);
    }
  }

  private static void addBaseReferenceColumnsToTable(
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

  private static void addBaseBackReferenceColumnsToTables(
    final IContainer<ITable> tables,
    final IEntityTypeSet entityTypeSet,
    final IContainer<ITable> referencableTables) {
    for (final var t : tables) {
      final var entityType = SCHEMA_SEARCHER.getEntityTypeByName(entityTypeSet, t.getName());
      addBaseBackReferenceColumnsToTable(t, entityType, referencableTables);
    }
  }

  private static void addBaseBackReferenceColumnsToTable(
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
