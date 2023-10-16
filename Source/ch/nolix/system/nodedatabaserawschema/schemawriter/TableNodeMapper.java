//package declaration
package ch.nolix.system.nodedatabaserawschema.schemawriter;

//own imports
import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.document.node.Node;
import ch.nolix.system.nodedatabaserawschema.structure.SubNodeHeaderCatalogue;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.ITableDto;

//class
public final class TableNodeMapper {

  //constant
  private static final ColumnNodeMapper COLUMN_NODE_MAPPER = new ColumnNodeMapper();

  //method
  public Node createTableNodeFrom(final ITableDto table) {

    final var childNodes = LinkedList.withElement(createIdNodeFrom(table), createNameNodeFrom(table));
    childNodes.addAtEnd(createColumnNodesFrom(table));

    return Node.withHeaderAndChildNodes(SubNodeHeaderCatalogue.TABLE, childNodes);
  }

  //method
  private Node createIdNodeFrom(final ITableDto table) {
    return Node.withHeaderAndChildNode(SubNodeHeaderCatalogue.ID, table.getId());
  }

  //method
  private Node createNameNodeFrom(final ITableDto table) {
    return Node.withHeaderAndChildNode(SubNodeHeaderCatalogue.NAME, table.getName());
  }

  //method
  private Iterable<Node> createColumnNodesFrom(final ITableDto table) {
    return table.getColumns().to(COLUMN_NODE_MAPPER::createColumnNodeFrom);
  }
}
