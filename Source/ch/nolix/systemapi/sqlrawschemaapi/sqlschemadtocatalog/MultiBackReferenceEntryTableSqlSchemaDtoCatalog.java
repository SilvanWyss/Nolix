package ch.nolix.systemapi.sqlrawschemaapi.sqlschemadtocatalog;

import ch.nolix.systemapi.sqlrawschemaapi.databasestructure.FixTableType;
import ch.nolix.systemapi.sqlrawschemaapi.databasestructure.MultiBackReferenceEntryTableColumn;
import ch.nolix.systemapi.sqlrawschemaapi.datatypeapi.DataTypeTypeCatalog;
import ch.nolix.systemapi.sqlschemaapi.modelapi.ColumnDto;
import ch.nolix.systemapi.sqlschemaapi.modelapi.TableDto;

public final class MultiBackReferenceEntryTableSqlSchemaDtoCatalog {

  private static final ColumnDto ENTITY_COLUMN_SQL_DTO = ColumnDto.withNameAndDataType(
    MultiBackReferenceEntryTableColumn.ENTITY_ID.getName(),
    DataTypeTypeCatalog.TEXT);

  private static final ColumnDto MULTI_BACK_REFERENCE_COLUMN_COLUMN_SQL_DTO = ColumnDto.withNameAndDataType(
    MultiBackReferenceEntryTableColumn.MULTI_BACK_REFERENCE_COLUMN_ID.getName(),
    DataTypeTypeCatalog.TEXT);

  private static final ColumnDto BACK_REFERENCED_ENTITY_COLUMN_SQL_DTO = ColumnDto.withNameAndDataType(
    MultiBackReferenceEntryTableColumn.BACK_REFERENCED_ENTITY_ID.getName(),
    DataTypeTypeCatalog.TEXT);

  public static final TableDto MULTI_BACK_REFERENCE_ENTRY_TABLE_SQL_DTO = TableDto.withNameAndColumn(
    FixTableType.MULTI_BACK_REFERENCE_ENTRY.getQualifiedName(),
    ENTITY_COLUMN_SQL_DTO,
    MULTI_BACK_REFERENCE_COLUMN_COLUMN_SQL_DTO,
    BACK_REFERENCED_ENTITY_COLUMN_SQL_DTO);

  private MultiBackReferenceEntryTableSqlSchemaDtoCatalog() {
  }
}
