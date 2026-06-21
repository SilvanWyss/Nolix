/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.sqlmidschema.sqlschemadtocatalog;

import ch.nolix.base.datastructure.immutablelist.ImmutableList;
import ch.nolix.systemapi.sqlmiddatabasestructure.column.EntityIndexColumn;
import ch.nolix.systemapi.sqlmiddatabasestructure.table.DataTable;
import ch.nolix.systemapi.sqlmidschema.datatype.DataTypeTypeCatalog;
import ch.nolix.systemapi.sqlschema.model.ColumnDto;
import ch.nolix.systemapi.sqlschema.model.TableDto;

/**
 * @author Silvan Wyss
 */
public final class EntityIndexTableSqlSchemaDtoCatalog {
  private static final ColumnDto ENTITY_ID_SQL_SCHEMA_COLUMN_DTO = //
  new ColumnDto(EntityIndexColumn.ENTITY_ID.toString(), DataTypeTypeCatalog.TEXT, ImmutableList.createEmpty());

  private static final ColumnDto TABLE_ID_SQL_SCHEMA_COLUMN_DTO = //
  new ColumnDto(EntityIndexColumn.TABLE_ID.toString(), DataTypeTypeCatalog.TEXT, ImmutableList.createEmpty());

  public static final TableDto ENTITY_INDEX_SQL_SCHEMA_TABLE_DTO = //
  new TableDto(
    DataTable.ENTITY_INDEX.toString(),
    ImmutableList.withElements(ENTITY_ID_SQL_SCHEMA_COLUMN_DTO, TABLE_ID_SQL_SCHEMA_COLUMN_DTO));

  private EntityIndexTableSqlSchemaDtoCatalog() {
  }
}
