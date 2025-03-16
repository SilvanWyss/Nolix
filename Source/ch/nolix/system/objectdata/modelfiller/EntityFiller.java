package ch.nolix.system.objectdata.modelfiller;

import ch.nolix.systemapi.middataapi.modelapi.EntityLoadingDto;
import ch.nolix.systemapi.objectdataapi.modelapi.IEntity;
import ch.nolix.systemapi.objectdataapi.modelfillerapi.IEntityFiller;

/**
 * @author Silvan Wyss
 * @version 2024-12-21
 */
public final class EntityFiller implements IEntityFiller {

  /**
   * {@inheritDoc}
   */
  @Override
  public void fillUpEntityFromEntityLoadingDto(final IEntity entity, final EntityLoadingDto entityLoadingDto) {

    final var id = entityLoadingDto.id();
    final var saveStamp = entityLoadingDto.saveStamp();
    final var contentFields = entityLoadingDto.contentFields();
    final var fields = entity.internalGetStoredFields();

    entity.internalSetLoadedAndIdAndSaveStamp(id, saveStamp);

    for (final var f : contentFields) {

      final var field = fields.getStoredFirst(f2 -> f2.hasName(f.columnName()));

      field.internalSetOptionalContent(f.optionalContent());
    }
  }
}
