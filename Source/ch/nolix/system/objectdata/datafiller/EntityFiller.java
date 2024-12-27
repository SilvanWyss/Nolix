package ch.nolix.system.objectdata.datafiller;

import ch.nolix.systemapi.objectdataapi.datafillerapi.IEntityFiller;
import ch.nolix.systemapi.objectdataapi.modelapi.IEntity;
import ch.nolix.systemapi.rawdataapi.dto.EntityLoadingDto;

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

    entity.internalSetId(entityLoadingDto.id());
    entity.internalSetSaveStamp(entityLoadingDto.saveStamp());

    final var fields = entity.internalGetStoredFields();

    for (final var f : entityLoadingDto.contentFields()) {

      final var field = fields.getStoredFirst(f2 -> f2.hasName(f.columnName()));

      field.internalSetOrClearContent(f.optionalContent().orElse(null));
    }
  }
}
