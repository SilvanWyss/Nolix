package ch.nolix.systemapi.nodemiddataapi.nodemapperapi;

import ch.nolix.coreapi.documentapi.nodeapi.INode;
import ch.nolix.systemapi.middataapi.modelapi.EntityCreationDto;

/**
 * @author Silvan Wyss
 * @version 2025-01-12
 */
public interface IEntityIndexNodeMapper {

  /**
   * @param entityCreationDto
   * @param tableId
   * @return a new entity index node from the given entityCreationDto.
   * @throws RuntimeException if the given entityCreationDto is null.
   */
  INode<?> mapEntityCreationDtoToEntityIndexNode(EntityCreationDto entityCreationDto, String tableId);
}
