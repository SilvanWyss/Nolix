//package declaration
package ch.nolix.system.noderawobjectschema.schemawriter;

//own imports
import ch.nolix.common.document.node.Node;
import ch.nolix.system.noderawobjectschema.structure.SubNodeHeaderCatalogue;
import ch.nolix.techapi.rawobjectschemaapi.schemadtoapi.ITableDTO;

//class
public final class TableNodeMapper {
	
	//static attribute
	private static final ColumnNodeMapper columnNodeMapper = new ColumnNodeMapper();
	
	//method
	public Node createTableNodeFrom(final ITableDTO table) {
		
		final var node = new Node();
		
		node
		.setHeader(SubNodeHeaderCatalogue.TABLE)
		.addAttribute(createNameNodeFrom(table))
		.addAttributes(createColumnNodesFrom(table));
		
		return node;
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
