/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.objectdata.expectation;

import ch.nolix.systemapi.middata.adapter.IDataAdapterAndSchemaReader;
import ch.nolix.systemapi.objectdata.expectation.IMultiReferenceEntryExpectationAdder;
import ch.nolix.systemapi.objectdata.model.IEntity;
import ch.nolix.systemapi.objectdata.model.IMultiReferenceEntry;

/**
 * @author Silvan Wyss
 */
public final class MultiReferenceEntryExpectationAdder implements IMultiReferenceEntryExpectationAdder {

  /**
   * {@inheritDoc}
   */
  @Override
  public void addExpectationThatNewlyReferencedEntitiesExist(
    final IMultiReferenceEntry<? extends IEntity> multiReferenceEntry,
    final IDataAdapterAndSchemaReader dataAndSchemaAdapter) {
    final var referencedTableName = multiReferenceEntry.getReferencedTableName();
    final var referencedEntityId = multiReferenceEntry.getReferencedEntityId();

    dataAndSchemaAdapter.expectTableContainsEntity(referencedTableName, referencedEntityId);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void addExpectationThatNewlyReferencedEntitiesExistIfMultiReferenceEntryIsNew(
    final IMultiReferenceEntry<? extends IEntity> multiReferenceEntry,
    final IDataAdapterAndSchemaReader dataAndSchemaAdapter) {
    if (multiReferenceEntry.isNew()) {
      addExpectationThatNewlyReferencedEntitiesExist(multiReferenceEntry, dataAndSchemaAdapter);
    }
  }
}
