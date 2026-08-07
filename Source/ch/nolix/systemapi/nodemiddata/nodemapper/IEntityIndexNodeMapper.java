/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.nodemiddata.nodemapper;

import ch.nolix.baseapi.document.node.Node;
import ch.nolix.systemapi.middata.model.EntityCreationDto;

/**
 * @author Silvan Wyss
 */
public interface IEntityIndexNodeMapper {
  /**
   * @param entityCreationDto
   * @param tableId
   * @return a new entity index node from the given entityCreationDto
   * @throws RuntimeException if the given entityCreationDto is null
   */
  Node<?> mapEntityCreationDtoToEntityIndexNode(EntityCreationDto entityCreationDto, String tableId);
}
