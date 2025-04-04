package ch.nolix.systemapi.objectdataapi.middatamodelmapperapi;

import ch.nolix.systemapi.middataapi.modelapi.EntityCreationDto;
import ch.nolix.systemapi.middataapi.modelapi.EntityDeletionDto;
import ch.nolix.systemapi.middataapi.modelapi.EntityUpdateDto;
import ch.nolix.systemapi.objectdataapi.modelapi.IEntity;

/**
 * @author Silvan Wyss
 * @version 2024-12-25
 */
public interface IEntityDtoMapper {

  /**
   * @param entity
   * @return a new {@link EntityCreationDto} from the given entity.
   * @throws RuntimeException if the given entity is null.
   */
  EntityCreationDto mapEntityToEntityCreationDto(IEntity entity);

  /**
   * @param entity
   * @return a new {@link EntityDeletionDto} from the given entity.
   * @throws RuntimeException if the given entity is null.
   */
  EntityDeletionDto mapEntityToEntityDeletionDto(IEntity entity);

  /**
   * @param entity
   * @return a new {@link EntityUpdateDto} from the given entity.
   * @throws RuntimeException if the given entity is null.
   */
  EntityUpdateDto mapEntityToEntityUpdateDto(IEntity entity);
}
