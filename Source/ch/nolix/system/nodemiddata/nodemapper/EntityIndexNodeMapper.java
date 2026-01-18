/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.nodemiddata.nodemapper;

import ch.nolix.core.document.node.Node;
import ch.nolix.coreapi.document.node.INode;
import ch.nolix.systemapi.middata.model.EntityCreationDto;
import ch.nolix.systemapi.nodemiddata.nodemapper.IEntityIndexNodeMapper;
import ch.nolix.systemapi.nodemidschema.databasestructure.NodeHeaderCatalog;

/**
 * @author Silvan Wyss
 */
public final class EntityIndexNodeMapper implements IEntityIndexNodeMapper {
  /**
   * {@inheritDoc}
   */
  @Override
  public INode<?> mapEntityCreationDtoToEntityIndexNode(
    final EntityCreationDto entityCreationDto,
    final String tableId) {
    return Node.withHeaderAndChildNode(NodeHeaderCatalog.ENTITY_INDEX, entityCreationDto.id(), tableId);
  }
}
