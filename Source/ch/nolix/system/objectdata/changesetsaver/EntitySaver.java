package ch.nolix.system.objectdata.changesetsaver;

import ch.nolix.system.objectdata.datatool.EntityTool;
import ch.nolix.system.objectdata.fieldtool.FieldTool;
import ch.nolix.systemapi.objectdataapi.dataapi.IEntity;
import ch.nolix.systemapi.objectdataapi.dataapi.IField;
import ch.nolix.systemapi.objectdataapi.dataapi.IMultiBackReference;
import ch.nolix.systemapi.objectdataapi.dataapi.IMultiReference;
import ch.nolix.systemapi.objectdataapi.dataapi.IMultiValue;
import ch.nolix.systemapi.objectdataapi.datatoolapi.IEntityTool;
import ch.nolix.systemapi.objectdataapi.fieldtoolapi.IFieldTool;
import ch.nolix.systemapi.rawdataapi.dataandschemaadapterapi.IDataAndSchemaAdapter;

public final class EntitySaver {

  private static final IEntityTool ENTITY_TOOL = new EntityTool();

  private static final IFieldTool FIELD_TOOL = new FieldTool();

  private static final MultiValueSaver MULTI_VALUE_SAVER = new MultiValueSaver();

  private static final MultiReferenceSaver MULTI_REFERENCE_SAVER = new MultiReferenceSaver();

  private static final MultiBackReferenceSaver MULTI_BACK_REFERENCE_SAVER = new MultiBackReferenceSaver();

  public void saveChangesOfEntity(
    final IEntity entity,
    final IDataAndSchemaAdapter dataAndSchemaAdapter) {
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
    final IDataAndSchemaAdapter dataAndSchemaAdapter) {

    dataAndSchemaAdapter.insertEntity(
      newEntity.getParentTableName(),
      ENTITY_TOOL.createNewEntityDtoForEntity(newEntity));

    saveMultiPropertyChangesOfEntity(newEntity, dataAndSchemaAdapter);
  }

  private void saveChangesOfEditedEntity(
    final IEntity editedEntity,
    final IDataAndSchemaAdapter dataAndSchemaAdapter) {

    dataAndSchemaAdapter.updateEntity(
      editedEntity.getParentTableName(),
      ENTITY_TOOL.createEntityUpdateDtoForEntity(editedEntity));

    saveMultiPropertyChangesOfEntity(editedEntity, dataAndSchemaAdapter);
  }

  private void saveEntityDeletion(
    final IEntity deletedEntity,
    final IDataAndSchemaAdapter dataAndSchemaAdapter) {
    dataAndSchemaAdapter.deleteEntity(
      deletedEntity.getStoredParentTable().getName(),
      ENTITY_TOOL.createEntityHeadDtoForEntity(deletedEntity));
  }

  private void saveMultiPropertyChangesOfEntity(
    final IEntity entity,
    final IDataAndSchemaAdapter dataAndSchemaAdapter) {
    for (final var p : entity.internalGetStoredFields()) {
      saveChangesOfPotentialMultiProperty(p, dataAndSchemaAdapter);
    }
  }

  private void saveChangesOfPotentialMultiProperty(
    final IField p,
    final IDataAndSchemaAdapter dataAndSchemaAdapter) {
    if (FIELD_TOOL.isNewOrEdited(p)) {
      saveChangesOfPotentialMultiPropertyWhenIsNewOrEdited(p, dataAndSchemaAdapter);
    }
  }

  private void saveChangesOfPotentialMultiPropertyWhenIsNewOrEdited(
    final IField field,
    final IDataAndSchemaAdapter dataAndSchemaAdapter) {
    switch (field.getType()) {
      case MULTI_VALUE:
        MULTI_VALUE_SAVER.saveChangesOfMultiValue((IMultiValue<?>) field, dataAndSchemaAdapter);
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
