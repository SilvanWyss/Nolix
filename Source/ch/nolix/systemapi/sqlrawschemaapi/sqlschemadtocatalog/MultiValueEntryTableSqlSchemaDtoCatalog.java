package ch.nolix.systemapi.sqlrawschemaapi.sqlschemadtocatalog;

import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.systemapi.sqlrawschemaapi.databasestructure.FixTableType;
import ch.nolix.systemapi.sqlrawschemaapi.databasestructure.MultiReferenceEntryTableColumn;
import ch.nolix.systemapi.sqlrawschemaapi.databasestructure.MultiValueEntryTableColumn;
import ch.nolix.systemapi.sqlrawschemaapi.datatypeapi.DataTypeTypeCatalog;
import ch.nolix.systemapi.sqlschemaapi.modelapi.ColumnDto;
import ch.nolix.systemapi.sqlschemaapi.modelapi.TableDto;

public final class MultiValueEntryTableSqlSchemaDtoCatalog {

  private static final ColumnDto MULTI_VALUE_COLUMN_COLUMN_SQL_DTO = //
  new ColumnDto(
    MultiValueEntryTableColumn.MULTI_VALUE_COLUMN_ID.getName(),
    DataTypeTypeCatalog.TEXT,
    ImmutableList.createEmpty());

  private static final ColumnDto ENTITY_COLUMN_SQL_DTO = //
  new ColumnDto(
    MultiReferenceEntryTableColumn.ENTITY_ID.getName(),
    DataTypeTypeCatalog.TEXT,
    ImmutableList.createEmpty());

  private static final ColumnDto VALUE_COLUMN_SQL_DTO = //
  new ColumnDto(
    MultiValueEntryTableColumn.VALUE.getName(),
    DataTypeTypeCatalog.TEXT,
    ImmutableList.createEmpty());

  public static final TableDto MULTI_VALUE_ENTRY_TABLE_SQL_DTO = //
  new TableDto(
    FixTableType.MULTI_VALUE_ENTRY.getQualifiedName(),
    ImmutableList.withElement(
      MULTI_VALUE_COLUMN_COLUMN_SQL_DTO,
      ENTITY_COLUMN_SQL_DTO,
      VALUE_COLUMN_SQL_DTO));

  private MultiValueEntryTableSqlSchemaDtoCatalog() {
  }
}
