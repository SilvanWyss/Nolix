package ch.nolix.systemapi.sqlrawschemaapi.sqlschemadtocatalog;

import ch.nolix.systemapi.sqlrawschemaapi.databasestructure.MultiEntryTableType;
import ch.nolix.systemapi.sqlrawschemaapi.databasestructure.MultiReferenceEntryTableColumn;
import ch.nolix.systemapi.sqlrawschemaapi.databasestructure.MultiValueEntryTableColumn;
import ch.nolix.systemapi.sqlrawschemaapi.datatypeapi.DataTypeTypeCatalog;
import ch.nolix.systemapi.sqlschemaapi.dto.ColumnDto;
import ch.nolix.systemapi.sqlschemaapi.dto.TableDto;

public final class MultiValueEntryTableSqlSchemaDtoCatalog {

  private static final ColumnDto MULTI_VALUE_COLUMN_COLUMN_SQL_DTO = ColumnDto.withNameAndDataType(
    MultiValueEntryTableColumn.MULTI_VALUE_COLUMN_ID.getName(),
    DataTypeTypeCatalog.TEXT);

  private static final ColumnDto ENTITY_COLUMN_SQL_DTO = ColumnDto.withNameAndDataType(
    MultiReferenceEntryTableColumn.ENTITY_ID.getName(), DataTypeTypeCatalog.TEXT);

  private static final ColumnDto VALUE_COLUMN_SQL_DTO = ColumnDto.withNameAndDataType(
    MultiValueEntryTableColumn.VALUE.getName(),
    DataTypeTypeCatalog.TEXT);

  public static final TableDto MULTI_VALUE_ENTRY_TABLE_SQL_DTO = TableDto.withNameAndColumn(
    MultiEntryTableType.MULTI_VALUE_ENTRY.getQualifiedName(),
    MULTI_VALUE_COLUMN_COLUMN_SQL_DTO,
    ENTITY_COLUMN_SQL_DTO,
    VALUE_COLUMN_SQL_DTO);

  private MultiValueEntryTableSqlSchemaDtoCatalog() {
  }
}
