//package declaration
package ch.nolix.system.objectdata.data;

import ch.nolix.system.objectdata.datatool.EntityTool;
import ch.nolix.system.objectdata.propertytool.PropertyTool;
import ch.nolix.systemapi.objectdataapi.dataapi.IEntity;
import ch.nolix.systemapi.objectdataapi.dataapi.IMultiReference;
import ch.nolix.systemapi.objectdataapi.dataapi.IMultiValue;
import ch.nolix.systemapi.objectdataapi.dataapi.IProperty;
import ch.nolix.systemapi.objectdataapi.datatoolapi.IEntityTool;
import ch.nolix.systemapi.objectdataapi.propertytoolapi.IPropertyTool;

//class
public final class EntitySaver {

  //constant
  private static final IEntityTool ENTITY_TOOL = new EntityTool();

  //constant
  private static final IPropertyTool PROPERTY_TOOL = new PropertyTool();

  //constant
  private static final MultiValueSaver MULTI_VALUE_SAVER = new MultiValueSaver();

  //constant
  private static final MultiReferenceSaver MULTI_REFERENCE_SAVER = new MultiReferenceSaver();

  //method
  public void saveChangesOfEntity(final IEntity entity, final Database database) {
    switch (entity.getState()) {
      case NEW:
        saveNewEntity(entity, database);
        break;
      case EDITED:
        saveChangesOfEditedEntity(entity, database);
        break;
      case DELETED:
        saveEntityDeletion(entity, database);
        break;
      default:
        //Does nothing.
    }
  }

  //method
  private void saveNewEntity(final IEntity newEntity, final Database database) {

    database.internalGetRefDataAndSchemaAdapter().insertEntity(
      newEntity.getParentTableName(),
      ENTITY_TOOL.createNewEntityDtoForEntity(newEntity));

    saveMultiPropertyChangesOfEntity(newEntity, database);
  }

  //method
  private void saveChangesOfEditedEntity(final IEntity editedEntity, final Database database) {

    database.internalGetRefDataAndSchemaAdapter().updateEntity(
      editedEntity.getParentTableName(),
      ENTITY_TOOL.createEntityUpdateDtoForEntity(editedEntity));

    saveMultiPropertyChangesOfEntity(editedEntity, database);
  }

  //method
  private void saveEntityDeletion(final IEntity deletedEntity, final Database database) {
    database.internalGetRefDataAndSchemaAdapter().deleteEntity(
      deletedEntity.getStoredParentTable().getName(),
      ENTITY_TOOL.createEntityHeadDtoForEntity(deletedEntity));
  }

  //method
  private void saveMultiPropertyChangesOfEntity(
    final IEntity entity,
    final Database database) {
    for (final var p : entity.technicalGetStoredProperties()) {
      saveChangesOfPotentialMultiProperty(database, p);
    }
  }

  //method
  private void saveChangesOfPotentialMultiProperty(final Database database, final IProperty p) {
    if (PROPERTY_TOOL.isNewOrEdited(p)) {
      saveChangesOfPotentialMultiPropertyWhenIsNewOrEdited(database, p);
    }
  }

  //method
  private void saveChangesOfPotentialMultiPropertyWhenIsNewOrEdited(final Database database,
    final IProperty p) {
    switch (p.getType()) {
      case MULTI_VALUE:
        MULTI_VALUE_SAVER.saveChangesOfMultiValue((IMultiValue<?>) p, database);
        break;
      case MULTI_REFERENCE:
        MULTI_REFERENCE_SAVER.saveChangesOfMultiReference((IMultiReference<?>) p, database);
        break;
      default:
        //Does nothing.
    }
  }
}
