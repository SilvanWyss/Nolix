package ch.nolix.system.objectdata.expectation;

import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.system.objectdata.fieldexaminer.FieldExaminer;
import ch.nolix.systemapi.middata.adapter.IDataAdapterAndSchemaReader;
import ch.nolix.systemapi.objectdata.expectation.IFieldExpectationAdder;
import ch.nolix.systemapi.objectdata.expectation.IMultiReferenceEntryExpectationAdder;
import ch.nolix.systemapi.objectdata.fieldexaminer.IFieldExaminer;
import ch.nolix.systemapi.objectdata.model.IEntity;
import ch.nolix.systemapi.objectdata.model.IField;
import ch.nolix.systemapi.objectdata.model.IMultiReference;
import ch.nolix.systemapi.objectdata.model.IOptionalReference;
import ch.nolix.systemapi.objectdata.model.IReference;

/**
 * @author Silvan Wyss
 * @version 2025-11-05
 */
public final class FieldExpectationAdder implements IFieldExpectationAdder {
  private static final IFieldExaminer FIELD_EXAMINER = new FieldExaminer();

  private static final IMultiReferenceEntryExpectationAdder MULTI_REFERENCE_ENTRY_EXPECTATION_ADDER = //
  new MultiReferenceEntryExpectationAdder();

  /**
   * {@inheritDoc}
   */
  @Override
  public void addExpectationThatNewlyReferencedEntitiesExist(
    final IField field,
    final IDataAdapterAndSchemaReader dataAndSchemaAdapter) {
    switch (field) {
      case IReference<? extends IEntity> reference ->
        addExpectationThatNewlyReferencedEntitiesExist(reference, dataAndSchemaAdapter);
      case IOptionalReference<? extends IEntity> optionalReference ->
        addExpectationThatNewlyReferencedEntitiesExist(optionalReference, dataAndSchemaAdapter);
      case IMultiReference<? extends IEntity> multiReference ->
        addExpectationThatNewlyReferencedEntitiesExist(multiReference, dataAndSchemaAdapter);
      default ->
        throw InvalidArgumentException.forArgument(field);
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void addExpectationThatNewlyReferencedEntitiesExist(
    final IMultiReference<IEntity> multiReference,
    final IDataAdapterAndSchemaReader dataAndSchemaAdapter) {
    final var newAndDeletedEntries = multiReference.getStoredNewAndDeletedEntries();
    for (final var e : newAndDeletedEntries) {
      MULTI_REFERENCE_ENTRY_EXPECTATION_ADDER.addExpectationThatNewlyReferencedEntitiesExistIfMultiReferenceEntryIsNew(
        e,
        dataAndSchemaAdapter);
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void addExpectationThatNewlyReferencedEntitiesExist(
    final IOptionalReference<IEntity> optionalReference,
    final IDataAdapterAndSchemaReader dataAndSchemaAdapter) {
    if (optionalReference.containsAny()) {
      final var referencedTableName = optionalReference.getReferencedTableName();
      final var referencedEntityId = optionalReference.getReferencedEntityId();

      dataAndSchemaAdapter.expectTableContainsEntity(referencedTableName, referencedEntityId);
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void addExpectationThatNewlyReferencedEntitiesExist(
    final IReference<IEntity> reference,
    final IDataAdapterAndSchemaReader dataAndSchemaAdapter) {
    final var referencedTableName = reference.getReferencedTableName();
    final var referencedEntityId = reference.getReferencedEntityId();

    dataAndSchemaAdapter.expectTableContainsEntity(referencedTableName, referencedEntityId);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void addExpectationThatNewlyReferencedEntitiesExistIfFieldIsNewOrEdited(
    final IField field,
    final IDataAdapterAndSchemaReader dataAndSchemaAdapter) {
    if (FIELD_EXAMINER.isNewOrEdited(field)) {
      addExpectationThatNewlyReferencedEntitiesExist(field, dataAndSchemaAdapter);
    }
  }
}
