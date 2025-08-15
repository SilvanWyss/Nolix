package ch.nolix.system.objectdata.changesetsaver;

import ch.nolix.system.databaseobject.modelexaminer.DatabaseObjectExaminer;
import ch.nolix.system.objectdata.middatamodelmapper.EntityDtoMapper;
import ch.nolix.systemapi.databaseobject.modelexaminer.IDatabaseObjectExaminer;
import ch.nolix.systemapi.middata.adapter.IDataAdapterAndSchemaReader;
import ch.nolix.systemapi.objectdata.middatamodelmapper.IEntityDtoMapper;
import ch.nolix.systemapi.objectdata.model.IEntity;
import ch.nolix.systemapi.objectdata.model.IField;
import ch.nolix.systemapi.objectdata.model.IMultiBackReference;
import ch.nolix.systemapi.objectdata.model.IMultiReference;
import ch.nolix.systemapi.objectdata.model.IMultiValueField;

public final class EntitySaver {

  private static final IDatabaseObjectExaminer DATABASE_OBJECT_EXAMINER = new DatabaseObjectExaminer();

  private static final IEntityDtoMapper ENTITY_DTO_MAPPER = new EntityDtoMapper();

  private static final MultiValueSaver MULTI_VALUE_SAVER = new MultiValueSaver();

  private static final MultiReferenceSaver MULTI_REFERENCE_SAVER = new MultiReferenceSaver();

  private static final MultiBackReferenceSaver MULTI_BACK_REFERENCE_SAVER = new MultiBackReferenceSaver();

  public void saveChangesOfEntity(
    final IEntity entity,
    final IDataAdapterAndSchemaReader dataAndSchemaAdapter) {
    switch (entity.getState()) {
      case NEW:
        saveNewEntity(entity, dataAndSchemaAdapter);
        break;
      case EDITED:
        saveChangesOfEditedEntity(entity, dataAndSchemaAdapter);
        break;
      case DELETED:
        saveEntityDeletion(entity, dataAndSchemaAdapter);
        break;
      default:
        //Does nothing.
    }
  }

  private void saveNewEntity(
    final IEntity newEntity,
    final IDataAdapterAndSchemaReader dataAndSchemaAdapter) {

    dataAndSchemaAdapter.insertEntity(
      newEntity.getStoredParentTable().getName(),
      ENTITY_DTO_MAPPER.mapEntityToEntityCreationDto(newEntity));

    saveMultiPropertyChangesOfEntity(newEntity, dataAndSchemaAdapter);
  }

  private void saveChangesOfEditedEntity(
    final IEntity editedEntity,
    final IDataAdapterAndSchemaReader dataAndSchemaAdapter) {

    dataAndSchemaAdapter.updateEntity(
      editedEntity.getStoredParentTable().getName(),
      ENTITY_DTO_MAPPER.mapEntityToEntityUpdateDto(editedEntity));

    saveMultiPropertyChangesOfEntity(editedEntity, dataAndSchemaAdapter);
  }

  private void saveEntityDeletion(
    final IEntity deletedEntity,
    final IDataAdapterAndSchemaReader dataAndSchemaAdapter) {
    dataAndSchemaAdapter.deleteEntity(
      deletedEntity.getStoredParentTable().getName(),
      ENTITY_DTO_MAPPER.mapEntityToEntityDeletionDto(deletedEntity));
  }

  private void saveMultiPropertyChangesOfEntity(
    final IEntity entity,
    final IDataAdapterAndSchemaReader dataAndSchemaAdapter) {
    for (final var p : entity.internalGetStoredFields()) {
      saveChangesOfPotentialMultiProperty(p, dataAndSchemaAdapter);
    }
  }

  private void saveChangesOfPotentialMultiProperty(
    final IField p,
    final IDataAdapterAndSchemaReader dataAndSchemaAdapter) {
    if (DATABASE_OBJECT_EXAMINER.isNewOrEdited(p)) {
      saveChangesOfPotentialMultiPropertyWhenIsNewOrEdited(p, dataAndSchemaAdapter);
    }
  }

  private void saveChangesOfPotentialMultiPropertyWhenIsNewOrEdited(
    final IField field,
    final IDataAdapterAndSchemaReader dataAndSchemaAdapter) {
    switch (field.getType()) {
      case MULTI_VALUE_FIELD:
        MULTI_VALUE_SAVER.saveChangesOfMultiValue((IMultiValueField<?>) field, dataAndSchemaAdapter);
        break;
      case MULTI_REFERENCE:
        MULTI_REFERENCE_SAVER.saveMultiReference((IMultiReference<?>) field, dataAndSchemaAdapter);
        break;
      case MULTI_BACK_REFERENCE:
        MULTI_BACK_REFERENCE_SAVER.saveMultiBackReference((IMultiBackReference<?>) field, dataAndSchemaAdapter);
        break;
      default:
        //Does nothing.
    }
  }
}
