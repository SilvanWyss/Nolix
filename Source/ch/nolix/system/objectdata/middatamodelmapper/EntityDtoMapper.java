package ch.nolix.system.objectdata.middatamodelmapper;

import ch.nolix.system.objectdata.modelsearcher.EntitySearcher;
import ch.nolix.systemapi.middata.model.EntityCreationDto;
import ch.nolix.systemapi.middata.model.EntityDeletionDto;
import ch.nolix.systemapi.middata.model.EntityUpdateDto;
import ch.nolix.systemapi.objectdata.middatamodelmapper.IEntityDtoMapper;
import ch.nolix.systemapi.objectdata.middatamodelmapper.IStringContentFieldDtoMapper;
import ch.nolix.systemapi.objectdata.model.IEntity;
import ch.nolix.systemapi.objectdata.modelsearcher.IEntitySearcher;

/**
 * @author Silvan Wyss
 * @version 2024-12-25
 */
public final class EntityDtoMapper implements IEntityDtoMapper {

  private static final IEntitySearcher ENTITY_SEARCHER = new EntitySearcher();

  private static final IStringContentFieldDtoMapper STRING_CONTENT_FIELD_DTO_MAPPER = new StringContentFieldDtoMapper();

  /**
   * {@inheritDoc}
   */
  @Override
  public EntityCreationDto mapEntityToEntityCreationDto(final IEntity entity) {
    return //
    new EntityCreationDto(
      entity.getId(),
      entity.internalGetStoredFields().to(STRING_CONTENT_FIELD_DTO_MAPPER::mapFieldToStringContentFieldDto));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public EntityDeletionDto mapEntityToEntityDeletionDto(final IEntity entity) {
    return new EntityDeletionDto(entity.getId(), entity.getSaveStamp());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public EntityUpdateDto mapEntityToEntityUpdateDto(final IEntity entity) {
    return //
    new EntityUpdateDto(
      entity.getId(),
      entity.getSaveStamp(),
      ENTITY_SEARCHER
        .getStoredEditedFields(entity)
        .to(STRING_CONTENT_FIELD_DTO_MAPPER::mapFieldToStringContentFieldDto));
  }
}
