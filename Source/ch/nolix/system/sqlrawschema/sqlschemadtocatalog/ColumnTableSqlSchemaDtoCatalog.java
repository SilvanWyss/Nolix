package ch.nolix.system.sqlrawschema.sqlschemadtocatalog;

import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.systemapi.sqlrawschemaapi.databasestructure.ColumnTableColumn;
import ch.nolix.systemapi.sqlrawschemaapi.databasestructure.FixTableType;
import ch.nolix.systemapi.sqlrawschemaapi.databasestructure.TableTableColumn;
import ch.nolix.systemapi.sqlrawschemaapi.datatypeapi.DataTypeTypeCatalog;
import ch.nolix.systemapi.sqlschemaapi.modelapi.ColumnDto;
import ch.nolix.systemapi.sqlschemaapi.modelapi.TableDto;

public final class ColumnTableSqlSchemaDtoCatalog {

  private static final ColumnDto ID_SQL_DTO = //
  new ColumnDto(TableTableColumn.ID.getName(), DataTypeTypeCatalog.TEXT, ImmutableList.createEmpty());

  private static final ColumnDto PARENT_TABLE_ID_SQL_DTO = //
  new ColumnDto(
    ColumnTableColumn.PARENT_TABLE_ID.getName(),
    DataTypeTypeCatalog.TEXT,
    ImmutableList.createEmpty());

  private static final ColumnDto NAME_SQL_DTO = //
  new ColumnDto(ColumnTableColumn.NAME.getName(), DataTypeTypeCatalog.TEXT, ImmutableList.createEmpty());

  private static final ColumnDto PROPERTY_TYPE_SQL_DTO = //
  new ColumnDto(ColumnTableColumn.CONTENT_TYPE.getName(), DataTypeTypeCatalog.TEXT, ImmutableList.createEmpty());

  private static final ColumnDto DATA_TYPE_SQL_DTO = //
  new ColumnDto(ColumnTableColumn.DATA_TYPE.getName(), DataTypeTypeCatalog.TEXT, ImmutableList.createEmpty());

  private static final ColumnDto BACK_REFERENCED_COLUMN_ID_SQL_DTO = //
  new ColumnDto(
    ColumnTableColumn.BACK_REFERENCED_COLUM_ID.getName(),
    DataTypeTypeCatalog.TEXT,
    ImmutableList.createEmpty());

  public static final TableDto COLUMN_TABLE_SQL_DTO = //
  new TableDto(
    FixTableType.COLUMN.getQualifiedName(),
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
