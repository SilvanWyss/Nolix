package ch.nolix.systemapi.objectdataapi.datafillerapi;

import ch.nolix.systemapi.objectdataapi.dataapi.IEntity;
import ch.nolix.systemapi.rawdataapi.datadto.EntityLoadingDto;

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