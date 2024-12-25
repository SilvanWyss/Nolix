package ch.nolix.system.objectdata.datadtomapper;

import ch.nolix.system.objectdata.datatool.EntityTool;
import ch.nolix.systemapi.objectdataapi.dataapi.IEntity;
import ch.nolix.systemapi.objectdataapi.datadtomapperapi.IEntityDtoMapper;
import ch.nolix.systemapi.objectdataapi.datadtomapperapi.IStringContentFieldDtoMapper;
import ch.nolix.systemapi.objectdataapi.datatoolapi.IEntityTool;
import ch.nolix.systemapi.rawdataapi.datadto.EntityCreationDto;
import ch.nolix.systemapi.rawdataapi.datadto.EntityDeletionDto;
import ch.nolix.systemapi.rawdataapi.datadto.EntityUpdateDto;

/**
 * @author Silvan Wyss
 * @version 2024-12-25
 */
public final class EntityDtoMapper implements IEntityDtoMapper {

  private static final IEntityTool ENTITY_TOOL = new EntityTool();

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
      ENTITY_TOOL.getStoredEditedFields(entity).to(STRING_CONTENT_FIELD_DTO_MAPPER::mapFieldToStringContentFieldDto));
  }
}
