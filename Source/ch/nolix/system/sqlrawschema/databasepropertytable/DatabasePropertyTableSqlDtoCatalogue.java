//package declaration
package ch.nolix.system.sqlrawschema.databasepropertytable;

import ch.nolix.system.sqlrawschema.structure.SqlDatatypeCatalogue;
import ch.nolix.system.sqlrawschema.structure.SystemDataTable;
import ch.nolix.system.sqlschema.schemadto.ColumnDto;
import ch.nolix.system.sqlschema.schemadto.TableDto;

//class
public final class DatabasePropertyTableSqlDtoCatalogue {

  //constant
  private static final ColumnDto KEY_COLUMN_SQL_DTO = new ColumnDto(DatabasePropertySystemTableColumn.KEY.getLabel(),
    SqlDatatypeCatalogue.TEXT);

  //constant
  private static final ColumnDto VALUE_COLUMN_SQL_DTO = new ColumnDto(
    DatabasePropertySystemTableColumn.VALUE.getLabel(), SqlDatatypeCatalogue.TEXT);

  //constant
  public static final TableDto DATABASE_PROPERTY_TABLE_SQL_DTO = new TableDto(
    SystemDataTable.DATABASE_PROPERTY.getQualifiedName(),
    KEY_COLUMN_SQL_DTO,
    VALUE_COLUMN_SQL_DTO);

  //constructor
  private DatabasePropertyTableSqlDtoCatalogue() {
  }
}
