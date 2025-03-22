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

    final var midTables = database.getStoredMidDataAdapterAndSchemaReader().loadTables();

    final var tables = midTables
      .to(rt -> TABLE_MAPPER.mapTableDtoToTableWithoutColumnsAndWithoutEntities(rt, database));

    addBaseValueColumnsToTablesFromMidTables(tables, midTables);
    addBaseReferenceColumnsToTablesFromMidTables(tables, midTables, tables);
    addBaseBackReferenceColumnsToTablesFromMidTables(tables, midTables, tables);

    return LinkedList.fromIterable(tables);
  }

  private void addBaseValueColumnsToTablesFromMidTables(
    final IContainer<Table<IEntity>> tables,
    final IContainer<TableDto> midTables) {
    for (final var t : tables) {
      final var tableName = t.getName();
      final var midTable = midTables.getStoredFirst(rt -> rt.name().equals(tableName));
      addBaseValueColumnsToTableFromMidTable(t, midTable);
    }
  }

  private void addBaseValueColumnsToTableFromMidTable(
    final Table<IEntity> table,
    final TableDto midTable) {

    final var midBaseValueColumns = midTable.columns().getStoredSelected(this::isBaseValue);

    for (final var c : midBaseValueColumns) {

      final var column = COLUMN_MAPPER.mapMidSchemaColumnDtoToColumnView(
        c,
        table,
        ImmutableList.createEmpty());

      table.internalAddColumn(column);
    }
  }

  private void addBaseReferenceColumnsToTablesFromMidTables(
    final IContainer<Table<IEntity>> tables,
    final IContainer<TableDto> midTables,
    final IContainer<? extends ITable<IEntity>> referencableTables) {
    for (final var t : tables) {
      final var tableName = t.getName();
      final var midTable = midTables.getStoredFirst(rt -> rt.name().equals(tableName));
      addBaseReferenceColumnsToTableFromMidTable(t, midTable, referencableTables);
    }
  }

  private void addBaseReferenceColumnsToTableFromMidTable(
    final Table<IEntity> table,
    final TableDto midTable,
    final IContainer<? extends ITable<IEntity>> referencableTables) {

    final var midBaseReferenceColumns = midTable.columns().getStoredSelected(this::isBaseReference);

    for (final var c : midBaseReferenceColumns) {

      final var column = COLUMN_MAPPER.mapMidSchemaColumnDtoToColumnView(
        c,
        table,
        referencableTables);

      table.internalAddColumn(column);
    }
  }

  private void addBaseBackReferenceColumnsToTablesFromMidTables(
    final IContainer<Table<IEntity>> tables,
    final IContainer<TableDto> midTables,
    final IContainer<? extends ITable<IEntity>> referencableTables) {
    for (final var t : tables) {
      final var tableName = t.getName();
      final var midTable = midTables.getStoredFirst(rt -> rt.name().equals(tableName));
      addBaseBackReferenceColumnsToTableFromMidTable(t, midTable, referencableTables);
    }
  }

  private void addBaseBackReferenceColumnsToTableFromMidTable(
    final Table<IEntity> table,
    final TableDto midTable,
    final IContainer<? extends ITable<IEntity>> referencableTables) {

    final var midBaseValueColumns = midTable.columns().getStoredSelected(this::isBaseBackReference);

    for (final var c : midBaseValueColumns) {

      final var column = COLUMN_MAPPER.mapMidSchemaColumnDtoToColumnView(
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
