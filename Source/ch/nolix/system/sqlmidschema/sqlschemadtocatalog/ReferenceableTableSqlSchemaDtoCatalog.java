package ch.nolix.system.sqlmidschema.sqlschemadtocatalog;

import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.systemapi.sqlmidschema.databasestructure.FixTable;
import ch.nolix.systemapi.sqlmidschema.databasestructure.ReferenceableTableColumn;
import ch.nolix.systemapi.sqlmidschema.datatype.DataTypeTypeCatalog;
import ch.nolix.systemapi.sqlschema.model.ColumnDto;
import ch.nolix.systemapi.sqlschema.model.TableDto;

public final class ReferenceableTableSqlSchemaDtoCatalog {
  private static final ColumnDto PARENT_BASE_REFERENCE_COLUMN_ID_SQL_DTO = //
  new ColumnDto(
    ReferenceableTableColumn.PARENT_BASE_REFERENCE_COLUMN_ID.getName(),
    DataTypeTypeCatalog.TEXT,
    ImmutableList.createEmpty());

  private static final ColumnDto REFERENCEABLE_TABLE_ID_SQL_DTO = //
  new ColumnDto(
    ReferenceableTableColumn.REFERENCEABLE_TABLE_ID.getName(),
    DataTypeTypeCatalog.TEXT,
    ImmutableList.createEmpty());

  public static final TableDto REFERENCEABLE_TABLE_TABLE_SQL_DTO = //
  new TableDto(
    FixTable.COLUMN.getName(),
    ImmutableList.withElement(PARENT_BASE_REFERENCE_COLUMN_ID_SQL_DTO, REFERENCEABLE_TABLE_ID_SQL_DTO));

  private ReferenceableTableSqlSchemaDtoCatalog() {
  }
}
