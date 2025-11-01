package ch.nolix.system.sqlmidschema.sqlschemadtocatalog;

import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.systemapi.sqlmidschema.databasestructure.FixTable;
import ch.nolix.systemapi.sqlmidschema.databasestructure.MultiReferenceEntryColumn;
import ch.nolix.systemapi.sqlmidschema.databasestructure.MultiValueEntryColumn;
import ch.nolix.systemapi.sqlmidschema.datatype.DataTypeTypeCatalog;
import ch.nolix.systemapi.sqlschema.model.ColumnDto;
import ch.nolix.systemapi.sqlschema.model.TableDto;

public final class MultiValueEntryTableSqlSchemaDtoCatalog {
  private static final ColumnDto MULTI_VALUE_COLUMN_COLUMN_SQL_DTO = //
  new ColumnDto(
    MultiValueEntryColumn.MULTI_VALUE_COLUMN_ID.getName(),
    DataTypeTypeCatalog.TEXT,
    ImmutableList.createEmpty());

  private static final ColumnDto ENTITY_COLUMN_SQL_DTO = //
  new ColumnDto(
    MultiReferenceEntryColumn.ENTITY_ID.getName(),
    DataTypeTypeCatalog.TEXT,
    ImmutableList.createEmpty());

  private static final ColumnDto VALUE_COLUMN_SQL_DTO = //
  new ColumnDto(
    MultiValueEntryColumn.VALUE.getName(),
    DataTypeTypeCatalog.TEXT,
    ImmutableList.createEmpty());

  public static final TableDto MULTI_VALUE_ENTRY_TABLE_SQL_DTO = //
  new TableDto(
    FixTable.MULTI_VALUE_ENTRY.getName(),
    ImmutableList.withElements(
      MULTI_VALUE_COLUMN_COLUMN_SQL_DTO,
      ENTITY_COLUMN_SQL_DTO,
      VALUE_COLUMN_SQL_DTO));

  private MultiValueEntryTableSqlSchemaDtoCatalog() {
  }
}
