package ch.nolix.system.objectdata.middatamodelmapper;

import ch.nolix.system.objectdata.modelsearcher.EntitySearcher;
import ch.nolix.systemapi.middata.model.EntityCreationDto;
import ch.nolix.systemapi.middata.model.EntityDeletionDto;
import ch.nolix.systemapi.middata.model.EntityUpdateDto;
import ch.nolix.systemapi.objectdata.middatamodelmapper.IEntityDtoMapper;
import ch.nolix.systemapi.objectdata.middatamodelmapper.IStringRepresentedFieldDtoMapper;
import ch.nolix.systemapi.objectdata.model.IEntity;
import ch.nolix.systemapi.objectdata.modelsearcher.IEntitySearcher;

/**
 * @author Silvan Wyss
 * @version 2024-12-25
 */
public final class EntityDtoMapper implements IEntityDtoMapper {

  private static final IEntitySearcher ENTITY_SEARCHER = new EntitySearcher();

  private static final IStringRepresentedFieldDtoMapper STRING_CONTENT_FIELD_DTO_MAPPER = //
  new StringContentFieldDtoMapper();

  /**
   * {@inheritDoc}
   */
  @Override
  public EntityCreationDto mapEntityToEntityCreationDto(final IEntity entity) {

    final var id = entity.getId();
    final var entityFields = entity.internalGetStoredFields();
    final var contentFields = entityFields.to(STRING_CONTENT_FIELD_DTO_MAPPER::mapFieldToStringRepresentedFieldDto);

    return new EntityCreationDto(id, contentFields);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public EntityDeletionDto mapEntityToEntityDeletionDto(final IEntity entity) {

    final var id = entity.getId();
    final var saveStamp = entity.getSaveStamp();

    return new EntityDeletionDto(id, saveStamp);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public EntityUpdateDto mapEntityToEntityUpdateDto(final IEntity entity) {

    final var id = entity.getId();
    final var saveStamp = entity.getSaveStamp();
    final var editedFields = ENTITY_SEARCHER.getStoredEditedFields(entity);
    final var contentFields = editedFields.to(STRING_CONTENT_FIELD_DTO_MAPPER::mapFieldToStringRepresentedFieldDto);

    return new EntityUpdateDto(id, saveStamp, contentFields);
  }
}
