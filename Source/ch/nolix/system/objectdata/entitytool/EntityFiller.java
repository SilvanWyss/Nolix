package ch.nolix.system.objectdata.entitytool;

import ch.nolix.systemapi.middata.model.EntityLoadingDto;
import ch.nolix.systemapi.objectdata.entitytool.IEntityContentFieldsFiller;
import ch.nolix.systemapi.objectdata.entitytool.IEntityFiller;
import ch.nolix.systemapi.objectdata.model.IEntity;

/**
 * @author Silvan Wyss
 */
public final class EntityFiller implements IEntityFiller {
  private static final IEntityContentFieldsFiller ENTITY_CONTENT_FIELDS_FILLER = new EntityContentFieldsFiller();

  /**
   * {@inheritDoc}
   */
  @Override
  public void fillUpEntityFromEntityLoadingDto(final IEntity entity, final EntityLoadingDto entityLoadingDto) {
    final var id = entityLoadingDto.id();
    final var saveStamp = entityLoadingDto.saveStamp();
    final var contentFields = entityLoadingDto.contentFields();

    entity.internalSetLoadedAndIdAndSaveStamp(id, saveStamp);

    ENTITY_CONTENT_FIELDS_FILLER.fillUpEntityContentFieldsFromContentFieldDtos(entity, contentFields);
  }
}
