package ch.nolix.systemapi.objectdata.entitytool;

import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.systemapi.middata.model.EntityLoadingDto;
import ch.nolix.systemapi.middata.model.FieldDto;
import ch.nolix.systemapi.objectdata.model.IEntity;

/**
 * @author Silvan Wyss
 * @version 2025-08-22
 */
public interface IEntityContentFieldsFiller {

  /**
   * Fills up the content fields of the given entity from the given
   * contentFieldDtos.
   * 
   * @param entity
   * @param contentFieldDtos
   * @throws RuntimeException if the given entity is null.
   * @throws RuntimeException if the given contentFieldDtos is null.
   * @throws RuntimeException if one of the given contentFieldDtos is null.
   */
  void fillUpEntityContentFieldsFromContentFieldDtos(IEntity entity, IContainer<FieldDto> contentFieldDtos);

  /**
   * Fills up the content fields of the given entity from the given
   * entityLoadingDto.
   * 
   * @param entity
   * @param entityLoadingDto
   * @throws RuntimeException if the given entity is null.
   * @throws RuntimeException if the given entityLoadingDto is null.
   */
  void fillUpEntityContentFieldsFromEntityLoadingDto(IEntity entity, EntityLoadingDto entityLoadingDto);
}
