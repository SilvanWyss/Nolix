/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.sqlmidschema.sqlschemadtocatalog;

import ch.nolix.base.container.immutablelist.ImmutableList;
import ch.nolix.systemapi.sqlmiddatabasestructure.column.TableColumn;
import ch.nolix.systemapi.sqlmiddatabasestructure.table.SchemaTable;
import ch.nolix.systemapi.sqlmidschema.datatype.DataTypeTypeCatalog;
import ch.nolix.systemapi.sqlschema.model.ColumnDto;
import ch.nolix.systemapi.sqlschema.model.TableDto;

/**
 * @author Silvan Wyss
 */
public final class TableTableSqlSchemaDtoCatalog {
  private static final ColumnDto ID_COLUMN_SQL_DTO = //
  new ColumnDto(TableColumn.ID.toString(), DataTypeTypeCatalog.TEXT, ImmutableList.createEmpty());

  private static final ColumnDto NAME_COLUMN_SQL_DTO = //
  new ColumnDto(TableColumn.NAME.toString(), DataTypeTypeCatalog.TEXT, ImmutableList.createEmpty());

  public static final TableDto TABLE_TABLE_SQL_DTO = //
  new TableDto(SchemaTable.TABLE.toString(), ImmutableList.withElements(ID_COLUMN_SQL_DTO, NAME_COLUMN_SQL_DTO));

  private TableTableSqlSchemaDtoCatalog() {
  }
}
