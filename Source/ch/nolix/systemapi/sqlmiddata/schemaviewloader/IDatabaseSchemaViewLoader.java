/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.sqlmiddata.schemaviewloader;

import ch.nolix.systemapi.midschema.adapter.ISchemaReader;
import ch.nolix.systemapi.midschemainfo.model.DatabaseInfoDto;

/**
 * @author Silvan Wyss
 */
public interface IDatabaseSchemaViewLoader {
  /**
   * @param databaseName
   * @param schemaAdapter
   * @return a new {@link DatabaseInfoDto} with the given databaseName from the
   *         given schemaAdapter.
   * @throws RuntimeException if the given schemaAdapter is null.
   */
  DatabaseInfoDto loadDatabaseSchemaView(String databaseName, ISchemaReader schemaAdapter);
}
