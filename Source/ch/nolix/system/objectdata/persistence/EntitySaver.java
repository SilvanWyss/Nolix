package ch.nolix.system.objectdata.persistence;

import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.system.objectdata.middatamodelmapper.EntityDtoMapper;
import ch.nolix.systemapi.middata.adapter.IDataAdapterAndSchemaReader;
import ch.nolix.systemapi.objectdata.middatamodelmapper.IEntityDtoMapper;
import ch.nolix.systemapi.objectdata.model.IEntity;
import ch.nolix.systemapi.objectdata.perstistence.IEntitySaver;
import ch.nolix.systemapi.objectdata.perstistence.IMultiFieldSaver;

/**
 * @author Silvan Wyss
 */
public final class EntitySaver implements IEntitySaver {
  private static final IEntityDtoMapper ENTITY_DTO_MAPPER = new EntityDtoMapper();

  private static final IMultiFieldSaver MULTI_FIELD_SAVER = new MultiFieldSaver();

  /**
   * {@inheritDoc}
   */
  @Override
  public void saveEntityChanges(final IEntity entity, final IDataAdapterAndSchemaReader dataAndSchemaAdapter) {
    final var entityState = entity.getState();

    switch (entityState) {
      case NEW:
        saveEntityCreation(entity, dataAndSchemaAdapter);
        break;
      case UNEDITED:
        break;
      case EDITED:
        saveEntityUpdates(entity, dataAndSchemaAdapter);
        break;
      case DELETED:
        saveEntityDeletion(entity, dataAndSchemaAdapter);
        break;
      default:
        throw InvalidArgumentException.forArgumentAndArgumentName(entityState, "entity state");
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void saveEntityCreation(final IEntity entity, final IDataAdapterAndSchemaReader dataAndSchemaAdapter) {
    final var tableName = entity.getStoredParentTable().getName();
    final var entityCreationDto = ENTITY_DTO_MAPPER.mapEntityToEntityCreationDto(entity);

    dataAndSchemaAdapter.insertEntity(tableName, entityCreationDto);
    MULTI_FIELD_SAVER.saveMultiFieldChangesOfEntity(entity, dataAndSchemaAdapter);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void saveEntityDeletion(final IEntity entity, final IDataAdapterAndSchemaReader dataAndSchemaAdapter) {
    final var tableName = entity.getStoredParentTable().getName();
    final var entityDeletionDto = ENTITY_DTO_MAPPER.mapEntityToEntityDeletionDto(entity);

    dataAndSchemaAdapter.deleteEntity(tableName, entityDeletionDto);
    MULTI_FIELD_SAVER.saveMultiFieldChangesOfEntity(entity, dataAndSchemaAdapter);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void saveEntityUpdates(final IEntity entity, final IDataAdapterAndSchemaReader dataAndSchemaAdapter) {
    final var tableName = entity.getStoredParentTable().getName();
    final var entityUpdateDto = ENTITY_DTO_MAPPER.mapEntityToEntityUpdateDto(entity);

    dataAndSchemaAdapter.updateEntity(tableName, entityUpdateDto);
    MULTI_FIELD_SAVER.saveMultiFieldChangesOfEntity(entity, dataAndSchemaAdapter);
  }
}
