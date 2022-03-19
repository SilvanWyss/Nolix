//package declaration
package ch.nolix.system.nodedatabaserawschema.schemareader;

//own imports
import ch.nolix.core.document.node.BaseNode;
import ch.nolix.system.nodedatabaserawschema.structure.TableNodeSearcher;
import ch.nolix.system.objectschema.flatschemadto.FlatTableDTO;

//class
final class FlatTableDTOMapper {
	
	//static attribute
	private static final TableNodeSearcher tableNodeSearcher = new TableNodeSearcher();
	
	//method
	public FlatTableDTO createFlatTableDTOFromTableNode(final BaseNode tableNode) {
		
		final var id = tableNodeSearcher.getRefIdNodeFromTableNode(tableNode).getOneAttributeHeader();
		final var name = tableNodeSearcher.getRefNameNodeFromTableNode(tableNode).getOneAttributeHeader();
		
		return new FlatTableDTO(id, name);
	}
}
