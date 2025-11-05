package ch.nolix.system.objectdata.expectation;

import ch.nolix.system.objectdata.modelexaminer.EntityExaminer;
import ch.nolix.systemapi.middata.adapter.IDataAdapterAndSchemaReader;
import ch.nolix.systemapi.objectdata.expectation.IEntityExpectationAdder;
import ch.nolix.systemapi.objectdata.expectation.IFieldExpectationAdder;
import ch.nolix.systemapi.objectdata.model.IEntity;
import ch.nolix.systemapi.objectdata.modelexaminer.IEntityExaminer;

/**
 * @author Silvan Wyss
 * @version 2025-11-05
 */
public final class EntityExpectationAdder implements IEntityExpectationAdder {
  private static final IEntityExaminer ENTITY_EXAMINER = new EntityExaminer();

  private static final IFieldExpectationAdder FIELD_EXPECTATION_ADDER = new FieldExpectationAdder();

  /**
   * {@inheritDoc}
   */
  @Override
  public void addExpectationThatNewlyReferencedEntitiesExist(
    final IEntity entity,
    final IDataAdapterAndSchemaReader dataAndSchemaAdapter) {
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
    final IDataAdapterAndSchemaReader dataAndSchemaAdapter) {
    if (ENTITY_EXAMINER.isNewOrEdited(entity)) {
      addExpectationThatNewlyReferencedEntitiesExist(entity, dataAndSchemaAdapter);
    }
  }
}
