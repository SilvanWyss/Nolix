package ch.nolix.systemapi.sqlrawschemaapi.databasestructure;

/**
 * @author Silvan Wyss
 * @version 2025-02-07
 */
public final class ColumnTableFieldIndexCatalog {

  public static final int ID_INDEX = 1;

  public static final int PARENT_TABLE_INDEX = 2;

  public static final int NAME_INDEX = 3;

  public static final int CONTENT_TYPE_INDEX = 4;

  public static final int DATA_TYPE_INDEX = 5;

  /**
   * Prevents that an instance of the {@link ColumnTableFieldIndexCatalog} can be
   * created.
   */
  private ColumnTableFieldIndexCatalog() {
  }
}
