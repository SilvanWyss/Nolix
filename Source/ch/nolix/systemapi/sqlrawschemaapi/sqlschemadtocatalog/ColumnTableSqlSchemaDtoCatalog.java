package ch.nolix.systemapi.sqlrawschemaapi.sqlschemadtocatalog;

import ch.nolix.systemapi.sqlrawschemaapi.databasestructure.ColumnTableColumn;
import ch.nolix.systemapi.sqlrawschemaapi.databasestructure.FixTableType;
import ch.nolix.systemapi.sqlrawschemaapi.databasestructure.TableTableColumn;
import ch.nolix.systemapi.sqlrawschemaapi.datatypeapi.DataTypeTypeCatalog;
import ch.nolix.systemapi.sqlschemaapi.modelapi.ColumnDto;
import ch.nolix.systemapi.sqlschemaapi.modelapi.TableDto;

public final class ColumnTableSqlSchemaDtoCatalog {

  private static final ColumnDto ID_SQL_DTO = ColumnDto.withNameAndDataType(TableTableColumn.ID.getName(),
    DataTypeTypeCatalog.TEXT);

  private static final ColumnDto PARENT_TABLE_ID_SQL_DTO = ColumnDto.withNameAndDataType(
    ColumnTableColumn.PARENT_TABLE_ID.getName(),
    DataTypeTypeCatalog.TEXT);

  private static final ColumnDto NAME_SQL_DTO = ColumnDto.withNameAndDataType(ColumnTableColumn.NAME.getName(),
    DataTypeTypeCatalog.TEXT);

  private static final ColumnDto PROPERTY_TYPE_SQL_DTO = ColumnDto.withNameAndDataType(
    ColumnTableColumn.FIELD_TYPE.getName(),
    DataTypeTypeCatalog.TEXT);

  private static final ColumnDto DATA_TYPE_SQL_DTO = ColumnDto.withNameAndDataType(
    ColumnTableColumn.DATA_TYPE.getName(),
    DataTypeTypeCatalog.TEXT);

  private static final ColumnDto REFERENCED_TABLE_SQL_DTO = ColumnDto.withNameAndDataType(
    ColumnTableColumn.REFERENCED_TABLE_ID.getName(), DataTypeTypeCatalog.TEXT);

  private static final ColumnDto BACK_REFERENCED_COLUMN_ID_SQL_DTO = ColumnDto.withNameAndDataType(
    ColumnTableColumn.BACK_REFERENCED_COLUM_ID.getName(), DataTypeTypeCatalog.TEXT);

  public static final TableDto COLUMN_TABLE_SQL_DTO = TableDto.withNameAndColumn(
    FixTableType.COLUMN.getQualifiedName(),
    ID_SQL_DTO,
    PARENT_TABLE_ID_SQL_DTO,
    NAME_SQL_DTO,
    PROPERTY_TYPE_SQL_DTO,
    DATA_TYPE_SQL_DTO,
    REFERENCED_TABLE_SQL_DTO,
    BACK_REFERENCED_COLUMN_ID_SQL_DTO);

  private ColumnTableSqlSchemaDtoCatalog() {
  }
}
