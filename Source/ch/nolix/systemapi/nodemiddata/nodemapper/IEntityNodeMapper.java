/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.nodemiddata.nodemapper;

import ch.nolix.baseapi.document.node.Node;
import ch.nolix.systemapi.middata.model.EntityCreationDto;
import ch.nolix.systemapi.midschemainfo.model.TableInfoDto;

/**
 * @author Silvan Wyss
 */
public interface IEntityNodeMapper {
  /**
   * @param entityCreationDto
   * @param tableView
   * @param saveStamp
   * @return a new entity node from the given entityCreationDto
   * @throws RuntimeException if the given entityCreationDto is null
   */
  Node<?> mapEntityCreationDtoToEntityNode(
    EntityCreationDto entityCreationDto,
    TableInfoDto tableView,
    long saveStamp);
}
