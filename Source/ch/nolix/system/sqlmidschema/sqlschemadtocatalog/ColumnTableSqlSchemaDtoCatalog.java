package ch.nolix.system.sqlmidschema.sqlschemadtocatalog;

import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.systemapi.sqlmidschema.databasestructure.ColumnColumn;
import ch.nolix.systemapi.sqlmidschema.databasestructure.FixTable;
import ch.nolix.systemapi.sqlmidschema.databasestructure.TableColumn;
import ch.nolix.systemapi.sqlmidschema.datatype.DataTypeTypeCatalog;
import ch.nolix.systemapi.sqlschema.model.ColumnDto;
import ch.nolix.systemapi.sqlschema.model.TableDto;

public final class ColumnTableSqlSchemaDtoCatalog {

  private static final ColumnDto ID_SQL_DTO = //
  new ColumnDto(TableColumn.ID.getName(), DataTypeTypeCatalog.TEXT, ImmutableList.createEmpty());

  private static final ColumnDto PARENT_TABLE_ID_SQL_DTO = //
  new ColumnDto(
    ColumnColumn.PARENT_TABLE_ID.getName(),
    DataTypeTypeCatalog.TEXT,
    ImmutableList.createEmpty());

  private static final ColumnDto NAME_SQL_DTO = //
  new ColumnDto(ColumnColumn.NAME.getName(), DataTypeTypeCatalog.TEXT, ImmutableList.createEmpty());

  private static final ColumnDto CONTENT_TYPE_SQL_DTO = //
  new ColumnDto(ColumnColumn.FIELD_TYPE.getName(), DataTypeTypeCatalog.TEXT, ImmutableList.createEmpty());

  private static final ColumnDto DATA_TYPE_SQL_DTO = //
  new ColumnDto(ColumnColumn.DATA_TYPE.getName(), DataTypeTypeCatalog.TEXT, ImmutableList.createEmpty());

  private static final ColumnDto BACK_REFERENCED_COLUMN_ID_SQL_DTO = //
  new ColumnDto(
    ColumnColumn.BACK_REFERENCED_COLUM_ID.getName(),
    DataTypeTypeCatalog.TEXT,
    ImmutableList.createEmpty());

  public static final TableDto COLUMN_TABLE_SQL_DTO = //
  new TableDto(
    FixTable.COLUMN.getName(),
    ImmutableList.withElement(
      ID_SQL_DTO,
      PARENT_TABLE_ID_SQL_DTO,
      NAME_SQL_DTO,
      CONTENT_TYPE_SQL_DTO,
      DATA_TYPE_SQL_DTO,
      BACK_REFERENCED_COLUMN_ID_SQL_DTO));

  private ColumnTableSqlSchemaDtoCatalog() {
  }
}
