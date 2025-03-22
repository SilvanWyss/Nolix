package ch.nolix.system.sqlmidschema.sqlschemadtocatalog;

import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.systemapi.sqlmidschemaapi.databasestructure.FixTable;
import ch.nolix.systemapi.sqlmidschemaapi.databasestructure.MultiReferenceEntryTableColumn;
import ch.nolix.systemapi.sqlmidschemaapi.datatypeapi.DataTypeTypeCatalog;
import ch.nolix.systemapi.sqlschemaapi.modelapi.ColumnDto;
import ch.nolix.systemapi.sqlschemaapi.modelapi.TableDto;

public final class MultiReferenceEntryTableSqlSchemaDtoCatalog {

  private static final ColumnDto MULTI_REFERENCE_COLUMN_COLUMN_SQL_DTO = //
  new ColumnDto(
    MultiReferenceEntryTableColumn.MULTI_REFERENCE_COLUMN_ID.getName(),
    DataTypeTypeCatalog.TEXT,
    ImmutableList.createEmpty());

  private static final ColumnDto ENTITY_COLUMN_SQL_DTO = //
  new ColumnDto(
    MultiReferenceEntryTableColumn.ENTITY_ID.getName(),
    DataTypeTypeCatalog.TEXT,
    ImmutableList.createEmpty());

  private static final ColumnDto REFERENCED_ENTITY_COLUMN_SQL_DTO = //
  new ColumnDto(
    MultiReferenceEntryTableColumn.REFERENCED_ENTITY_ID.getName(),
    DataTypeTypeCatalog.TEXT,
    ImmutableList.createEmpty());

  private static final ColumnDto REFERENCED_ENTITY_TABLE_COLUMN_SQL_DTO = //
  new ColumnDto(
    MultiReferenceEntryTableColumn.REFERENCED_ENTITY_TABLE_ID.getName(),
    DataTypeTypeCatalog.TEXT,
    ImmutableList.createEmpty());

  public static final TableDto MULTI_REFERENCE_ENTRY_TABLE_SQL_DTO = //
  new TableDto(
    FixTable.MULTI_REFERENCE_ENTRY.getName(),
    ImmutableList.withElement(
      MULTI_REFERENCE_COLUMN_COLUMN_SQL_DTO,
      ENTITY_COLUMN_SQL_DTO,
      REFERENCED_ENTITY_COLUMN_SQL_DTO,
      REFERENCED_ENTITY_TABLE_COLUMN_SQL_DTO));

  private MultiReferenceEntryTableSqlSchemaDtoCatalog() {
  }
}
