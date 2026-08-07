/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.nodemiddata.nodemapper;

import ch.nolix.base.document.node.ImmutableNode;
import ch.nolix.baseapi.document.node.Node;
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
  public Node<?> mapEntityCreationDtoToEntityIndexNode(
    final EntityCreationDto entityCreationDto,
    final String tableId) {
    return ImmutableNode.withHeaderAndChildNodes(NodeHeaderCatalog.ENTITY_INDEX, entityCreationDto.id(), tableId);
  }
}
