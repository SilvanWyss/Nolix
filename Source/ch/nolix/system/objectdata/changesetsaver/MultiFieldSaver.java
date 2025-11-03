package ch.nolix.system.objectdata.changesetsaver;

import ch.nolix.core.misc.function.FunctionService;
import ch.nolix.system.objectdata.fieldexaminer.FieldExaminer;
import ch.nolix.systemapi.middata.adapter.IDataAdapterAndSchemaReader;
import ch.nolix.systemapi.objectdata.changesetsaver.IMultiFieldSaver;
import ch.nolix.systemapi.objectdata.changesetsaver.IMultiValueFieldSaver;
import ch.nolix.systemapi.objectdata.fieldexaminer.IFieldExaminer;
import ch.nolix.systemapi.objectdata.model.IEntity;
import ch.nolix.systemapi.objectdata.model.IField;
import ch.nolix.systemapi.objectdata.model.IMultiBackReference;
import ch.nolix.systemapi.objectdata.model.IMultiReference;
import ch.nolix.systemapi.objectdata.model.IMultiValueField;

/**
 * @author Silvan Wyss
 * @version 2025-11-03
 */
public final class MultiFieldSaver implements IMultiFieldSaver {
  private static final IFieldExaminer FIELD_EXAMINER = new FieldExaminer();

  private static final IMultiValueFieldSaver MULTI_VALUE_FIELD_SAVER = new MultiValueFieldSaver();

  private static final MultiReferenceSaver MULTI_REFERENCE_SAVER = new MultiReferenceSaver();

  private static final MultiBackReferenceSaver MULTI_BACK_REFERENCE_SAVER = new MultiBackReferenceSaver();

  /**
   * {@inheritDoc}
   */
  @Override
  public void saveFieldChangesIfIsMultiField(
    final IField field,
    final IDataAdapterAndSchemaReader dataAndSchemaAdapter) {
    switch (field) {
      case IMultiValueField<?> multiValueField ->
        MULTI_VALUE_FIELD_SAVER.saveMultiValueFieldChanges(multiValueField, dataAndSchemaAdapter);
      case IMultiReference<? extends IEntity> multiReference ->
        MULTI_REFERENCE_SAVER.saveMultiReferenceChanges(multiReference, dataAndSchemaAdapter);
      case IMultiBackReference<? extends IEntity> multiBackReference ->
        MULTI_BACK_REFERENCE_SAVER.saveMultiBackReferenceChanges(multiBackReference, dataAndSchemaAdapter);
      default ->
        FunctionService.doNothing();
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void saveMultiFieldChangesOfEntity(
    final IEntity entity,
    final IDataAdapterAndSchemaReader dataAndSchemaAdapter) {
    final var entityState = entity.getState();

    switch (entityState) {
      case NEW, DELETED:
        for (final var f : entity.internalGetStoredFields()) {
          saveFieldChangesIfIsMultiField(f, dataAndSchemaAdapter);
        }

        break;
      case EDITED:
        for (final var f : entity.internalGetStoredFields()) {
          saveFieldChangesIfIsNewOrEditedMultiField(f, dataAndSchemaAdapter);
        }

        break;
      default:
    }
  }

  /**
   * Saves the changes of the given field if the given field is a new or edited
   * multi field.
   * 
   * @param field
   * @param dataAndSchemaAdapter
   */
  private void saveFieldChangesIfIsNewOrEditedMultiField(
    final IField field,
    final IDataAdapterAndSchemaReader dataAndSchemaAdapter) {
    if (FIELD_EXAMINER.isNewOrEdited(field)) {
      saveFieldChangesIfIsMultiField(field, dataAndSchemaAdapter);
    }
  }
}
