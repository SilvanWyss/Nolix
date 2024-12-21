package ch.nolix.system.noderawschema.schemawriter;

import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.document.node.Node;
import ch.nolix.systemapi.noderawschemaapi.databasestructureapi.NodeHeaderCatalogue;
import ch.nolix.systemapi.rawschemaapi.schemadto.TableDto;

public final class TableNodeMapper {

  private static final ColumnNodeMapper COLUMN_NODE_MAPPER = new ColumnNodeMapper();

  public Node createTableNodeFrom(final TableDto table) {

    final var childNodes = LinkedList.withElement(createIdNodeFrom(table), createNameNodeFrom(table));
    childNodes.addAtEnd(createColumnNodesFrom(table));

    return Node.withHeaderAndChildNodes(NodeHeaderCatalogue.TABLE, childNodes);
  }

  private Node createIdNodeFrom(final TableDto table) {
    return Node.withHeaderAndChildNode(NodeHeaderCatalogue.ID, table.id());
  }

  private Node createNameNodeFrom(final TableDto table) {
    return Node.withHeaderAndChildNode(NodeHeaderCatalogue.NAME, table.name());
  }

  private Iterable<Node> createColumnNodesFrom(final TableDto table) {
    return table.columns().to(COLUMN_NODE_MAPPER::createColumnNodeFrom);
  }
}
