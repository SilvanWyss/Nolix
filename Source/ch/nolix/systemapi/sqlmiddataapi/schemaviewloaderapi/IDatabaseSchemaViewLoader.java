package ch.nolix.systemapi.sqlmiddataapi.schemaviewloaderapi;

import ch.nolix.systemapi.middataapi.schemaviewmodel.DatabaseSchemaViewDto;
import ch.nolix.systemapi.midschemaapi.adapterapi.ISchemaReader;

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
