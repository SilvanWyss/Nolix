package ch.nolix.system.objectdata.model;

import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.systemapi.midschema.fieldproperty.BaseFieldType;
import ch.nolix.systemapi.midschema.model.ColumnDto;
import ch.nolix.systemapi.midschema.model.TableDto;
import ch.nolix.systemapi.objectdata.model.IEntity;
import ch.nolix.systemapi.objectdata.model.ITable;

public final class TableLoader {
  public static LinkedList<Table<IEntity>> loadTablesForDatabase(final Database database) {
    final var midTables = database.getStoredMidDataAdapterAndSchemaReader().loadTables();

    final var tables = midTables
      .to(rt -> TableMapper.mapMidSchemaTableDtoToTableWithoutColumns(rt, database));

    addBaseValueColumnsToTablesFromMidTables(tables, midTables);
    addBaseReferenceColumnsToTablesFromMidTables(tables, midTables, tables);
    addBaseBackReferenceColumnsToTablesFromMidTables(tables, midTables, tables);

    return LinkedList.fromIterable(tables);
  }

  private static void addBaseValueColumnsToTablesFromMidTables(
    final IContainer<Table<IEntity>> tables,
    final IContainer<TableDto> midTables) {
    for (final var t : tables) {
      final var tableName = t.getName();
      final var midTable = midTables.getStoredFirst(rt -> rt.name().equals(tableName));
      addBaseValueColumnsToTableFromMidTable(t, midTable);
    }
  }

  private static void addBaseValueColumnsToTableFromMidTable(
    final Table<IEntity> table,
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
    final IContainer<Table<IEntity>> tables,
    final IContainer<TableDto> midTables,
    final IContainer<? extends ITable<IEntity>> referencableTables) {
    for (final var t : tables) {
      final var tableName = t.getName();
      final var midTable = midTables.getStoredFirst(rt -> rt.name().equals(tableName));
      addBaseReferenceColumnsToTableFromMidTable(t, midTable, referencableTables);
    }
  }

  private static void addBaseReferenceColumnsToTableFromMidTable(
    final Table<IEntity> table,
    final TableDto midTable,
    final IContainer<? extends ITable<IEntity>> referencableTables) {
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
    final IContainer<Table<IEntity>> tables,
    final IContainer<TableDto> midTables,
    final IContainer<? extends ITable<IEntity>> referencableTables) {
    for (final var t : tables) {
      final var tableName = t.getName();
      final var midTable = midTables.getStoredFirst(rt -> rt.name().equals(tableName));
      addBaseBackReferenceColumnsToTableFromMidTable(t, midTable, referencableTables);
    }
  }

  private static void addBaseBackReferenceColumnsToTableFromMidTable(
    final Table<IEntity> table,
    final TableDto midTable,
    final IContainer<? extends ITable<IEntity>> referencableTables) {
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

  private TableLoader() {
  }
}
