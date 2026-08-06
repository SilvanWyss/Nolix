/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.objectdata.expectation;

import ch.nolix.system.objectdata.modelexaminer.EntityExaminer;
import ch.nolix.systemapi.middata.adapter.DataAdapterAndSchemaReader;
import ch.nolix.systemapi.objectdata.expectation.IEntityExpectationAdder;
import ch.nolix.systemapi.objectdata.model.IEntity;

/**
 * @author Silvan Wyss
 */
public final class EntityExpectationAdder implements IEntityExpectationAdder {
  private static final EntityExaminer ENTITY_EXAMINER = new EntityExaminer();

  private static final FieldExpectationAdder FIELD_EXPECTATION_ADDER = new FieldExpectationAdder();

  /**
   * {@inheritDoc}
   */
  @Override
  public void addExpectationThatNewlyReferencedEntitiesExist(
    final IEntity entity,
    final DataAdapterAndSchemaReader dataAndSchemaAdapter) {
    final var fields = entity.internalGetStoredFields();

    for (final var f : fields) {
      FIELD_EXPECTATION_ADDER.addExpectationThatNewlyReferencedEntitiesExistIfFieldIsNewOrEdited(
        f,
        dataAndSchemaAdapter);
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void addExpectationThatNewlyReferencedEntitiesExistIfEntityIsNewOrEdited(
    final IEntity entity,
    final DataAdapterAndSchemaReader dataAndSchemaAdapter) {
    if (ENTITY_EXAMINER.isNewOrEdited(entity)) {
      addExpectationThatNewlyReferencedEntitiesExist(entity, dataAndSchemaAdapter);
    }
  }
}
