package ch.nolix.systemapi.sqlrawschemaapi.sqlschemadtocatalog;

import ch.nolix.coreapi.programatomapi.variableapi.PascalCaseVariableCatalog;
import ch.nolix.systemapi.sqlrawschemaapi.datatypeapi.DataTypeTypeCatalog;
import ch.nolix.systemapi.sqlschemaapi.modelapi.ColumnDto;

/**
 * @author Silvan Wyss
 * @version 2025-02-02
 */
public final class EntitySqlSchemaColumnDtoCatalog {

  public static final ColumnDto ID_COLUMN_DTO = //
  ColumnDto.withNameAndDataType(PascalCaseVariableCatalog.ID, DataTypeTypeCatalog.TEXT);

  public static final ColumnDto SAVE_STAMP_COLUMN_DTO = //
  ColumnDto.withNameAndDataType(PascalCaseVariableCatalog.SAVE_STAMP, DataTypeTypeCatalog.INTEGER);

  /**
   * Prevents that an instance of the {@link EntitySqlSchemaColumnDtoCatalog} can be
   * created.
   */
  private EntitySqlSchemaColumnDtoCatalog() {
  }
}
