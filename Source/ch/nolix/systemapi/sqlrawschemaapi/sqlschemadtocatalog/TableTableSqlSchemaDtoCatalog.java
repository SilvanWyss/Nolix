package ch.nolix.systemapi.sqlrawschemaapi.sqlschemadtocatalog;

import ch.nolix.system.sqlrawschema.datatype.DatatypeTypeCatalog;
import ch.nolix.systemapi.sqlrawschemaapi.databasestructure.SchemaTableType;
import ch.nolix.systemapi.sqlrawschemaapi.databasestructure.TableTableColumn;
import ch.nolix.systemapi.sqlschemaapi.dto.ColumnDto;
import ch.nolix.systemapi.sqlschemaapi.dto.TableDto;

public final class TableTableSqlSchemaDtoCatalog {

  private static final ColumnDto ID_COLUMN_SQL_DTO = ColumnDto.withNameAndDataType(
    TableTableColumn.ID.getName(),
    DatatypeTypeCatalog.TEXT);

  private static final ColumnDto NAME_COLUMN_SQL_DTO = ColumnDto.withNameAndDataType(TableTableColumn.NAME.getName(),
    DatatypeTypeCatalog.TEXT);

  public static final TableDto TABLE_TABLE_SQL_DTO = TableDto.withNameAndColumn(
    SchemaTableType.TABLE.getQualifiedName(),
    ID_COLUMN_SQL_DTO, NAME_COLUMN_SQL_DTO);

  private TableTableSqlSchemaDtoCatalog() {
  }
}
