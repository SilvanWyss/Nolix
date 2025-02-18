package ch.nolix.systemapi.sqlrawschemaapi.sqlschemadtocatalog;

import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.systemapi.sqlrawschemaapi.databasestructure.FixTableType;
import ch.nolix.systemapi.sqlrawschemaapi.databasestructure.MultiBackReferenceEntryTableColumn;
import ch.nolix.systemapi.sqlrawschemaapi.datatypeapi.DataTypeTypeCatalog;
import ch.nolix.systemapi.sqlschemaapi.modelapi.ColumnDto;
import ch.nolix.systemapi.sqlschemaapi.modelapi.TableDto;

public final class MultiBackReferenceEntryTableSqlSchemaDtoCatalog {

  private static final ColumnDto ENTITY_COLUMN_SQL_DTO = //
  new ColumnDto(
    MultiBackReferenceEntryTableColumn.ENTITY_ID.getName(),
    DataTypeTypeCatalog.TEXT,
    ImmutableList.createEmpty());

  private static final ColumnDto MULTI_BACK_REFERENCE_COLUMN_COLUMN_SQL_DTO = //
  new ColumnDto(
    MultiBackReferenceEntryTableColumn.MULTI_BACK_REFERENCE_COLUMN_ID.getName(),
    DataTypeTypeCatalog.TEXT,
    ImmutableList.createEmpty());

  private static final ColumnDto BACK_REFERENCED_ENTITY_COLUMN_SQL_DTO = //
  new ColumnDto(
    MultiBackReferenceEntryTableColumn.BACK_REFERENCED_ENTITY_ID.getName(),
    DataTypeTypeCatalog.TEXT,
    ImmutableList.createEmpty());

  public static final TableDto MULTI_BACK_REFERENCE_ENTRY_TABLE_SQL_DTO = //
  new TableDto(
    FixTableType.MULTI_BACK_REFERENCE_ENTRY.getQualifiedName(),
    ImmutableList.withElement(
      ENTITY_COLUMN_SQL_DTO,
      MULTI_BACK_REFERENCE_COLUMN_COLUMN_SQL_DTO,
      BACK_REFERENCED_ENTITY_COLUMN_SQL_DTO));

  private MultiBackReferenceEntryTableSqlSchemaDtoCatalog() {
  }
}
