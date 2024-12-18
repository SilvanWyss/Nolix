package ch.nolix.system.sqlrawschema.columntable;

import ch.nolix.system.sqlrawschema.datatype.DatatypeTypeCatalogue;
import ch.nolix.system.sqlrawschema.structure.SchemaTableType;
import ch.nolix.system.sqlrawschema.tabletable.TableTableColumn;
import ch.nolix.system.sqlschema.schemadto.TableDto;
import ch.nolix.systemapi.sqlschemaapi.schemadto.ColumnDto;
import ch.nolix.systemapi.sqlschemaapi.schemadtoapi.ITableDto;

public final class ColumnTableSqlDtoCatalogue {

  private static final ColumnDto ID_SQL_DTO = ColumnDto.withNameAndDataType(TableTableColumn.ID.getName(),
    DatatypeTypeCatalogue.TEXT);

  private static final ColumnDto PARENT_TABLE_ID_SQL_DTO = ColumnDto.withNameAndDataType(
    ColumnTableColumn.PARENT_TABLE_ID.getName(),
    DatatypeTypeCatalogue.TEXT);

  private static final ColumnDto NAME_SQL_DTO = ColumnDto.withNameAndDataType(ColumnTableColumn.NAME.getName(),
    DatatypeTypeCatalogue.TEXT);

  private static final ColumnDto PROPERTY_TYPE_SQL_DTO = ColumnDto.withNameAndDataType(
    ColumnTableColumn.FIELD_TYPE.getName(),
    DatatypeTypeCatalogue.TEXT);

  private static final ColumnDto DATA_TYPE_SQL_DTO = ColumnDto.withNameAndDataType(
    ColumnTableColumn.DATA_TYPE.getName(),
    DatatypeTypeCatalogue.TEXT);

  private static final ColumnDto REFERENCED_TABLE_SQL_DTO = ColumnDto.withNameAndDataType(
    ColumnTableColumn.REFERENCED_TABLE_ID.getName(), DatatypeTypeCatalogue.TEXT);

  private static final ColumnDto BACK_REFERENCED_COLUMN_ID_SQL_DTO = ColumnDto.withNameAndDataType(
    ColumnTableColumn.BACK_REFERENCED_COLUM_ID.getName(), DatatypeTypeCatalogue.TEXT);

  public static final ITableDto COLUMN_TABLE_SQL_DTO = TableDto.withNameAndColumn(
    SchemaTableType.COLUMN.getQualifiedName(),
    ID_SQL_DTO,
    PARENT_TABLE_ID_SQL_DTO,
    NAME_SQL_DTO,
    PROPERTY_TYPE_SQL_DTO,
    DATA_TYPE_SQL_DTO,
    REFERENCED_TABLE_SQL_DTO,
    BACK_REFERENCED_COLUMN_ID_SQL_DTO);

  private ColumnTableSqlDtoCatalogue() {
  }
}
