package ch.nolix.system.sqlmidschema.sqlschemadtocatalog;

import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.systemapi.sqlmidschema.databasestructure.FixTable;
import ch.nolix.systemapi.sqlmidschema.databasestructure.TableColumn;
import ch.nolix.systemapi.sqlmidschema.datatype.DataTypeTypeCatalog;
import ch.nolix.systemapi.sqlschema.model.ColumnDto;
import ch.nolix.systemapi.sqlschema.model.TableDto;

public final class TableTableSqlSchemaDtoCatalog {
  private static final ColumnDto ID_COLUMN_SQL_DTO = //
  new ColumnDto(
    TableColumn.ID.getName(),
    DataTypeTypeCatalog.TEXT,
    ImmutableList.createEmpty());

  private static final ColumnDto NAME_COLUMN_SQL_DTO = //
  new ColumnDto(TableColumn.NAME.getName(),
    DataTypeTypeCatalog.TEXT,
    ImmutableList.createEmpty());

  public static final TableDto TABLE_TABLE_SQL_DTO = //
  new TableDto(FixTable.TABLE.getName(), ImmutableList.withElements(ID_COLUMN_SQL_DTO, NAME_COLUMN_SQL_DTO));

  private TableTableSqlSchemaDtoCatalog() {
  }
}
