//package declaration
package ch.nolix.system.objectdatabase.database;

//own imports
import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.systemapi.databaseapi.propertytypeapi.BasePropertyType;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IEntity;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.ITable;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.ITableDto;

//class
final class DatabaseTableLoader {

  //constant
  private static final TableMapper TABLE_MAPPER = new TableMapper();

  //constant
  private static final ColumnMapper COLUMN_MAPPER = new ColumnMapper();

  //method
  public LinkedList<Table<IEntity>> loadTablesForDatabase(final Database database) {

    final var rawTables = database.internalGetRefDataAndSchemaAdapter().loadTables();

    final var tables = rawTables.to(rt -> TABLE_MAPPER.createEmptyTableFromTableDtoForDatabase(rt, database));

    addBaseValueColumnsToTablesFromRawTables(tables, rawTables);
    addBaseReferenceColumnsToTablesFromRawTables(tables, rawTables, tables);
    addBaseBackReferenceColumnsToTablesFromRawTables(tables, rawTables, tables);

    return LinkedList.fromIterable(tables);
  }

  //method
  private void addBaseValueColumnsToTablesFromRawTables(
    final IContainer<Table<IEntity>> tables,
    final IContainer<ITableDto> rawTables) {
    for (final var t : tables) {
      final var tableName = t.getName();
      final var rawTable = rawTables.getStoredFirst(rt -> rt.getName().equals(tableName));
      addBaseValueColumnsToTableFromRawTable(t, rawTable);
    }
  }

  //method
  private void addBaseValueColumnsToTableFromRawTable(
    final Table<IEntity> table,
    final ITableDto rawTable) {

    final var rawBaseValueColumns = rawTable
      .getColumns()
      .getStoredSelected(
        c -> c.getParameterizedPropertyType().getPropertyType().getBaseType() == BasePropertyType.BASE_VALUE);

    for (final var c : rawBaseValueColumns) {

      final var column = COLUMN_MAPPER.createColumnFromDtoForParentTableUsingGivenReferencableTables(
        c,
        table,
        new ImmutableList<>());

      table.internalAddColumn(column);
    }
  }

  //method
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

  //method
  private void addBaseReferenceColumnsToTableFromRawTable(
    final Table<IEntity> table,
    final ITableDto rawTable,
    final IContainer<? extends ITable<IEntity>> referencableTables) {

    final var rawBaseReferenceColumns = rawTable
      .getColumns()
      .getStoredSelected(
        c -> c.getParameterizedPropertyType().getPropertyType().getBaseType() == BasePropertyType.BASE_REFERENCE);

    for (final var c : rawBaseReferenceColumns) {

      final var column = COLUMN_MAPPER.createColumnFromDtoForParentTableUsingGivenReferencableTables(
        c,
        table,
        referencableTables);

      table.internalAddColumn(column);
    }
  }

  //method
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

  //method
  private void addBaseBackReferenceColumnsToTableFromRawTable(
    final Table<IEntity> table,
    final ITableDto rawTable,
    final IContainer<? extends ITable<IEntity>> referencableTables) {

    final var rawBaseValueColumns = rawTable
      .getColumns()
      .getStoredSelected(
        c -> c.getParameterizedPropertyType().getPropertyType()
          .getBaseType() == BasePropertyType.BASE_BACK_REFERENCE);

    for (final var c : rawBaseValueColumns) {

      final var column = COLUMN_MAPPER.createColumnFromDtoForParentTableUsingGivenReferencableTables(
        c,
        table,
        referencableTables);

      table.internalAddColumn(column);
    }
  }
}
