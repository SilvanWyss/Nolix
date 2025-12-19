package ch.nolix.systemapi.objectdata.entitytool;

import ch.nolix.systemapi.middata.model.EntityLoadingDto;
import ch.nolix.systemapi.objectdata.model.IEntity;

/**
 * @author Silvan Wyss
 */
public interface IEntityFiller {
  /**
   * Fills up the given entity from the given entityLoadingDto.
   * 
   * @param entity
   * @param entityLoadingDto
   * @throws RuntimeException if the given entity is not valid.
   * @throws RuntimeException if the given entityLoadingDto is not valid.
   */
  void fillUpEntityFromEntityLoadingDto(IEntity entity, EntityLoadingDto entityLoadingDto);
}
