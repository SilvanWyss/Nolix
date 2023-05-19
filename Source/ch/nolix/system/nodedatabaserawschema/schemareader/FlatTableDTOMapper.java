//package declaration
package ch.nolix.system.nodedatabaserawschema.schemareader;

//own imports
import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;
import ch.nolix.system.nodedatabaserawschema.structure.TableNodeSearcher;
import ch.nolix.system.objectschema.flatschemadto.FlatTableDTO;

//class
final class FlatTableDTOMapper {
	
	//static attribute
	private static final TableNodeSearcher tableNodeSearcher = new TableNodeSearcher();
	
	//method
	public FlatTableDTO createFlatTableDTOFromTableNode(final IMutableNode<?> tableNode) {
		
		final var id = tableNodeSearcher.getOriIdNodeFromTableNode(tableNode).getSingleChildNodeHeader();
		final var name = tableNodeSearcher.getOriNameNodeFromTableNode(tableNode).getSingleChildNodeHeader();
		
		return new FlatTableDTO(id, name);
	}
}
