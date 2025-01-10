package ch.nolix.systemapi.objectdataapi.modelfillerapi;

import ch.nolix.systemapi.objectdataapi.modelapi.IEntity;
import ch.nolix.systemapi.rawdataapi.model.EntityLoadingDto;

/**
 * @author Silvan Wyss
 * @version 2024-12-21
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
