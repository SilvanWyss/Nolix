//package declaration
package ch.nolix.system.nodeintermediateschema.schemawriter;

//own imports
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.document.node.Node;
import ch.nolix.system.nodeintermediateschema.structure.SubNodeHeaderCatalogue;
import ch.nolix.techapi.intermediateschemaapi.schemadtoapi.ITableDTO;

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
		
		final var columnNodes = new LinkedList<Node>();
		columnNodes.addAtEnd(createIdColumnNode());
		columnNodes.addAtEnd(createSaveStampColumnNode());
		columnNodes.addAtEnd(table.getColumns().to(columnNodeMapper::createColumnNodeFrom));
		
		return columnNodes;
	}
	
	//method
	private Node createIdColumnNode() {
		return
		Node.withHeaderAndAttribute(
			SubNodeHeaderCatalogue.COLUMN,
			Node.withHeaderAndAttribute(SubNodeHeaderCatalogue.HEADER, SubNodeHeaderCatalogue.ID),
			Node.withHeaderAndAttribute(SubNodeHeaderCatalogue.DATA_TYPE, SubNodeHeaderCatalogue.STRING)
		);
	}
	
	//method
	private Node createSaveStampColumnNode() {
		return
		Node.withHeaderAndAttribute(
			SubNodeHeaderCatalogue.COLUMN,
			Node.withHeaderAndAttribute(SubNodeHeaderCatalogue.HEADER, SubNodeHeaderCatalogue.SAVE_STAMP),
			Node.withHeaderAndAttribute(SubNodeHeaderCatalogue.DATA_TYPE, SubNodeHeaderCatalogue.BIG_DECIMAL)
		);
	}
}
