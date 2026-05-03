/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.sqlmidschema.sqlschemadtocatalog;

import ch.nolix.base.container.immutablelist.ImmutableList;
import ch.nolix.systemapi.sqlmiddatabasestructure.column.MultiReferenceEntryColumn;
import ch.nolix.systemapi.sqlmiddatabasestructure.column.MultiValueEntryColumn;
import ch.nolix.systemapi.sqlmiddatabasestructure.table.DataTable;
import ch.nolix.systemapi.sqlmidschema.datatype.DataTypeTypeCatalog;
import ch.nolix.systemapi.sqlschema.model.ColumnDto;
import ch.nolix.systemapi.sqlschema.model.TableDto;

/**
 * @author Silvan Wyss
 */
public final class MultiValueEntryTableSqlSchemaDtoCatalog {
  private static final ColumnDto MULTI_VALUE_COLUMN_COLUMN_SQL_DTO = //
  new ColumnDto(
    MultiValueEntryColumn.MULTI_VALUE_COLUMN_ID.toString(),
    DataTypeTypeCatalog.TEXT,
    ImmutableList.createEmpty());

  private static final ColumnDto ENTITY_COLUMN_SQL_DTO = //
  new ColumnDto(MultiReferenceEntryColumn.ENTITY_ID.toString(), DataTypeTypeCatalog.TEXT, ImmutableList.createEmpty());

  private static final ColumnDto VALUE_COLUMN_SQL_DTO = //
  new ColumnDto(MultiValueEntryColumn.VALUE.toString(), DataTypeTypeCatalog.TEXT, ImmutableList.createEmpty());

  public static final TableDto MULTI_VALUE_ENTRY_TABLE_SQL_DTO = //
  new TableDto(
    DataTable.MULTI_VALUE_ENTRY.toString(),
    ImmutableList.withElements(
      MULTI_VALUE_COLUMN_COLUMN_SQL_DTO,
      ENTITY_COLUMN_SQL_DTO,
      VALUE_COLUMN_SQL_DTO));

  private MultiValueEntryTableSqlSchemaDtoCatalog() {
  }
}
