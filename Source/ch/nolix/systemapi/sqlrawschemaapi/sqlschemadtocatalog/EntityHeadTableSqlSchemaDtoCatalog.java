package ch.nolix.systemapi.sqlrawschemaapi.sqlschemadtocatalog;

import ch.nolix.systemapi.sqlrawschemaapi.databasestructure.EntityHeadTableColumn;
import ch.nolix.systemapi.sqlrawschemaapi.databasestructure.IndexTableType;
import ch.nolix.systemapi.sqlrawschemaapi.datatypeapi.DataTypeTypeCatalog;
import ch.nolix.systemapi.sqlschemaapi.dto.ColumnDto;
import ch.nolix.systemapi.sqlschemaapi.dto.TableDto;

public final class EntityHeadTableSqlSchemaDtoCatalog {

  private static final ColumnDto TABLE_COLUMN_DTO = ColumnDto.withNameAndDataType(
    EntityHeadTableColumn.TABLE_ID.getName(), DataTypeTypeCatalog.TEXT);

  private static final ColumnDto ENTITY_ID_COLUMN_DTO = ColumnDto.withNameAndDataType(
    EntityHeadTableColumn.ENTITY_ID.getName(), DataTypeTypeCatalog.TEXT);

  public static final TableDto ENTITY_HEAD_TABLE_DTO = TableDto.withNameAndColumn(
    IndexTableType.ENTITY_HEAD.getQualifiedName(),
    TABLE_COLUMN_DTO,
    ENTITY_ID_COLUMN_DTO);

  private EntityHeadTableSqlSchemaDtoCatalog() {
  }
}
