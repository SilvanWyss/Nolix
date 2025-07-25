package ch.nolix.systemapi.sqlmiddata.schemaviewloader;

import ch.nolix.systemapi.midschema.adapter.ISchemaReader;
import ch.nolix.systemapi.midschemaview.model.DatabaseViewDto;

/**
 * @author Silvan Wyss
 * @version 2025-01-31
 */
public interface IDatabaseSchemaViewLoader {

  /**
   * @param databaseName
   * @param schemaAdapter
   * @return a new {@link DatabaseViewDto} with the given databaseName from the
   *         given schemaAdapter.
   * @throws RuntimeException if the given schemaAdapter is null.
   */
  DatabaseViewDto loadDatabaseSchemaView(String databaseName, ISchemaReader schemaAdapter);
}
