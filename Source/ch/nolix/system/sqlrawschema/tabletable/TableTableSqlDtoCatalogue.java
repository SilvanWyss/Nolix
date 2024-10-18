package ch.nolix.system.sqlrawschema.tabletable;

import ch.nolix.system.sqlrawschema.datatype.DatatypeTypeCatalogue;
import ch.nolix.system.sqlrawschema.structure.SchemaTableType;
import ch.nolix.system.sqlschema.schemadto.ColumnDto;
import ch.nolix.system.sqlschema.schemadto.TableDto;
import ch.nolix.systemapi.sqlschemaapi.schemadtoapi.IColumnDto;
import ch.nolix.systemapi.sqlschemaapi.schemadtoapi.ITableDto;

public final class TableTableSqlDtoCatalogue {

  private static final IColumnDto ID_COLUMN_SQL_DTO = ColumnDto.withNameAndDataType(
    TableTableColumn.ID.getName(),
    DatatypeTypeCatalogue.TEXT);

  private static final IColumnDto NAME_COLUMN_SQL_DTO = ColumnDto.withNameAndDataType(TableTableColumn.NAME.getName(),
    DatatypeTypeCatalogue.TEXT);

  public static final ITableDto TABLE_TABLE_SQL_DTO = TableDto.withNameAndColumn(
    SchemaTableType.TABLE.getQualifiedName(),
    ID_COLUMN_SQL_DTO, NAME_COLUMN_SQL_DTO);

  private TableTableSqlDtoCatalogue() {
  }
}
