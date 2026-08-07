/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.nodemiddata.nodemapper;

import ch.nolix.base.document.node.ImmutableNode;
import ch.nolix.baseapi.document.node.Node;
import ch.nolix.systemapi.middata.model.EntityCreationDto;
import ch.nolix.systemapi.midschemainfo.model.TableInfoDto;
import ch.nolix.systemapi.nodemiddata.nodemapper.IEntityNodeMapper;
import ch.nolix.systemapi.nodemidschema.databasestructure.NodeHeaderCatalog;

/**
 * @author Silvan Wyss
 */
public final class EntityNodeMapper implements IEntityNodeMapper {
  private static final FieldNodeMapper FIELD_NODE_MAPPER = new FieldNodeMapper();

  @Override
  /**
   * {@inheritDoc}
   */
  public Node<?> mapEntityCreationDtoToEntityNode(
    final EntityCreationDto newEntity,
    final TableInfoDto tableView,
    final long saveStamp) {
    final var entityHeader = NodeHeaderCatalog.ENTITY;
    final var fieldNodes = FIELD_NODE_MAPPER.mapEntityCreationDtoToFieldNodes(newEntity, saveStamp, tableView);

    return ImmutableNode.withHeaderAndChildNodes(entityHeader, fieldNodes);
  }
}
