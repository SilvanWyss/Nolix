/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.objectdata.expectation;

import ch.nolix.system.objectdata.modelsearcher.DatabaseSearcher;
import ch.nolix.systemapi.middata.adapter.IDataAdapterAndSchemaReader;
import ch.nolix.systemapi.objectdata.expectation.IDatabaseExpectationAdder;
import ch.nolix.systemapi.objectdata.expectation.IEntityExpectationAdder;
import ch.nolix.systemapi.objectdata.model.IDatabase;
import ch.nolix.systemapi.objectdata.modelsearcher.IDatabaseSearcher;

/**
 * @author Silvan Wyss
 */
public final class DatabaseExpectationAdder implements IDatabaseExpectationAdder {
  private static final IDatabaseSearcher DATABASE_SEARCHER = new DatabaseSearcher();

  private static final IEntityExpectationAdder ENTITY_EXPECTATION_ADDER = new EntityExpectationAdder();

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
