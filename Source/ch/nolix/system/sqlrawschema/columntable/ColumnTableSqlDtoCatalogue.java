//package declaration
package ch.nolix.system.sqlrawschema.columntable;

//own imports
import ch.nolix.system.sqlrawschema.datatype.DatatypeTypeCatalogue;
import ch.nolix.system.sqlrawschema.structure.SchemaTableType;
import ch.nolix.system.sqlrawschema.tabletable.TableTableColumn;
import ch.nolix.system.sqlschema.schemadto.ColumnDto;
import ch.nolix.system.sqlschema.schemadto.TableDto;
import ch.nolix.systemapi.sqlschemaapi.schemadtoapi.IColumnDto;
import ch.nolix.systemapi.sqlschemaapi.schemadtoapi.ITableDto;

//class
public final class ColumnTableSqlDtoCatalogue {

  //constant
  private static final IColumnDto ID_SQL_DTO = ColumnDto.withNameAndDataType(TableTableColumn.ID.getName(),
    DatatypeTypeCatalogue.TEXT);

  //constant
  private static final IColumnDto PARENT_TABLE_ID_SQL_DTO = ColumnDto.withNameAndDataType(
    ColumnTableColumn.PARENT_TABLE_ID.getName(),
    DatatypeTypeCatalogue.TEXT);

  //constant
  private static final IColumnDto NAME_SQL_DTO = ColumnDto.withNameAndDataType(ColumnTableColumn.NAME.getName(),
    DatatypeTypeCatalogue.TEXT);

  //constant
  private static final IColumnDto PROPERTY_TYPE_SQL_DTO = ColumnDto.withNameAndDataType(
    ColumnTableColumn.FIELD_TYPE.getName(),
    DatatypeTypeCatalogue.TEXT);

  //constant
  private static final IColumnDto DATA_TYPE_SQL_DTO = ColumnDto.withNameAndDataType(
    ColumnTableColumn.DATA_TYPE.getName(),
    DatatypeTypeCatalogue.TEXT);

  //constant
  private static final IColumnDto REFERENCED_TABLE_SQL_DTO = ColumnDto.withNameAndDataType(
    ColumnTableColumn.REFERENCED_TABLE_ID.getName(), DatatypeTypeCatalogue.TEXT);

  //constant
  private static final IColumnDto BACK_REFERENCED_COLUMN_ID_SQL_DTO = ColumnDto.withNameAndDataType(
    ColumnTableColumn.BACK_REFERENCED_COLUM_ID.getName(), DatatypeTypeCatalogue.TEXT);

  //constant
  public static final ITableDto COLUMN_TABLE_SQL_DTO = TableDto.withNameAndColumn(
    SchemaTableType.COLUMN.getQualifiedName(),
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
