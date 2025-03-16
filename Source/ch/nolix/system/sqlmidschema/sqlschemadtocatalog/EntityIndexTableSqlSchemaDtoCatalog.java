package ch.nolix.system.sqlmidschema.sqlschemadtocatalog;

import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.systemapi.sqlmidschemaapi.databasestructure.EntityIndexTableColumn;
import ch.nolix.systemapi.sqlmidschemaapi.databasestructure.FixTableType;
import ch.nolix.systemapi.sqlmidschemaapi.datatypeapi.DataTypeTypeCatalog;
import ch.nolix.systemapi.sqlschemaapi.modelapi.ColumnDto;
import ch.nolix.systemapi.sqlschemaapi.modelapi.TableDto;

public final class EntityIndexTableSqlSchemaDtoCatalog {

  private static final ColumnDto ENTITY_ID_SQL_SCHEMA_COLUMN_DTO = //
  new ColumnDto(EntityIndexTableColumn.ENTITY_ID.getName(), DataTypeTypeCatalog.TEXT, ImmutableList.createEmpty());

  private static final ColumnDto TABLE_ID_SQL_SCHEMA_COLUMN_DTO = //
  new ColumnDto(EntityIndexTableColumn.TABLE_ID.getName(), DataTypeTypeCatalog.TEXT, ImmutableList.createEmpty());

  public static final TableDto ENTITY_INDEX_SQL_SCHEMA_TABLE_DTO = //
  new TableDto(
    FixTableType.ENTITY_INDEX.getName(),
    ImmutableList.withElement(ENTITY_ID_SQL_SCHEMA_COLUMN_DTO, TABLE_ID_SQL_SCHEMA_COLUMN_DTO));

  private EntityIndexTableSqlSchemaDtoCatalog() {
  }
}
