package ch.nolix.systemapi.sqlrawdataapi.schemaviewloaderapi;

import ch.nolix.systemapi.rawdataapi.schemaviewmodel.DatabaseSchemaViewDto;
import ch.nolix.systemapi.rawschemaapi.adapterapi.ISchemaReader;

/**
 * @author Silvan Wyss
 * @version 2025-01-31
 */
public interface IDatabaseSchemaViewLoader {

  /**
   * @param databaseName
   * @param schemaAdapter
   * @return a new {@link DatabaseSchemaViewDto} with the given databaseName from
   *         the given schemaAdapter.
   * @throws RuntimeException if the given schemaAdapter is null.
   */
  DatabaseSchemaViewDto loadDatabaseSchemaView(String databaseName, ISchemaReader schemaAdapter);
}
