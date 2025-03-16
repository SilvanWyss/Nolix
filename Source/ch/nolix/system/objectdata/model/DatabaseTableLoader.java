package ch.nolix.system.objectdata.model;

import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.systemapi.midschemaapi.modelapi.ColumnDto;
import ch.nolix.systemapi.midschemaapi.modelapi.TableDto;
import ch.nolix.systemapi.objectdataapi.modelapi.IEntity;
import ch.nolix.systemapi.objectdataapi.modelapi.ITable;
import ch.nolix.systemapi.objectschemaapi.fieldproperty.BaseContentType;

final class DatabaseTableLoader {

  private static final TableMapper TABLE_MAPPER = new TableMapper();

  private static final ColumnMapper COLUMN_MAPPER = new ColumnMapper();

  public LinkedList<Table<IEntity>> loadTablesForDatabase(final Database database) {

    final var rawTables = database.getStoredRawDataAdapterAndSchemaReader().loadTables();

    final var tables = rawTables
      .to(rt -> TABLE_MAPPER.mapTableDtoToTableWithoutColumnsAndWithoutEntities(rt, database));

    addBaseValueColumnsToTablesFromRawTables(tables, rawTables);
    addBaseReferenceColumnsToTablesFromRawTables(tables, rawTables, tables);
    addBaseBackReferenceColumnsToTablesFromRawTables(tables, rawTables, tables);

    return LinkedList.fromIterable(tables);
  }

  private void addBaseValueColumnsToTablesFromRawTables(
    final IContainer<Table<IEntity>> tables,
    final IContainer<TableDto> rawTables) {
    for (final var t : tables) {
      final var tableName = t.getName();
      final var rawTable = rawTables.getStoredFirst(rt -> rt.name().equals(tableName));
      addBaseValueColumnsToTableFromRawTable(t, rawTable);
    }
  }

  private void addBaseValueColumnsToTableFromRawTable(
    final Table<IEntity> table,
    final TableDto rawTable) {

    final var rawBaseValueColumns = rawTable.columns().getStoredSelected(this::isBaseValue);

    for (final var c : rawBaseValueColumns) {

      final var column = COLUMN_MAPPER.mapRawSchemaColumnDtoToColumnView(
        c,
        table,
        ImmutableList.createEmpty());

      table.internalAddColumn(column);
    }
  }

  private void addBaseReferenceColumnsToTablesFromRawTables(
    final IContainer<Table<IEntity>> tables,
    final IContainer<TableDto> rawTables,
    final IContainer<? extends ITable<IEntity>> referencableTables) {
    for (final var t : tables) {
      final var tableName = t.getName();
      final var rawTable = rawTables.getStoredFirst(rt -> rt.name().equals(tableName));
      addBaseReferenceColumnsToTableFromRawTable(t, rawTable, referencableTables);
    }
  }

  private void addBaseReferenceColumnsToTableFromRawTable(
    final Table<IEntity> table,
    final TableDto rawTable,
    final IContainer<? extends ITable<IEntity>> referencableTables) {

    final var rawBaseReferenceColumns = rawTable.columns().getStoredSelected(this::isBaseReference);

    for (final var c : rawBaseReferenceColumns) {

      final var column = COLUMN_MAPPER.mapRawSchemaColumnDtoToColumnView(
        c,
        table,
        referencableTables);

      table.internalAddColumn(column);
    }
  }

  private void addBaseBackReferenceColumnsToTablesFromRawTables(
    final IContainer<Table<IEntity>> tables,
    final IContainer<TableDto> rawTables,
    final IContainer<? extends ITable<IEntity>> referencableTables) {
    for (final var t : tables) {
      final var tableName = t.getName();
      final var rawTable = rawTables.getStoredFirst(rt -> rt.name().equals(tableName));
      addBaseBackReferenceColumnsToTableFromRawTable(t, rawTable, referencableTables);
    }
  }

  private void addBaseBackReferenceColumnsToTableFromRawTable(
    final Table<IEntity> table,
    final TableDto rawTable,
    final IContainer<? extends ITable<IEntity>> referencableTables) {

    final var rawBaseValueColumns = rawTable.columns().getStoredSelected(this::isBaseBackReference);

    for (final var c : rawBaseValueColumns) {

      final var column = COLUMN_MAPPER.mapRawSchemaColumnDtoToColumnView(
        c,
        table,
        referencableTables);

      table.internalAddColumn(column);
    }
  }

  private boolean isBaseBackReference(final ColumnDto columnDto) {
    return columnDto.contentModel().getContentType().getBaseType() == BaseContentType.BASE_BACK_REFERENCE;
  }

  private boolean isBaseReference(final ColumnDto columnDto) {
    return columnDto.contentModel().getContentType().getBaseType() == BaseContentType.BASE_REFERENCE;
  }

  private boolean isBaseValue(final ColumnDto columnDto) {
    return columnDto.contentModel().getContentType().getBaseType() == BaseContentType.BASE_VALUE;
  }

}
