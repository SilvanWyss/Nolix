/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.objectdata.expectation;

import ch.nolix.system.objectdata.modelsearcher.DatabaseSearcher;
import ch.nolix.systemapi.middata.adapter.IDataAdapterAndSchemaReader;
import ch.nolix.systemapi.objectdata.expectation.IDatabaseExpectationAdder;
import ch.nolix.systemapi.objectdata.model.IDatabase;

/**
 * @author Silvan Wyss
 */
public final class DatabaseExpectationAdder implements IDatabaseExpectationAdder {
  private static final DatabaseSearcher DATABASE_SEARCHER = new DatabaseSearcher();

  private static final EntityExpectationAdder ENTITY_EXPECTATION_ADDER = new EntityExpectationAdder();

  /**
   * {@inheritDoc}
   */
  @Override
  public void addExpectationThatNewlyReferencedEntitiesExist(
    final IDatabase database,
    final IDataAdapterAndSchemaReader dataAndSchemaAdapter) {
    final var entitiesInLocalData = DATABASE_SEARCHER.getStoredEntitiesInLocalData(database);

    for (final var e : entitiesInLocalData) {
      ENTITY_EXPECTATION_ADDER.addExpectationThatNewlyReferencedEntitiesExistIfEntityIsNewOrEdited(
        e,
        dataAndSchemaAdapter);
    }
  }
}
