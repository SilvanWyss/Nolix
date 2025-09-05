package ch.nolix.system.nodemidschema.nodemapper;

import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.document.node.Node;
import ch.nolix.coreapi.container.list.ILinkedList;
import ch.nolix.coreapi.document.node.INode;
import ch.nolix.systemapi.midschema.model.TableDto;
import ch.nolix.systemapi.nodemidschema.databasestructure.NodeHeaderCatalog;
import ch.nolix.systemapi.nodemidschema.nodemapper.ITableNodeMapper;

/**
 * @author Silvan Wyss
 * @version 2021-09-12
 */
public final class TableNodeMapper implements ITableNodeMapper {
  /**
   * {@inheritDoc}
   */
  @Override
  public INode<?> mapTableDtoToNode(final TableDto tableDto) {
    final ILinkedList<INode<?>> childNodes = LinkedList.createEmpty();

    final var idNode = TableNodeComponentMapper.mapTableDtoToIdNode(tableDto);
    final var nameNode = TableNodeComponentMapper.mapTableDtoToNameNode(tableDto);
    final var columnNodes = TableNodeComponentMapper.mapTableDtoToColumnNodes(tableDto);

    childNodes.addAtEnd(idNode);
    childNodes.addAtEnd(nameNode);
    childNodes.addAtEnd(columnNodes);

    return Node.withHeaderAndChildNodes(NodeHeaderCatalog.TABLE, childNodes);
  }
}
