package ch.nolix.systemapi.nodemiddata.nodemapper;

import ch.nolix.coreapi.document.node.INode;
import ch.nolix.systemapi.middata.model.EntityCreationDto;

/**
 * @author Silvan Wyss
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
