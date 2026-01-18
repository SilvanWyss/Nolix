/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.nodemiddata.nodemapper;

import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.coreapi.document.node.INode;
import ch.nolix.systemapi.middata.model.EntityCreationDto;
import ch.nolix.systemapi.midschemaview.model.TableViewDto;

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
  IContainer<INode<?>> mapEntityCreationDtoToFieldNodes(
    EntityCreationDto entityCreationDto,
    long saveStamp,
    TableViewDto tableView);
}
