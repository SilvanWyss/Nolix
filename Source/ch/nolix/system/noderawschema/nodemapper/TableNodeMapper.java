package ch.nolix.system.noderawschema.nodemapper;

import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.document.node.Node;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.containerapi.listapi.ILinkedList;
import ch.nolix.coreapi.documentapi.nodeapi.INode;
import ch.nolix.systemapi.noderawschemaapi.databasestructureapi.NodeHeaderCatalog;
import ch.nolix.systemapi.noderawschemaapi.nodemapperapi.IColumnNodeMapper;
import ch.nolix.systemapi.noderawschemaapi.nodemapperapi.ITableNodeMapper;
import ch.nolix.systemapi.rawschemaapi.modelapi.TableDto;

/**
 * @author Silvan Wyss
 * @version 2021-09-12
 */
public final class TableNodeMapper implements ITableNodeMapper {

  private static final IColumnNodeMapper COLUMN_NODE_MAPPER = new ColumnNodeMapper();

  /**
   * {@inheritDoc}
   */
  @Override
  public INode<?> mapTableDtoToNode(final TableDto tableDto) {

    final ILinkedList<INode<?>> childNodes = LinkedList.createEmpty();
    childNodes.addAtEnd(mapTableDtoToIdNode(tableDto));
    childNodes.addAtEnd(mapTableDtoToNameNode(tableDto));
    childNodes.addAtEnd(mapColumnDtoToColumnNodes(tableDto));

    return Node.withHeaderAndChildNodes(NodeHeaderCatalog.TABLE, childNodes);
  }

  private INode<?> mapTableDtoToIdNode(
    final TableDto tableDto) {
    return Node.withHeaderAndChildNode(NodeHeaderCatalog.ID, tableDto.id());
  }

  private INode<?> mapTableDtoToNameNode(
    final TableDto tableDto) {
    return Node.withHeaderAndChildNode(NodeHeaderCatalog.NAME, tableDto.name());
  }

  private IContainer<INode<?>> mapColumnDtoToColumnNodes(
    final TableDto tableDto) {
    return tableDto.columns().to(COLUMN_NODE_MAPPER::mapColumnDtoToColumnNode);
  }
}
