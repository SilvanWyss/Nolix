package ch.nolix.system.sqlmidschema.sqlschemadtocatalog;

import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.systemapi.sqlmidschemaapi.databasestructure.ColumnColumn;
import ch.nolix.systemapi.sqlmidschemaapi.databasestructure.FixTable;
import ch.nolix.systemapi.sqlmidschemaapi.databasestructure.TableTableColumn;
import ch.nolix.systemapi.sqlmidschemaapi.datatypeapi.DataTypeTypeCatalog;
import ch.nolix.systemapi.sqlschemaapi.modelapi.ColumnDto;
import ch.nolix.systemapi.sqlschemaapi.modelapi.TableDto;

public final class ColumnTableSqlSchemaDtoCatalog {

  private static final ColumnDto ID_SQL_DTO = //
  new ColumnDto(TableTableColumn.ID.getName(), DataTypeTypeCatalog.TEXT, ImmutableList.createEmpty());

  private static final ColumnDto PARENT_TABLE_ID_SQL_DTO = //
  new ColumnDto(
    ColumnColumn.PARENT_TABLE_ID.getName(),
    DataTypeTypeCatalog.TEXT,
    ImmutableList.createEmpty());

  private static final ColumnDto NAME_SQL_DTO = //
  new ColumnDto(ColumnColumn.NAME.getName(), DataTypeTypeCatalog.TEXT, ImmutableList.createEmpty());

  private static final ColumnDto PROPERTY_TYPE_SQL_DTO = //
  new ColumnDto(ColumnColumn.CONTENT_TYPE.getName(), DataTypeTypeCatalog.TEXT, ImmutableList.createEmpty());

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
      PROPERTY_TYPE_SQL_DTO,
      DATA_TYPE_SQL_DTO,
      BACK_REFERENCED_COLUMN_ID_SQL_DTO));

  private ColumnTableSqlSchemaDtoCatalog() {
  }
}
