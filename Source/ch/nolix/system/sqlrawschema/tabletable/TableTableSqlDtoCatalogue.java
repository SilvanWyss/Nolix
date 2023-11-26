//package declaration
package ch.nolix.system.sqlrawschema.tabletable;

//own imports
import ch.nolix.system.sqlrawschema.structure.SqlDatatypeCatalogue;
import ch.nolix.system.sqlrawschema.structure.SystemDataTable;
import ch.nolix.system.sqlschema.schemadto.ColumnDto;
import ch.nolix.system.sqlschema.schemadto.TableDto;
import ch.nolix.systemapi.sqlschemaapi.schemadtoapi.IColumnDto;
import ch.nolix.systemapi.sqlschemaapi.schemadtoapi.ITableDto;

//class
public final class TableTableSqlDtoCatalogue {

  //constant
  private static final IColumnDto ID_COLUMN_SQL_DTO = new ColumnDto(
    TableTableColumn.ID.getName(),
    SqlDatatypeCatalogue.TEXT);

  //constant
  private static final IColumnDto NAME_COLUMN_SQL_DTO = new ColumnDto(TableTableColumn.NAME.getName(),
    SqlDatatypeCatalogue.TEXT);

  //constant
  public static final ITableDto TABLE_TABLE_SQL_DTO = new TableDto(SystemDataTable.TABLE.getQualifiedName(),
    ID_COLUMN_SQL_DTO, NAME_COLUMN_SQL_DTO);

  //constructor
  private TableTableSqlDtoCatalogue() {
  }
}
