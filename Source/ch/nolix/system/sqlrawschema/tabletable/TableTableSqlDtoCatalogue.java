package ch.nolix.system.sqlrawschema.tabletable;

import ch.nolix.system.sqlrawschema.datatype.DatatypeTypeCatalogue;
import ch.nolix.systemapi.sqlrawschemaapi.databasestructure.SchemaTableType;
import ch.nolix.systemapi.sqlrawschemaapi.databasestructure.TableTableColumn;
import ch.nolix.systemapi.sqlschemaapi.schemadto.ColumnDto;
import ch.nolix.systemapi.sqlschemaapi.schemadto.TableDto;

public final class TableTableSqlDtoCatalogue {

  private static final ColumnDto ID_COLUMN_SQL_DTO = ColumnDto.withNameAndDataType(
    TableTableColumn.ID.getName(),
    DatatypeTypeCatalogue.TEXT);

  private static final ColumnDto NAME_COLUMN_SQL_DTO = ColumnDto.withNameAndDataType(TableTableColumn.NAME.getName(),
    DatatypeTypeCatalogue.TEXT);

  public static final TableDto TABLE_TABLE_SQL_DTO = TableDto.withNameAndColumn(
    SchemaTableType.TABLE.getQualifiedName(),
    ID_COLUMN_SQL_DTO, NAME_COLUMN_SQL_DTO);

  private TableTableSqlDtoCatalogue() {
  }
}
