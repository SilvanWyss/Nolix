/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.objectdata.model;

import ch.nolix.base.datamodel.id.IdCreator;
import ch.nolix.base.datastructure.immutablelist.ImmutableList;
import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.system.objectdata.entitytool.EntityCreator;
import ch.nolix.system.objectdata.schemamapper.ColumnMapper;
import ch.nolix.system.objectdata.schemasearcher.SchemaSearcher;
import ch.nolix.systemapi.midschema.fieldproperty.BaseFieldType;
import ch.nolix.systemapi.objectdata.model.IEntity;
import ch.nolix.systemapi.objectdata.model.IEntityTypeSet;
import ch.nolix.systemapi.objectschema.model.ITable;
import ch.nolix.systemapi.objectschema.schemaadapter.SchemaAdapter;

/**
 * @author Silvan Wyss
 */
public final class SchemaInitializer {
  private static final SchemaSearcher SCHEMA_SEARCHER = new SchemaSearcher();

  private static final ch.nolix.system.objectdata.schemamapper.TableMapper TABLE_MAPPER = //
  new ch.nolix.system.objectdata.schemamapper.TableMapper();

  private static final EntityCreator ENTITY_CREATOR = new EntityCreator();

  private static final ColumnMapper COLUMN_MAPPER = new ColumnMapper();

  private SchemaInitializer() {
  }

  public static void initializeDatabaseIfDatabaseIsEmpty(
    final IEntityTypeSet entityTypeSet,
    final SchemaAdapter schemaAdapter) {
    if (schemaAdapter.databaseIsEmpty()) {
      initializeDatabase(entityTypeSet, schemaAdapter);
    }
  }

  private static void initializeDatabase(
    final IEntityTypeSet entityTypeSet,
    final SchemaAdapter schemaAdapter) {
    final var tables = TABLE_MAPPER.mapSchemaToEmptyTables(entityTypeSet);

    tables.forEach(schemaAdapter::addTable);

    addBaseValueColumnsToTables(tables, entityTypeSet);
    addBaseReferenceColumnsToTables(tables, entityTypeSet, tables);
    addBaseBackReferenceColumnsToTables(tables, entityTypeSet, tables);

    schemaAdapter.saveChanges();
  }

  private static void addBaseValueColumnsToTables(
    final ExtendedIterable<ITable> tables,
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
      final var columnId = IdCreator.createIdOf10HexadecimalCharacters();
      final var column = COLUMN_MAPPER.mapFieldToColumn(v, columnId, ImmutableList.createEmpty());

      table.addColumn(column);
    }
  }

  private static void addBaseReferenceColumnsToTables(
    final ExtendedIterable<ITable> tables,
    final IEntityTypeSet entityTypeSet,
    final ExtendedIterable<ITable> referencableTables) {
    for (final var t : tables) {
      final var entityType = SCHEMA_SEARCHER.getEntityTypeByName(entityTypeSet, t.getName());
      addBaseReferenceColumnsToTable(t, entityType, referencableTables);
    }
  }

  private static void addBaseReferenceColumnsToTable(
    final ITable table,
    final Class<? extends IEntity> entityType,
    final ExtendedIterable<ITable> referencableTables) {
    final var entity = ENTITY_CREATOR.createEmptyEntityForEntityType(entityType);

    final var baseReferencesView = //
    entity
      .internalGetStoredFields()
      .getViewOfStoredSelected(p -> p.getType().getBaseType() == BaseFieldType.BASE_REFERENCE);

    for (final var r : baseReferencesView) {
      final var columnId = IdCreator.createIdOf10HexadecimalCharacters();
      final var column = COLUMN_MAPPER.mapFieldToColumn(r, columnId, referencableTables);

      table.addColumn(column);
    }
  }

  private static void addBaseBackReferenceColumnsToTables(
    final ExtendedIterable<ITable> tables,
    final IEntityTypeSet entityTypeSet,
    final ExtendedIterable<ITable> referencableTables) {
    for (final var t : tables) {
      final var entityType = SCHEMA_SEARCHER.getEntityTypeByName(entityTypeSet, t.getName());
      addBaseBackReferenceColumnsToTable(t, entityType, referencableTables);
    }
  }

  private static void addBaseBackReferenceColumnsToTable(
    final ITable table,
    final Class<? extends IEntity> entityType,
    final ExtendedIterable<ITable> referencableTables) {
    final var entity = ENTITY_CREATOR.createEmptyEntityForEntityType(entityType);

    final var baseBackReferencesView = //
    entity
      .internalGetStoredFields()
      .getViewOfStoredSelected(p -> p.getType().getBaseType() == BaseFieldType.BASE_BACK_REFERENCE);

    for (final var b : baseBackReferencesView) {
      final var columnId = IdCreator.createIdOf10HexadecimalCharacters();
      final var column = COLUMN_MAPPER.mapFieldToColumn(b, columnId, referencableTables);

      table.addColumn(column);
    }
  }
}
