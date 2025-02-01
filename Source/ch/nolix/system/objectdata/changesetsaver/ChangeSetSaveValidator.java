package ch.nolix.system.objectdata.changesetsaver;

import ch.nolix.system.objectdata.fieldtool.FieldTool;
import ch.nolix.system.objectdata.modelexaminer.EntityExaminer;
import ch.nolix.system.objectdata.modelsearcher.DatabaseSearcher;
import ch.nolix.systemapi.objectdataapi.fieldtoolapi.IFieldTool;
import ch.nolix.systemapi.objectdataapi.modelapi.IDatabase;
import ch.nolix.systemapi.objectdataapi.modelapi.IEntity;
import ch.nolix.systemapi.objectdataapi.modelapi.IField;
import ch.nolix.systemapi.objectdataapi.modelapi.IMultiReference;
import ch.nolix.systemapi.objectdataapi.modelapi.IOptionalReference;
import ch.nolix.systemapi.objectdataapi.modelapi.IReference;
import ch.nolix.systemapi.objectdataapi.modelexaminerapi.IEntityExaminer;
import ch.nolix.systemapi.objectdataapi.modelsearcherapi.IDatabaseSearcher;
import ch.nolix.systemapi.rawdataapi.adapterapi.IDataAdapterAndSchemaReader;

public final class ChangeSetSaveValidator {

  private static final IDatabaseSearcher DATABASE_SEARCHER = new DatabaseSearcher();

  private static final IEntityExaminer ENTITY_EXAMINER = new EntityExaminer();

  private static final IFieldTool FIELD_TOOL = new FieldTool();

  public void addExpectationToDatabaseThatNewlyReferencedEntitiesExist(
    final IDatabase database,
    final IDataAdapterAndSchemaReader dataAndSchemaAdapter) {

    final var entitiesInLocalData = DATABASE_SEARCHER.getStoredEntitiesInLocalData(database);

    for (final var e : entitiesInLocalData) {
      addExpectationToDatabaseThatNewlyReferencedEntitiesExist(e, dataAndSchemaAdapter);
    }
  }

  private void addExpectationToDatabaseThatNewlyReferencedEntitiesExist(
    final IEntity entity,
    final IDataAdapterAndSchemaReader dataAndSchemaAdapter) {
    if (ENTITY_EXAMINER.isNewOrEdited(entity)) {
      addExpectationToDatabaseThatNewlyReferencedEntitiesExistWhenEntityIsNewOrEdited(entity, dataAndSchemaAdapter);
    }
  }

  private void addExpectationToDatabaseThatNewlyReferencedEntitiesExistWhenEntityIsNewOrEdited(
    final IEntity entity,
    final IDataAdapterAndSchemaReader dataAndSchemaAdapter) {
    for (final var p : entity.internalGetStoredFields()) {
      addExpectationToDatabaseThatNewlyReferencedEntitiesExist(p, dataAndSchemaAdapter);
    }
  }

  private void addExpectationToDatabaseThatNewlyReferencedEntitiesExist(
    final IField field,
    final IDataAdapterAndSchemaReader dataAndSchemaAdapter) {
    if (FIELD_TOOL.isNewOrEdited(field)) {
      addExpectationToDatabaseThatNewlyReferencedEntitiesExistWhenFieldIsNewOrEdited(field,
        dataAndSchemaAdapter);
    }
  }

  private void addExpectationToDatabaseThatNewlyReferencedEntitiesExistWhenFieldIsNewOrEdited(
    final IField field,
    final IDataAdapterAndSchemaReader dataAndSchemaAdapter) {
    switch (field.getType()) {
      case REFERENCE:

        final var reference = (IReference<?>) field;

        addExpectationToDatabaseThatNewlyReferencedEntitiesExistWhenReferenceIsNewOrEdited(reference,
          dataAndSchemaAdapter);

        break;
      case OPTIONAL_REFERENCE:

        final var optionalReference = (IOptionalReference<?>) field;

        addExpectationToDatabaseThatNewlyReferencedEntitiesExistWhenOptionalReferenceIsNewOrEdited(
          optionalReference,
          dataAndSchemaAdapter);

        break;
      case MULTI_REFERENCE:

        final var multiReference = (IMultiReference<?>) field;

        addExpectationToDatabaseThatNewlyReferencedEntitiesExistWhenMultiReferenceIsNewOrEdited(
          multiReference,
          dataAndSchemaAdapter);

        break;
      default:
        //Does nothing.
    }
  }

  private void addExpectationToDatabaseThatNewlyReferencedEntitiesExistWhenMultiReferenceIsNewOrEdited(
    final IMultiReference<?> multiReference,
    final IDataAdapterAndSchemaReader dataAndSchemaAdapter) {

    final var referencedTableName = multiReference.getReferencedTableName();

    for (final var le : multiReference.getStoredNewAndDeletedEntries()) {
      if (le.isNew()) {
        dataAndSchemaAdapter.expectTableContainsEntity(
          referencedTableName,
          le.getReferencedEntityId());
      }
    }
  }

  private void addExpectationToDatabaseThatNewlyReferencedEntitiesExistWhenOptionalReferenceIsNewOrEdited(
    final IOptionalReference<?> optionalReference,
    final IDataAdapterAndSchemaReader dataAndSchemaAdapter) {
    if (optionalReference.containsAny()) {
      dataAndSchemaAdapter.expectTableContainsEntity(
        optionalReference.getReferencedTableName(),
        optionalReference.getReferencedEntityId());
    }
  }

  private void addExpectationToDatabaseThatNewlyReferencedEntitiesExistWhenReferenceIsNewOrEdited(
    final IReference<?> reference,
    final IDataAdapterAndSchemaReader dataAndSchemaAdapter) {
    dataAndSchemaAdapter.expectTableContainsEntity(
      reference.getReferencedTableName(),
      reference.getReferencedEntityId());
  }
}
