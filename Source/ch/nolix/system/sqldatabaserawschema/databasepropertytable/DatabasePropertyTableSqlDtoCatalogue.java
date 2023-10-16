//package declaration
package ch.nolix.system.sqldatabaserawschema.databasepropertytable;

import ch.nolix.system.sqldatabasebasicschema.schemadto.ColumnDto;
import ch.nolix.system.sqldatabasebasicschema.schemadto.TableDto;
import ch.nolix.system.sqldatabaserawschema.structure.SqlDatatypeCatalogue;
import ch.nolix.system.sqldatabaserawschema.structure.SystemDataTable;

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
