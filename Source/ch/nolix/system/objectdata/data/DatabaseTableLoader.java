package ch.nolix.system.objectdata.data;

import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.systemapi.objectdataapi.dataapi.IEntity;
import ch.nolix.systemapi.objectdataapi.dataapi.ITable;
import ch.nolix.systemapi.rawschemaapi.schemadto.BackReferenceModelDto;
import ch.nolix.systemapi.rawschemaapi.schemadto.MultiBackReferenceModelDto;
import ch.nolix.systemapi.rawschemaapi.schemadto.MultiReferenceModelDto;
import ch.nolix.systemapi.rawschemaapi.schemadto.MultiValueModelDto;
import ch.nolix.systemapi.rawschemaapi.schemadto.OptionalBackReferenceModelDto;
import ch.nolix.systemapi.rawschemaapi.schemadto.OptionalReferenceModelDto;
import ch.nolix.systemapi.rawschemaapi.schemadto.OptionalValueModelDto;
import ch.nolix.systemapi.rawschemaapi.schemadto.ReferenceModelDto;
import ch.nolix.systemapi.rawschemaapi.schemadto.ValueModelDto;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.IColumnDto;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.ITableDto;

final class DatabaseTableLoader {

  private static final TableMapper TABLE_MAPPER = new TableMapper();

  private static final ColumnMapper COLUMN_MAPPER = new ColumnMapper();

  public LinkedList<Table<IEntity>> loadTablesForDatabase(final Database database) {

    final var rawTables = database.internalGetStoredDataAndSchemaAdapter().loadTables();

    final var tables = rawTables.to(rt -> TABLE_MAPPER.createEmptyTableFromTableDtoForDatabase(rt, database));

    addBaseValueColumnsToTablesFromRawTables(tables, rawTables);
    addBaseReferenceColumnsToTablesFromRawTables(tables, rawTables, tables);
    addBaseBackReferenceColumnsToTablesFromRawTables(tables, rawTables, tables);

    return LinkedList.fromIterable(tables);
  }

  private void addBaseValueColumnsToTablesFromRawTables(
    final IContainer<Table<IEntity>> tables,
    final IContainer<ITableDto> rawTables) {
    for (final var t : tables) {
      final var tableName = t.getName();
      final var rawTable = rawTables.getStoredFirst(rt -> rt.getName().equals(tableName));
      addBaseValueColumnsToTableFromRawTable(t, rawTable);
    }
  }

  private void addBaseValueColumnsToTableFromRawTable(
    final Table<IEntity> table,
    final ITableDto rawTable) {

    final var rawBaseValueColumns = rawTable.getColumns().getStoredSelected(this::isBaseValue);

    for (final var c : rawBaseValueColumns) {

      final var column = COLUMN_MAPPER.createColumnFromDtoForParentTableUsingGivenReferencableTables(
        c,
        table,
        ImmutableList.createEmpty());

      table.internalAddColumn(column);
    }
  }

  private void addBaseReferenceColumnsToTablesFromRawTables(
    final IContainer<Table<IEntity>> tables,
    final IContainer<ITableDto> rawTables,
    final IContainer<? extends ITable<IEntity>> referencableTables) {
    for (final var t : tables) {
      final var tableName = t.getName();
      final var rawTable = rawTables.getStoredFirst(rt -> rt.getName().equals(tableName));
      addBaseReferenceColumnsToTableFromRawTable(t, rawTable, referencableTables);
    }
  }

  private void addBaseReferenceColumnsToTableFromRawTable(
    final Table<IEntity> table,
    final ITableDto rawTable,
    final IContainer<? extends ITable<IEntity>> referencableTables) {

    final var rawBaseReferenceColumns = rawTable.getColumns().getStoredSelected(this::isBaseReference);

    for (final var c : rawBaseReferenceColumns) {

      final var column = COLUMN_MAPPER.createColumnFromDtoForParentTableUsingGivenReferencableTables(
        c,
        table,
        referencableTables);

      table.internalAddColumn(column);
    }
  }

  private void addBaseBackReferenceColumnsToTablesFromRawTables(
    final IContainer<Table<IEntity>> tables,
    final IContainer<ITableDto> rawTables,
    final IContainer<? extends ITable<IEntity>> referencableTables) {
    for (final var t : tables) {
      final var tableName = t.getName();
      final var rawTable = rawTables.getStoredFirst(rt -> rt.getName().equals(tableName));
      addBaseBackReferenceColumnsToTableFromRawTable(t, rawTable, referencableTables);
    }
  }

  private void addBaseBackReferenceColumnsToTableFromRawTable(
    final Table<IEntity> table,
    final ITableDto rawTable,
    final IContainer<? extends ITable<IEntity>> referencableTables) {

    final var rawBaseValueColumns = rawTable.getColumns().getStoredSelected(this::isBaseBackReference);

    for (final var c : rawBaseValueColumns) {

      final var column = COLUMN_MAPPER.createColumnFromDtoForParentTableUsingGivenReferencableTables(
        c,
        table,
        referencableTables);

      table.internalAddColumn(column);
    }
  }

  private boolean isBaseValue(IColumnDto columnDto) {

    final var contentModel = columnDto.getParameterizedFieldType();

    return //
    contentModel instanceof ValueModelDto
    || contentModel instanceof OptionalValueModelDto
    || contentModel instanceof MultiValueModelDto;
  }

  private boolean isBaseReference(IColumnDto columnDto) {

    final var contentModel = columnDto.getParameterizedFieldType();

    return //
    contentModel instanceof ReferenceModelDto
    || contentModel instanceof OptionalReferenceModelDto
    || contentModel instanceof MultiReferenceModelDto;
  }

  private boolean isBaseBackReference(IColumnDto columnDto) {

    final var contentModel = columnDto.getParameterizedFieldType();

    return //
    contentModel instanceof BackReferenceModelDto
    || contentModel instanceof OptionalBackReferenceModelDto
    || contentModel instanceof MultiBackReferenceModelDto;
  }
}
