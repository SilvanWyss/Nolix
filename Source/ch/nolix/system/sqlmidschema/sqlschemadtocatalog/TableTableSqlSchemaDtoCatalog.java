package ch.nolix.system.sqlmidschema.sqlschemadtocatalog;

import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.systemapi.sqlmidschemaapi.databasestructure.FixTableType;
import ch.nolix.systemapi.sqlmidschemaapi.databasestructure.TableTableColumn;
import ch.nolix.systemapi.sqlmidschemaapi.datatypeapi.DataTypeTypeCatalog;
import ch.nolix.systemapi.sqlschemaapi.modelapi.ColumnDto;
import ch.nolix.systemapi.sqlschemaapi.modelapi.TableDto;

public final class TableTableSqlSchemaDtoCatalog {

  private static final ColumnDto ID_COLUMN_SQL_DTO = //
  new ColumnDto(
    TableTableColumn.ID.getName(),
    DataTypeTypeCatalog.TEXT,
    ImmutableList.createEmpty());

  private static final ColumnDto NAME_COLUMN_SQL_DTO = //
  new ColumnDto(TableTableColumn.NAME.getName(),
    DataTypeTypeCatalog.TEXT,
    ImmutableList.createEmpty());

  public static final TableDto TABLE_TABLE_SQL_DTO = //
  new TableDto(FixTableType.TABLE.getName(), ImmutableList.withElement(ID_COLUMN_SQL_DTO, NAME_COLUMN_SQL_DTO));

  private TableTableSqlSchemaDtoCatalog() {
  }
}
