package ch.nolix.systemapi.sqlmidschema.databasestructure;

/**
 * @author Silvan Wyss
 * @version 2025-02-07
 */
public final class ColumnTableFieldIndexCatalog {
  public static final int ID_INDEX = 1;

  public static final int PARENT_TABLE_INDEX = 2;

  public static final int NAME_INDEX = 3;

  public static final int FIELD_TYPE_INDEX = 4;

  public static final int DATA_TYPE_INDEX = 5;

  public static final int REFERENCED_TABLE_ID_INDEX = 6;

  public static final int BACK_REFERENCED_TABLE_ID_INDEX = 7;

  /**
   * Prevents that an instance of the {@link ColumnTableFieldIndexCatalog} can be
   * created.
   */
  private ColumnTableFieldIndexCatalog() {
  }
}
