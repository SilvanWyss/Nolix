/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.nodemidschema.nodemapper;

import ch.nolix.core.document.node.Node;
import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.coreapi.document.node.INode;
import ch.nolix.systemapi.midschema.model.TableDto;
import ch.nolix.systemapi.nodemidschema.databasestructure.NodeHeaderCatalog;
import ch.nolix.systemapi.nodemidschema.nodemapper.IColumnNodeMapper;

/**
 * @author Silvan Wyss
 */
public final class TableNodeComponentMapper {
  private static final IColumnNodeMapper COLUMN_NODE_MAPPER = new ColumnNodeMapper();

  private TableNodeComponentMapper() {
  }

  public static IContainer<INode<?>> mapTableDtoToColumnNodes(final TableDto tableDto) {
    return tableDto.columns().to(COLUMN_NODE_MAPPER::mapColumnDtoToColumnNode);
  }

  public static INode<?> mapTableDtoToIdNode(final TableDto tableDto) {
    return Node.withHeaderAndChildNode(NodeHeaderCatalog.ID, tableDto.id());
  }

  public static INode<?> mapTableDtoToNameNode(final TableDto tableDto) {
    return Node.withHeaderAndChildNode(NodeHeaderCatalog.NAME, tableDto.name());
  }
}
