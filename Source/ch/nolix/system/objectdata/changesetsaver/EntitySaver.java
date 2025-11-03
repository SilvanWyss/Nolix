package ch.nolix.system.objectdata.changesetsaver;

import ch.nolix.system.databaseobject.modelexaminer.DatabaseObjectExaminer;
import ch.nolix.system.objectdata.middatamodelmapper.EntityDtoMapper;
import ch.nolix.systemapi.databaseobject.modelexaminer.IDatabaseObjectExaminer;
import ch.nolix.systemapi.middata.adapter.IDataAdapterAndSchemaReader;
import ch.nolix.systemapi.objectdata.changesetsaver.IEntitySaver;
import ch.nolix.systemapi.objectdata.changesetsaver.IMultiValueFieldSaver;
import ch.nolix.systemapi.objectdata.middatamodelmapper.IEntityDtoMapper;
import ch.nolix.systemapi.objectdata.model.IEntity;
import ch.nolix.systemapi.objectdata.model.IField;
import ch.nolix.systemapi.objectdata.model.IMultiBackReference;
import ch.nolix.systemapi.objectdata.model.IMultiReference;
import ch.nolix.systemapi.objectdata.model.IMultiValueField;

/**
 * @author Silvan Wyss
 * @version 2024-02-12
 */
public final class EntitySaver implements IEntitySaver {
  private static final IDatabaseObjectExaminer DATABASE_OBJECT_EXAMINER = new DatabaseObjectExaminer();

  private static final IEntityDtoMapper ENTITY_DTO_MAPPER = new EntityDtoMapper();

  private static final IMultiValueFieldSaver MULTI_VALUE_FIELD_SAVER = new MultiValueFieldSaver();

  private static final MultiReferenceSaver MULTI_REFERENCE_SAVER = new MultiReferenceSaver();

  private static final MultiBackReferenceSaver MULTI_BACK_REFERENCE_SAVER = new MultiBackReferenceSaver();

  /**
   * {@inheritDoc}
   */
  @Override
  public void saveEntityChanges(
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

  /**
   * {@inheritDoc}
   */
  @Override
  public void saveEntityDeletion(
    final IEntity entity,
    final IDataAdapterAndSchemaReader dataAndSchemaAdapter) {
    final var tableName = entity.getStoredParentTable().getName();
    final var entityDeletionDto = ENTITY_DTO_MAPPER.mapEntityToEntityDeletionDto(entity);

    dataAndSchemaAdapter.deleteEntity(tableName, entityDeletionDto);
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
        MULTI_VALUE_FIELD_SAVER.saveMultiValueFieldChanges((IMultiValueField<?>) field, dataAndSchemaAdapter);
        break;
      case MULTI_REFERENCE:
        MULTI_REFERENCE_SAVER.saveMultiReferenceChanges((IMultiReference<?>) field, dataAndSchemaAdapter);
        break;
      case MULTI_BACK_REFERENCE:
        MULTI_BACK_REFERENCE_SAVER.saveMultiBackReferenceChanges((IMultiBackReference<?>) field, dataAndSchemaAdapter);
        break;
      default:
        //Does nothing.
    }
  }
}
