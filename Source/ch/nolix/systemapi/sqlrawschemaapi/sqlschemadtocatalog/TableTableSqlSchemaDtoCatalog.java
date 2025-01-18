package ch.nolix.systemapi.sqlrawschemaapi.sqlschemadtocatalog;

import ch.nolix.systemapi.sqlrawschemaapi.databasestructure.FixTableType;
import ch.nolix.systemapi.sqlrawschemaapi.databasestructure.TableTableColumn;
import ch.nolix.systemapi.sqlrawschemaapi.datatypeapi.DataTypeTypeCatalog;
import ch.nolix.systemapi.sqlschemaapi.modelapi.ColumnDto;
import ch.nolix.systemapi.sqlschemaapi.modelapi.TableDto;

public final class TableTableSqlSchemaDtoCatalog {

  private static final ColumnDto ID_COLUMN_SQL_DTO = ColumnDto.withNameAndDataType(
    TableTableColumn.ID.getName(),
    DataTypeTypeCatalog.TEXT);

  private static final ColumnDto NAME_COLUMN_SQL_DTO = ColumnDto.withNameAndDataType(TableTableColumn.NAME.getName(),
    DataTypeTypeCatalog.TEXT);

  public static final TableDto TABLE_TABLE_SQL_DTO = TableDto.withNameAndColumn(
    FixTableType.TABLE.getQualifiedName(),
    ID_COLUMN_SQL_DTO, NAME_COLUMN_SQL_DTO);

  private TableTableSqlSchemaDtoCatalog() {
  }
}
