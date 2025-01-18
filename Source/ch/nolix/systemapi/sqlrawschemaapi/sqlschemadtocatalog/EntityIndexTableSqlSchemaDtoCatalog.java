package ch.nolix.systemapi.sqlrawschemaapi.sqlschemadtocatalog;

import ch.nolix.systemapi.sqlrawschemaapi.databasestructure.EntityIndexTableColumn;
import ch.nolix.systemapi.sqlrawschemaapi.databasestructure.FixTableType;
import ch.nolix.systemapi.sqlrawschemaapi.datatypeapi.DataTypeTypeCatalog;
import ch.nolix.systemapi.sqlschemaapi.modelapi.ColumnDto;
import ch.nolix.systemapi.sqlschemaapi.modelapi.TableDto;

public final class EntityIndexTableSqlSchemaDtoCatalog {

  private static final ColumnDto TABLE_ID_SQL_SCHEMA_COLUMN_DTO = ColumnDto.withNameAndDataType(
    EntityIndexTableColumn.TABLE_ID.getName(), DataTypeTypeCatalog.TEXT);

  private static final ColumnDto ENTITY_ID_SQL_SCHEMA_COLUMN_DTO = ColumnDto.withNameAndDataType(
    EntityIndexTableColumn.ENTITY_ID.getName(), DataTypeTypeCatalog.TEXT);

  public static final TableDto ENTITY_INDEX_SQL_SCHEMA_TABLE_DTO = TableDto.withNameAndColumn(
    FixTableType.ENTITY_INDEX.getQualifiedName(),
    TABLE_ID_SQL_SCHEMA_COLUMN_DTO,
    ENTITY_ID_SQL_SCHEMA_COLUMN_DTO);

  private EntityIndexTableSqlSchemaDtoCatalog() {
  }
}
