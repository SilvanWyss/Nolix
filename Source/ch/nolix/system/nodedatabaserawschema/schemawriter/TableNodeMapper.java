//package declaration
package ch.nolix.system.nodedatabaserawschema.schemawriter;

//own imports
import ch.nolix.core.document.node.Node;
import ch.nolix.system.nodedatabaserawschema.structure.SubNodeHeaderCatalogue;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.ITableDTO;

//class
public final class TableNodeMapper {
	
	//static attribute
	private static final ColumnNodeMapper columnNodeMapper = new ColumnNodeMapper();
	
	//method
	public Node createTableNodeFrom(final ITableDTO table) {
		
		final var node = new Node();
		
		node
		.setHeader(SubNodeHeaderCatalogue.TABLE)
		.addAttribute(createIdNodeFrom(table), createNameNodeFrom(table))
		.addAttributes(createColumnNodesFrom(table));
		
		return node;
	}
	
	//method
	private Node createIdNodeFrom(final ITableDTO table) {
		return Node.withHeaderAndAttribute(SubNodeHeaderCatalogue.ID, table.getId());
	}
	
	//method
	private Node createNameNodeFrom(final ITableDTO table) {
		return Node.withHeaderAndAttribute(SubNodeHeaderCatalogue.NAME, table.getName());
	}
	
	//method
	private Iterable<Node> createColumnNodesFrom(final ITableDTO table) {
		return table.getColumns().to(columnNodeMapper::createColumnNodeFrom);
	}
}
