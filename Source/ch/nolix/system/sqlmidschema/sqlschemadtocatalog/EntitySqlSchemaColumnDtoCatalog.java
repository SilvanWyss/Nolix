package ch.nolix.system.sqlmidschema.sqlschemadtocatalog;

import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.coreapi.misc.variable.PascalCaseVariableCatalog;
import ch.nolix.systemapi.sqlmidschema.datatype.DataTypeTypeCatalog;
import ch.nolix.systemapi.sqlschema.model.ColumnDto;

/**
 * @author Silvan Wyss
 * @version 2025-02-02
 */
public final class EntitySqlSchemaColumnDtoCatalog {

  public static final ColumnDto ID_COLUMN_DTO = //
  new ColumnDto(PascalCaseVariableCatalog.ID, DataTypeTypeCatalog.TEXT, ImmutableList.createEmpty());

  public static final ColumnDto SAVE_STAMP_COLUMN_DTO = //
  new ColumnDto(PascalCaseVariableCatalog.SAVE_STAMP, DataTypeTypeCatalog.INTEGER, ImmutableList.createEmpty());

  /**
   * Prevents that an instance of the {@link EntitySqlSchemaColumnDtoCatalog} can
   * be created.
   */
  private EntitySqlSchemaColumnDtoCatalog() {
  }
}
