package ch.nolix.system.sqlmidschema.sqlschemadtocatalog;

import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.systemapi.sqlmidschema.databasestructure.BackReferenceableColumnColumn;
import ch.nolix.systemapi.sqlmidschema.databasestructure.FixTable;
import ch.nolix.systemapi.sqlmidschema.datatype.DataTypeTypeCatalog;
import ch.nolix.systemapi.sqlschema.model.ColumnDto;
import ch.nolix.systemapi.sqlschema.model.TableDto;

/**
 * @author Silvan Wyss
 */
public final class BackReferenceableColumnTableSqlSchemaDtoCatalog {
  private static final ColumnDto PARENT_BASE_BACK_REFERENCE_COLUMN_ID_SQL_DTO = //
  new ColumnDto(
    BackReferenceableColumnColumn.PARENT_BASE_BACK_REFERENCE_COLUMN_ID.getName(),
    DataTypeTypeCatalog.TEXT,
    ImmutableList.createEmpty());

  private static final ColumnDto BACK_REFERENCEABLE_COLUMN_ID_SQL_DTO = //
  new ColumnDto(
    BackReferenceableColumnColumn.BACK_REFERENCEABLE_COLUMN_ID.getName(),
    DataTypeTypeCatalog.TEXT,
    ImmutableList.createEmpty());

  public static final TableDto BACK_REFERENCEABLE_COLUMN_TABLE_SQL_DTO = //
  new TableDto(
    FixTable.COLUMN.getName(),
    ImmutableList.withElements(PARENT_BASE_BACK_REFERENCE_COLUMN_ID_SQL_DTO, BACK_REFERENCEABLE_COLUMN_ID_SQL_DTO));

  private BackReferenceableColumnTableSqlSchemaDtoCatalog() {
  }
}
