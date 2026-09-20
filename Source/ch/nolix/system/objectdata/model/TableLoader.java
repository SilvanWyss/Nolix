/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.objectdata.model;

import ch.nolix.base.datastructure.immutablelist.ImmutableList;
import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.systemapi.midschema.fieldproperty.BaseFieldType;
import ch.nolix.systemapi.midschema.model.ColumnDto;
import ch.nolix.systemapi.midschema.model.TableDto;
import ch.nolix.systemapi.objectdata.model.Entity;
import ch.nolix.systemapi.objectdata.model.ITable;

/**
 * @author Silvan Wyss
 */
public final class TableLoader {
  private TableLoader() {
  }

  public static ImmutableList<Table<Entity>> loadTablesForDatabase(final Database database) {
    final var midTables = database.getStoredMidDataAdapterAndSchemaReader().loadTables();
    final var tables = midTables.to(t -> TableMapper.mapMidSchemaTableDtoToTableWithoutColumns(t, database));

    addBaseValueColumnsToTablesFromMidTables(tables, midTables);
    addBaseReferenceColumnsToTablesFromMidTables(tables, midTables, tables);
    addBaseBackReferenceColumnsToTablesFromMidTables(tables, midTables, tables);

    return ImmutableList.fromIterable(tables);
  }

  private static void addBaseValueColumnsToTablesFromMidTables(
    final ExtendedIterable<Table<Entity>> tables,
    final ExtendedIterable<TableDto> midTables) {
    for (final var t : tables) {
      final var tableName = t.getName();
      final var midTable = midTables.getStoredFirst(rt -> rt.name().equals(tableName));
      addBaseValueColumnsToTableFromMidTable(t, midTable);
    }
  }

  private static void addBaseValueColumnsToTableFromMidTable(
    final Table<Entity> table,
    final TableDto midTable) {
    final var midBaseValueColumnsView = midTable.columns().getViewOfStoredSelected(TableLoader::isBaseValue);

    for (final var c : midBaseValueColumnsView) {
      final var column = ColumnMapper.mapMidSchemaColumnDtoToColumn(
        c,
        table,
        ImmutableList.createEmpty());

      table.internalAddColumn(column);
    }
  }

  private static void addBaseReferenceColumnsToTablesFromMidTables(
    final ExtendedIterable<Table<Entity>> tables,
    final ExtendedIterable<TableDto> midTables,
    final ExtendedIterable<? extends ITable<Entity>> referencableTables) {
    for (final var t : tables) {
      final var tableName = t.getName();
      final var midTable = midTables.getStoredFirst(rt -> rt.name().equals(tableName));
      addBaseReferenceColumnsToTableFromMidTable(t, midTable, referencableTables);
    }
  }

  private static void addBaseReferenceColumnsToTableFromMidTable(
    final Table<Entity> table,
    final TableDto midTable,
    final ExtendedIterable<? extends ITable<Entity>> referencableTables) {
    final var midBaseReferenceColumnsView = midTable.columns().getViewOfStoredSelected(TableLoader::isBaseReference);

    for (final var c : midBaseReferenceColumnsView) {
      final var column = ColumnMapper.mapMidSchemaColumnDtoToColumn(
        c,
        table,
        referencableTables);

      table.internalAddColumn(column);
    }
  }

  private static void addBaseBackReferenceColumnsToTablesFromMidTables(
    final ExtendedIterable<Table<Entity>> tables,
    final ExtendedIterable<TableDto> midTables,
    final ExtendedIterable<? extends ITable<Entity>> referencableTables) {
    for (final var t : tables) {
      final var tableName = t.getName();
      final var midTable = midTables.getStoredFirst(rt -> rt.name().equals(tableName));
      addBaseBackReferenceColumnsToTableFromMidTable(t, midTable, referencableTables);
    }
  }

  private static void addBaseBackReferenceColumnsToTableFromMidTable(
    final Table<Entity> table,
    final TableDto midTable,
    final ExtendedIterable<? extends ITable<Entity>> referencableTables) {
    final var midBaseValueColumnsView = midTable.columns().getViewOfStoredSelected(TableLoader::isBaseBackReference);

    for (final var c : midBaseValueColumnsView) {
      final var column = ColumnMapper.mapMidSchemaColumnDtoToColumn(
        c,
        table,
        referencableTables);

      table.internalAddColumn(column);
    }
  }

  private static boolean isBaseBackReference(final ColumnDto columnDto) {
    return columnDto.fieldType().getBaseType() == BaseFieldType.BASE_BACK_REFERENCE;
  }

  private static boolean isBaseReference(final ColumnDto columnDto) {
    return columnDto.fieldType().getBaseType() == BaseFieldType.BASE_REFERENCE;
  }

  private static boolean isBaseValue(final ColumnDto columnDto) {
    return columnDto.fieldType().getBaseType() == BaseFieldType.BASE_VALUE_FIELD;
  }
}
