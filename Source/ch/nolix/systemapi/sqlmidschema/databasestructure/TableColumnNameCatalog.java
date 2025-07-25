package ch.nolix.systemapi.sqlmidschema.databasestructure;

/**
 * Of the {@link TableColumnNameCatalog} an instance cannot be created.
 * 
 * @author Silvan Wyss
 * @version 2025-03-28
 */
public final class TableColumnNameCatalog {

  //'Id' is a reserved word in MSSQL databases.
  public static final String ID = "Id_";

  public static final String NAME = "Name";

  /**
   * Prevents that an instance of the {@link TableColumnNameCatalog} can be
   * created.
   */
  private TableColumnNameCatalog() {
  }
}
