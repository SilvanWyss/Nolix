/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.nodemiddata.nodemapper;

import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.baseapi.document.node.Node;
import ch.nolix.systemapi.middata.model.EntityCreationDto;
import ch.nolix.systemapi.midschemainfo.model.TableInfoDto;

/**
 * @author Silvan Wyss
 */
public interface IFieldNodeMapper {
  /**
   * @param entityCreationDto
   * @param saveStamp
   * @param tableView
   * @return new field nodes from the given entityCreationDto
   */
  ExtendedIterable<Node<?>> mapEntityCreationDtoToFieldNodes(
    EntityCreationDto entityCreationDto,
    long saveStamp,
    TableInfoDto tableView);
}
