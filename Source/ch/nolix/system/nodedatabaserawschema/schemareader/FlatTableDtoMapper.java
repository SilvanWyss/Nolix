//package declaration
package ch.nolix.system.nodedatabaserawschema.schemareader;

//own imports
import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;
import ch.nolix.system.nodedatabaserawschema.structure.TableNodeSearcher;
import ch.nolix.system.objectschema.flatschemadto.FlatTableDto;

//class
final class FlatTableDtoMapper {
	
	//constant
	private static final TableNodeSearcher TABLE_NODE_SEARCHER = new TableNodeSearcher();
	
	//method
	public FlatTableDto createFlatTableDtoFromTableNode(final IMutableNode<?> tableNode) {
		
		final var id = TABLE_NODE_SEARCHER.getOriIdNodeFromTableNode(tableNode).getSingleChildNodeHeader();
		final var name = TABLE_NODE_SEARCHER.getOriNameNodeFromTableNode(tableNode).getSingleChildNodeHeader();
		
		return new FlatTableDto(id, name);
	}
}
