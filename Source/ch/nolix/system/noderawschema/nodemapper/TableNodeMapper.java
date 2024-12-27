package ch.nolix.system.noderawschema.nodemapper;

import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.document.node.Node;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.containerapi.listapi.ILinkedList;
import ch.nolix.coreapi.documentapi.nodeapi.INode;
import ch.nolix.systemapi.noderawschemaapi.databasestructureapi.NodeHeaderCatalogue;
import ch.nolix.systemapi.noderawschemaapi.nodemapperapi.IColumnNodeMapper;
import ch.nolix.systemapi.noderawschemaapi.nodemapperapi.ITableNodeMapper;
import ch.nolix.systemapi.rawschemaapi.dto.TableDto;

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
    childNodes.addAtEnd(createIdNodeFromTableDto(tableDto));
    childNodes.addAtEnd(createNameNodeFromTableDto(tableDto));
    childNodes.addAtEnd(createColumnNodesFromTableDto(tableDto));

    return Node.withHeaderAndChildNodes(NodeHeaderCatalogue.TABLE, childNodes);
  }

  private INode<?> createIdNodeFromTableDto( //NOSONAR: This method is an instance method.
    final TableDto tableDto) {
    return Node.withHeaderAndChildNode(NodeHeaderCatalogue.ID, tableDto.id());
  }

  private INode<?> createNameNodeFromTableDto( //NOSONAR: This method is an instance method.
    final TableDto tableDto) {
    return Node.withHeaderAndChildNode(NodeHeaderCatalogue.NAME, tableDto.name());
  }

  private IContainer<INode<?>> createColumnNodesFromTableDto( //NOSONAR: This method is an instance method.
    final TableDto tableDto) {
    return tableDto.columns().to(COLUMN_NODE_MAPPER::mapColumnDtoToNode);
  }
}
