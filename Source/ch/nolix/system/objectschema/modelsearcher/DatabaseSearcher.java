/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.objectschema.modelsearcher;

import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.systemapi.objectschema.model.IColumn;
import ch.nolix.systemapi.objectschema.model.IDatabase;
import ch.nolix.systemapi.objectschema.model.ITable;
import ch.nolix.systemapi.objectschema.modelsearcher.IDatabaseSearcher;

/**
 * @author Silvan Wyss
 */
public final class DatabaseSearcher implements IDatabaseSearcher {
  private static final TableSearcher TABLE_SEARCHER = new TableSearcher();

  @Override
  public ExtendedIterable<? extends IColumn> getStoredBaseBackReferenceColumns(final IDatabase database) {

    final var tables = database.getStoredTables();

    return tables.toMultiples(TABLE_SEARCHER::getStoredBaseBackReferenceColumns);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ITable getStoredTableByName(final IDatabase database, final String tableName) {

    final var tables = database.getStoredTables();

    return tables.getStoredFirst(t -> t.hasName(tableName));
  }
}
