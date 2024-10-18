package ch.nolix.system.noderawschema.schemawriter;

import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.document.node.Node;
import ch.nolix.system.noderawschema.structure.StructureHeaderCatalogue;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.ITableDto;

public final class TableNodeMapper {

  private static final ColumnNodeMapper COLUMN_NODE_MAPPER = new ColumnNodeMapper();

  public Node createTableNodeFrom(final ITableDto table) {

    final var childNodes = LinkedList.withElement(createIdNodeFrom(table), createNameNodeFrom(table));
    childNodes.addAtEnd(createColumnNodesFrom(table));

    return Node.withHeaderAndChildNodes(StructureHeaderCatalogue.TABLE, childNodes);
  }

  private Node createIdNodeFrom(final ITableDto table) {
    return Node.withHeaderAndChildNode(StructureHeaderCatalogue.ID, table.getId());
  }

  private Node createNameNodeFrom(final ITableDto table) {
    return Node.withHeaderAndChildNode(StructureHeaderCatalogue.NAME, table.getName());
  }

  private Iterable<Node> createColumnNodesFrom(final ITableDto table) {
    return table.getColumns().to(COLUMN_NODE_MAPPER::createColumnNodeFrom);
  }
}
