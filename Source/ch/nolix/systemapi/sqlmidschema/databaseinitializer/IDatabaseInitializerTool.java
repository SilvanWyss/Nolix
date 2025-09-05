package ch.nolix.systemapi.sqlmidschema.databaseinitializer;

import ch.nolix.coreapi.sql.connection.ISqlConnection;
import ch.nolix.systemapi.sqlschema.adapter.ISchemaWriter;
import ch.nolix.systemapi.time.moment.ITime;

/**
 * @author Silvan Wyss
 * @version 2025-01-18
 */
public interface IDatabaseInitializerTool {
  /**
   * Initializes the fix tables of the database using the given schemaWriter.
   * 
   * @param schemaWriter
   * @throws RuntimeException if the given schemaWriter is null or closed.
   */
  void initializeFixTables(ISchemaWriter schemaWriter);

  /**
   * Saves the given schemaTimestamp to the database using the given sqlConnection
   * and databaseName.
   * 
   * @param schemaTimestamp
   * @param sqlConnection
   * @param databaseName
   * @throws RuntimeException if the given schemaTimestamp is null.
   * @throws RuntimeException if the given sqlConnection is null or closed.
   * @throws RuntimeException if the given databaseName is null or blank.
   */
  void saveSchemaTimestamp(ITime schemaTimestamp, ISqlConnection sqlConnection, String databaseName);
}
