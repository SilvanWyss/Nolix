/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.nodemiddata.nodemapper;

import ch.nolix.coreapi.document.node.INode;
import ch.nolix.systemapi.middata.model.EntityCreationDto;
import ch.nolix.systemapi.midschemaview.model.TableViewDto;

/**
 * @author Silvan Wyss
 */
public interface IEntityNodeMapper {
  /**
   * @param entityCreationDto
   * @param tableView
   * @param saveStamp
   * @return a new entity node from the given entityCreationDto.
   * @throws RuntimeException if the given entityCreationDto is null.
   */
  INode<?> mapEntityCreationDtoToEntityNode(
    EntityCreationDto entityCreationDto,
    TableViewDto tableView,
    long saveStamp);
}
