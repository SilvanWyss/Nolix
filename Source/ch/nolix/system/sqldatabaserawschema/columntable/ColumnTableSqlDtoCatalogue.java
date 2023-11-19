//package declaration
package ch.nolix.system.sqldatabaserawschema.columntable;

import ch.nolix.system.sqldatabaserawschema.structure.SqlDatatypeCatalogue;
import ch.nolix.system.sqldatabaserawschema.structure.SystemDataTable;
import ch.nolix.system.sqldatabaserawschema.tabletable.TableTableColumn;
import ch.nolix.system.sqlschema.schemadto.ColumnDto;
import ch.nolix.system.sqlschema.schemadto.TableDto;
import ch.nolix.systemapi.sqlschemaapi.schemadtoapi.IColumnDto;
import ch.nolix.systemapi.sqlschemaapi.schemadtoapi.ITableDto;

//class
public final class ColumnTableSqlDtoCatalogue {

  //constant
  private static final IColumnDto ID_SQL_DTO = new ColumnDto(TableTableColumn.ID.getName(), SqlDatatypeCatalogue.TEXT);

  //constant
  private static final IColumnDto PARENT_TABLE_ID_SQL_DTO = new ColumnDto(ColumnTableColumn.PARENT_TABLE_ID.getName(),
    SqlDatatypeCatalogue.TEXT);

  //constant
  private static final IColumnDto NAME_SQL_DTO = new ColumnDto(ColumnTableColumn.NAME.getName(),
    SqlDatatypeCatalogue.TEXT);

  //constant
  private static final IColumnDto PROPERTY_TYPE_SQL_DTO = new ColumnDto(ColumnTableColumn.PROPERTY_TYPE.getName(),
    SqlDatatypeCatalogue.TEXT);

  //constant
  private static final IColumnDto DATA_TYPE_SQL_DTO = new ColumnDto(ColumnTableColumn.DATA_TYPE.getName(),
    SqlDatatypeCatalogue.TEXT);

  //constant
  private static final IColumnDto REFERENCED_TABLE_SQL_DTO = new ColumnDto(
    ColumnTableColumn.REFERENCED_TABLE_ID.getName(), SqlDatatypeCatalogue.TEXT);

  //constant
  private static final IColumnDto BACK_REFERENCED_COLUMN_ID_SQL_DTO = new ColumnDto(
    ColumnTableColumn.BACK_REFERENCED_COLUM_ID.getName(), SqlDatatypeCatalogue.TEXT);

  //constant
  public static final ITableDto COLUMN_TABLE_SQL_DTO = new TableDto(
    SystemDataTable.COLUMN.getQualifiedName(),
    ID_SQL_DTO,
    PARENT_TABLE_ID_SQL_DTO,
    NAME_SQL_DTO,
    PROPERTY_TYPE_SQL_DTO,
    DATA_TYPE_SQL_DTO,
    REFERENCED_TABLE_SQL_DTO,
    BACK_REFERENCED_COLUMN_ID_SQL_DTO);

  //constructor
  private ColumnTableSqlDtoCatalogue() {
  }
}
