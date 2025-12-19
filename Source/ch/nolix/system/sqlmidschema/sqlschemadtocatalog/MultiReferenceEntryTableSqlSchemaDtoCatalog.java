package ch.nolix.system.sqlmidschema.sqlschemadtocatalog;

import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.systemapi.sqlmidschema.databasestructure.FixTable;
import ch.nolix.systemapi.sqlmidschema.databasestructure.MultiReferenceEntryColumn;
import ch.nolix.systemapi.sqlmidschema.datatype.DataTypeTypeCatalog;
import ch.nolix.systemapi.sqlschema.model.ColumnDto;
import ch.nolix.systemapi.sqlschema.model.TableDto;

/**
 * @author Silvan Wyss
 */
public final class MultiReferenceEntryTableSqlSchemaDtoCatalog {
  private static final ColumnDto ENTITY_COLUMN_SQL_DTO = //
  new ColumnDto(
    MultiReferenceEntryColumn.ENTITY_ID.getName(),
    DataTypeTypeCatalog.TEXT,
    ImmutableList.createEmpty());

  private static final ColumnDto ENTITY_TABLE_COLUMN_SQL_DTO = //
  new ColumnDto(
    MultiReferenceEntryColumn.ENTITY_TABLE_ID.getName(),
    DataTypeTypeCatalog.TEXT,
    ImmutableList.createEmpty());

  private static final ColumnDto MULTI_REFERENCE_COLUMN_COLUMN_SQL_DTO = //
  new ColumnDto(
    MultiReferenceEntryColumn.MULTI_REFERENCE_COLUMN_ID.getName(),
    DataTypeTypeCatalog.TEXT,
    ImmutableList.createEmpty());

  private static final ColumnDto REFERENCED_ENTITY_COLUMN_SQL_DTO = //
  new ColumnDto(
    MultiReferenceEntryColumn.REFERENCED_ENTITY_ID.getName(),
    DataTypeTypeCatalog.TEXT,
    ImmutableList.createEmpty());

  private static final ColumnDto REFERENCED_ENTITY_TABLE_COLUMN_SQL_DTO = //
  new ColumnDto(
    MultiReferenceEntryColumn.REFERENCED_ENTITY_TABLE_ID.getName(),
    DataTypeTypeCatalog.TEXT,
    ImmutableList.createEmpty());

  public static final TableDto MULTI_REFERENCE_ENTRY_TABLE_SQL_DTO = //
  new TableDto(
    FixTable.MULTI_REFERENCE_ENTRY.getName(),
    ImmutableList.withElements(
      ENTITY_COLUMN_SQL_DTO,
      ENTITY_TABLE_COLUMN_SQL_DTO,
      MULTI_REFERENCE_COLUMN_COLUMN_SQL_DTO,
      REFERENCED_ENTITY_COLUMN_SQL_DTO,
      REFERENCED_ENTITY_TABLE_COLUMN_SQL_DTO));

  private MultiReferenceEntryTableSqlSchemaDtoCatalog() {
  }
}
