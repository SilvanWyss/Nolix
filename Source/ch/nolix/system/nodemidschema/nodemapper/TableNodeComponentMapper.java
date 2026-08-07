/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.nodemidschema.nodemapper;

import ch.nolix.base.document.node.ImmutableNode;
import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.baseapi.document.node.Node;
import ch.nolix.systemapi.midschema.model.TableDto;
import ch.nolix.systemapi.nodemidschema.databasestructure.NodeHeaderCatalog;

/**
 * @author Silvan Wyss
 */
public final class TableNodeComponentMapper {
  private static final ColumnNodeMapper COLUMN_NODE_MAPPER = new ColumnNodeMapper();

  private TableNodeComponentMapper() {
  }

  public static ExtendedIterable<Node<?>> mapTableDtoToColumnNodes(final TableDto tableDto) {
    return tableDto.columns().to(COLUMN_NODE_MAPPER::mapColumnDtoToColumnNode);
  }

  public static Node<?> mapTableDtoToIdNode(final TableDto tableDto) {
    return ImmutableNode.withHeaderAndChildNode(NodeHeaderCatalog.ID, tableDto.id());
  }

  public static Node<?> mapTableDtoToNameNode(final TableDto tableDto) {
    return ImmutableNode.withHeaderAndChildNode(NodeHeaderCatalog.NAME, tableDto.name());
  }
}
