/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.objectdata.middatamodelmapper;

import ch.nolix.systemapi.middata.model.EntityCreationDto;
import ch.nolix.systemapi.middata.model.EntityDeletionDto;
import ch.nolix.systemapi.middata.model.EntityUpdateDto;
import ch.nolix.systemapi.objectdata.model.Entity;

/**
 * @author Silvan Wyss
 */
public interface IEntityDtoMapper {
  /**
   * @param entity
   * @return a new {@link EntityCreationDto} from the given entity
   * @throws RuntimeException if the given entity is null
   */
  EntityCreationDto mapEntityToEntityCreationDto(Entity entity);

  /**
   * @param entity
   * @return a new {@link EntityDeletionDto} from the given entity
   * @throws RuntimeException if the given entity is null
   */
  EntityDeletionDto mapEntityToEntityDeletionDto(Entity entity);

  /**
   * @param entity
   * @return a new {@link EntityUpdateDto} from the given entity
   * @throws RuntimeException if the given entity is null
   */
  EntityUpdateDto mapEntityToEntityUpdateDto(Entity entity);
}
