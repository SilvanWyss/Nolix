//package declaration
package ch.nolix.system.noderawschema.schemawriter;

//own imports
import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.document.node.Node;
import ch.nolix.system.noderawschema.structure.StructureHeaderCatalogue;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.ITableDto;

//class
public final class TableNodeMapper {

  //constant
  private static final ColumnNodeMapper COLUMN_NODE_MAPPER = new ColumnNodeMapper();

  //method
  public Node createTableNodeFrom(final ITableDto table) {

    final var childNodes = LinkedList.withElement(createIdNodeFrom(table), createNameNodeFrom(table));
    childNodes.addAtEnd(createColumnNodesFrom(table));

    return Node.withHeaderAndChildNodes(StructureHeaderCatalogue.TABLE, childNodes);
  }

  //method
  private Node createIdNodeFrom(final ITableDto table) {
    return Node.withHeaderAndChildNode(StructureHeaderCatalogue.ID, table.getId());
  }

  //method
  private Node createNameNodeFrom(final ITableDto table) {
    return Node.withHeaderAndChildNode(StructureHeaderCatalogue.NAME, table.getName());
  }

  //method
  private Iterable<Node> createColumnNodesFrom(final ITableDto table) {
    return table.getColumns().to(COLUMN_NODE_MAPPER::createColumnNodeFrom);
  }
}
