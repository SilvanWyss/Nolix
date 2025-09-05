package ch.nolix.system.sqlmidschema.sqlschemadtocatalog;

import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.systemapi.sqlmidschema.databasestructure.ContentModelColumn;
import ch.nolix.systemapi.sqlmidschema.databasestructure.FixTable;
import ch.nolix.systemapi.sqlmidschema.datatype.DataTypeTypeCatalog;
import ch.nolix.systemapi.sqlschema.model.ColumnDto;
import ch.nolix.systemapi.sqlschema.model.TableDto;

public final class ContentModelTableSqlSchemaDtoCatalog {
  private static final ColumnDto FIELD_TYPE_SQL_DTO = //
  new ColumnDto(ContentModelColumn.FIELD_TYPE.getName(), DataTypeTypeCatalog.TEXT, ImmutableList.createEmpty());

  private static final ColumnDto DATA_TYPE_SQL_DTO = //
  new ColumnDto(ContentModelColumn.DATA_TYPE.getName(), DataTypeTypeCatalog.TEXT, ImmutableList.createEmpty());

  private static final ColumnDto REFERENCED_TABLE_ID_SQL_DTO = //
  new ColumnDto(
    ContentModelColumn.REFERENCED_TABLE_ID.getName(),
    DataTypeTypeCatalog.TEXT,
    ImmutableList.createEmpty());

  private static final ColumnDto BACK_REFERENCED_COLUMN_ID_SQL_DTO = //
  new ColumnDto(
    ContentModelColumn.BACK_REFERENCED_COLUM_ID.getName(),
    DataTypeTypeCatalog.TEXT,
    ImmutableList.createEmpty());

  public static final TableDto CONTENT_MODEL_TABLE_SQL_DTO = //
  new TableDto(
    FixTable.COLUMN.getName(),
    ImmutableList.withElement(
      FIELD_TYPE_SQL_DTO,
      DATA_TYPE_SQL_DTO,
      REFERENCED_TABLE_ID_SQL_DTO,
      BACK_REFERENCED_COLUMN_ID_SQL_DTO));

  private ContentModelTableSqlSchemaDtoCatalog() {
  }
}
