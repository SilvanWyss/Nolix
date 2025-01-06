package ch.nolix.system.sqlrawschema.databasepropertytable;

import ch.nolix.system.sqlrawschema.datatype.DatatypeTypeCatalogue;
import ch.nolix.systemapi.sqlrawschemaapi.databasestructure.MetaDataTableType;
import ch.nolix.systemapi.sqlschemaapi.dto.ColumnDto;
import ch.nolix.systemapi.sqlschemaapi.dto.TableDto;

public final class DatabasePropertyTableSqlDtoCatalogue {

  private static final ColumnDto KEY_COLUMN_SQL_DTO = ColumnDto.withNameAndDataType(
    DatabasePropertyTableColumn.KEY.getLabel(),
    DatatypeTypeCatalogue.TEXT);

  private static final ColumnDto VALUE_COLUMN_SQL_DTO = ColumnDto.withNameAndDataType(
    DatabasePropertyTableColumn.VALUE.getLabel(), DatatypeTypeCatalogue.TEXT);

  public static final TableDto DATABASE_PROPERTY_TABLE_SQL_DTO = TableDto.withNameAndColumn(
    MetaDataTableType.DATABASE_PROPERTY.getQualifiedName(),
    KEY_COLUMN_SQL_DTO,
    VALUE_COLUMN_SQL_DTO);

  private DatabasePropertyTableSqlDtoCatalogue() {
  }
}
