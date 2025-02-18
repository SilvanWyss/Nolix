package ch.nolix.system.sqlrawschema.sqlschemadtocatalog;

import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.systemapi.sqlrawschemaapi.databasestructure.FixTableType;
import ch.nolix.systemapi.sqlrawschemaapi.databasestructure.TableReferenceTableColumn;
import ch.nolix.systemapi.sqlrawschemaapi.datatypeapi.DataTypeTypeCatalog;
import ch.nolix.systemapi.sqlschemaapi.modelapi.ColumnDto;
import ch.nolix.systemapi.sqlschemaapi.modelapi.TableDto;

/**
 * @author Silvan Wyss
 * @version 2025-01-19
 */
public final class TableReferenceTableSqlSchemaCatalog {

  private static final ColumnDto REFERENCE_COLUMN_ID_COLUMN_SQL_DTO = //
  new ColumnDto(
    TableReferenceTableColumn.REFERENCE_COLUMN_ID.getName(),
    DataTypeTypeCatalog.TEXT,
    ImmutableList.createEmpty());

  private static final ColumnDto REFERENCED_TABLE_ID_COLUMN_SQL_DTO = //
  new ColumnDto(
    TableReferenceTableColumn.REFERENCED_TABLE_ID.getName(),
    DataTypeTypeCatalog.TEXT,
    ImmutableList.createEmpty());

  public static final TableDto TABLE_REFERENCE_SQL_DTO = //
  new TableDto(
    FixTableType.TABLE_REFERENCE.getName(),
    ImmutableList.withElement(REFERENCE_COLUMN_ID_COLUMN_SQL_DTO, REFERENCED_TABLE_ID_COLUMN_SQL_DTO));

  /**
   * Prevents that an instance of the {@link TableReferenceTableSqlSchemaCatalog}
   * can be created.
   */
  private TableReferenceTableSqlSchemaCatalog() {
  }
}
