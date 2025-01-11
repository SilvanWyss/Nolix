package ch.nolix.systemapi.sqlrawschemaapi.sqlschemadtocatalog;

import ch.nolix.systemapi.sqlrawschemaapi.databasestructure.DatabasePropertyTableColumn;
import ch.nolix.systemapi.sqlrawschemaapi.databasestructure.MetaDataTableType;
import ch.nolix.systemapi.sqlrawschemaapi.datatypeapi.DataTypeTypeCatalog;
import ch.nolix.systemapi.sqlschemaapi.dto.ColumnDto;
import ch.nolix.systemapi.sqlschemaapi.dto.TableDto;

public final class DatabasePropertyTableSqlSchemaDtoCatalog {

  private static final ColumnDto KEY_COLUMN_SQL_DTO = ColumnDto.withNameAndDataType(
    DatabasePropertyTableColumn.KEY.getLabel(),
    DataTypeTypeCatalog.TEXT);

  private static final ColumnDto VALUE_COLUMN_SQL_DTO = ColumnDto.withNameAndDataType(
    DatabasePropertyTableColumn.VALUE.getLabel(), DataTypeTypeCatalog.TEXT);

  public static final TableDto DATABASE_PROPERTY_TABLE_SQL_DTO = TableDto.withNameAndColumn(
    MetaDataTableType.DATABASE_PROPERTY.getQualifiedName(),
    KEY_COLUMN_SQL_DTO,
    VALUE_COLUMN_SQL_DTO);

  private DatabasePropertyTableSqlSchemaDtoCatalog() {
  }
}
