package ch.nolix.system.noderawschema.nodemapper;

import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.document.node.Node;
import ch.nolix.coreapi.containerapi.listapi.ILinkedList;
import ch.nolix.coreapi.documentapi.nodeapi.INode;
import ch.nolix.systemapi.noderawschemaapi.databasestructureapi.NodeHeaderCatalog;
import ch.nolix.systemapi.noderawschemaapi.nodemapperapi.ITableNodeMapper;
import ch.nolix.systemapi.rawschemaapi.modelapi.TableDto;

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
