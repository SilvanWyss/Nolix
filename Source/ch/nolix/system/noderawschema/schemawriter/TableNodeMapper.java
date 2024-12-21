package ch.nolix.system.noderawschema.schemawriter;

import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.document.node.Node;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.documentapi.nodeapi.INode;
import ch.nolix.system.noderawschema.nodemapper.ColumnNodeMapper;
import ch.nolix.systemapi.noderawschemaapi.databasestructureapi.NodeHeaderCatalogue;
import ch.nolix.systemapi.noderawschemaapi.nodemapperapi.IColumnNodeMapper;
import ch.nolix.systemapi.rawschemaapi.schemadto.TableDto;

public final class TableNodeMapper {

  private static final IColumnNodeMapper COLUMN_NODE_MAPPER = new ColumnNodeMapper();

  public INode<?> createTableNodeFrom(final TableDto table) {

    @SuppressWarnings("unchecked")
    final var childNodes = LinkedList.withElement(createIdNodeFrom(table), createNameNodeFrom(table));

    childNodes.addAtEnd(createColumnNodesFrom(table));

    return Node.withHeaderAndChildNodes(NodeHeaderCatalogue.TABLE, childNodes);
  }

  private INode<?> createIdNodeFrom(final TableDto table) {
    return Node.withHeaderAndChildNode(NodeHeaderCatalogue.ID, table.id());
  }

  private INode<?> createNameNodeFrom(final TableDto table) {
    return Node.withHeaderAndChildNode(NodeHeaderCatalogue.NAME, table.name());
  }

  private IContainer<INode<?>> createColumnNodesFrom(final TableDto table) {
    return table.columns().to(COLUMN_NODE_MAPPER::mapColumnDtoToNode);
  }
}
