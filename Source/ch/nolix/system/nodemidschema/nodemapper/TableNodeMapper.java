/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.nodemidschema.nodemapper;

import ch.nolix.base.datastructure.linkedlist.LinkedList;
import ch.nolix.base.document.node.ImmutableNode;
import ch.nolix.baseapi.datastructure.list.ILinkedList;
import ch.nolix.baseapi.document.node.Node;
import ch.nolix.systemapi.midschema.model.TableDto;
import ch.nolix.systemapi.nodemidschema.databasestructure.NodeHeaderCatalog;
import ch.nolix.systemapi.nodemidschema.nodemapper.ITableNodeMapper;

/**
 * @author Silvan Wyss
 */
public final class TableNodeMapper implements ITableNodeMapper {
  /**
   * {@inheritDoc}
   */
  @Override
  public Node<?> mapTableDtoToNode(final TableDto tableDto) {
    final ILinkedList<Node<?>> childNodes = LinkedList.createEmpty();

    final var idNode = TableNodeComponentMapper.mapTableDtoToIdNode(tableDto);
    final var nameNode = TableNodeComponentMapper.mapTableDtoToNameNode(tableDto);
    final var columnNodes = TableNodeComponentMapper.mapTableDtoToColumnNodes(tableDto);

    childNodes.addAtEnd(idNode);
    childNodes.addAtEnd(nameNode);
    childNodes.addAtEnd(columnNodes);

    return ImmutableNode.withHeaderAndChildNodes(NodeHeaderCatalog.TABLE, childNodes);
  }
}
