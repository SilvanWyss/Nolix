package ch.nolix.system.sqlmidschema.sqlschemadtocatalog;

import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.systemapi.sqlmidschema.databasestructure.EntityIndexColumn;
import ch.nolix.systemapi.sqlmidschema.databasestructure.FixTable;
import ch.nolix.systemapi.sqlmidschema.datatype.DataTypeTypeCatalog;
import ch.nolix.systemapi.sqlschema.model.ColumnDto;
import ch.nolix.systemapi.sqlschema.model.TableDto;

/**
 * @author Silvan Wyss
 */
public final class EntityIndexTableSqlSchemaDtoCatalog {
  private static final ColumnDto ENTITY_ID_SQL_SCHEMA_COLUMN_DTO = //
  new ColumnDto(EntityIndexColumn.ENTITY_ID.getName(), DataTypeTypeCatalog.TEXT, ImmutableList.createEmpty());

  private static final ColumnDto TABLE_ID_SQL_SCHEMA_COLUMN_DTO = //
  new ColumnDto(EntityIndexColumn.TABLE_ID.getName(), DataTypeTypeCatalog.TEXT, ImmutableList.createEmpty());

  public static final TableDto ENTITY_INDEX_SQL_SCHEMA_TABLE_DTO = //
  new TableDto(
    FixTable.ENTITY_INDEX.getName(),
    ImmutableList.withElements(ENTITY_ID_SQL_SCHEMA_COLUMN_DTO, TABLE_ID_SQL_SCHEMA_COLUMN_DTO));

  private EntityIndexTableSqlSchemaDtoCatalog() {
  }
}
