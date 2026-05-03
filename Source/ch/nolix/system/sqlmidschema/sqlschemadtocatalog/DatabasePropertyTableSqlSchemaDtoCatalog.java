/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.sqlmidschema.sqlschemadtocatalog;

import ch.nolix.base.container.immutablelist.ImmutableList;
import ch.nolix.systemapi.sqlmiddatabasestructure.column.DatabasePropertyColumn;
import ch.nolix.systemapi.sqlmiddatabasestructure.table.MetaDataTable;
import ch.nolix.systemapi.sqlmidschema.datatype.DataTypeTypeCatalog;
import ch.nolix.systemapi.sqlschema.model.ColumnDto;
import ch.nolix.systemapi.sqlschema.model.TableDto;

/**
 * @author Silvan Wyss
 */
public final class DatabasePropertyTableSqlSchemaDtoCatalog {
  private static final ColumnDto KEY_COLUMN_SQL_DTO = //
  new ColumnDto(DatabasePropertyColumn.KEY.toString(), DataTypeTypeCatalog.TEXT, ImmutableList.createEmpty());

  private static final ColumnDto VALUE_COLUMN_SQL_DTO = //
  new ColumnDto(DatabasePropertyColumn.VALUE.toString(), DataTypeTypeCatalog.TEXT, ImmutableList.createEmpty());

  public static final TableDto DATABASE_PROPERTY_TABLE_SQL_DTO = //
  new TableDto(
    MetaDataTable.DATABASE_PROPERTY.toString(),
    ImmutableList.withElements(KEY_COLUMN_SQL_DTO, VALUE_COLUMN_SQL_DTO));

  private DatabasePropertyTableSqlSchemaDtoCatalog() {
  }
}
