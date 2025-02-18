package ch.nolix.systemapi.sqlrawschemaapi.sqlschemadtocatalog;

import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.systemapi.sqlrawschemaapi.databasestructure.DatabasePropertyTableColumn;
import ch.nolix.systemapi.sqlrawschemaapi.databasestructure.FixTableType;
import ch.nolix.systemapi.sqlrawschemaapi.datatypeapi.DataTypeTypeCatalog;
import ch.nolix.systemapi.sqlschemaapi.modelapi.ColumnDto;
import ch.nolix.systemapi.sqlschemaapi.modelapi.TableDto;

public final class DatabasePropertyTableSqlSchemaDtoCatalog {

  private static final ColumnDto KEY_COLUMN_SQL_DTO = //
  new ColumnDto(DatabasePropertyTableColumn.KEY.getName(), DataTypeTypeCatalog.TEXT, ImmutableList.createEmpty());

  private static final ColumnDto VALUE_COLUMN_SQL_DTO = //
  new ColumnDto(DatabasePropertyTableColumn.VALUE.getName(), DataTypeTypeCatalog.TEXT, ImmutableList.createEmpty());

  public static final TableDto DATABASE_PROPERTY_TABLE_SQL_DTO = //
  TableDto.withNameAndColumn(
    FixTableType.DATABASE_PROPERTY.getQualifiedName(),
    KEY_COLUMN_SQL_DTO,
    VALUE_COLUMN_SQL_DTO);

  private DatabasePropertyTableSqlSchemaDtoCatalog() {
  }
}
