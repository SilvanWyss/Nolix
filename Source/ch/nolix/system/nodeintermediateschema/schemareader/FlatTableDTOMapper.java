//package declaration
package ch.nolix.system.nodeintermediateschema.schemareader;

//own imports
import ch.nolix.common.document.node.BaseNode;
import ch.nolix.system.nodeintermediateschema.structure.TableNodeSearcher;
import ch.nolix.system.objectschema.flatschemadto.FlatTableDTO;

//class
final class FlatTableDTOMapper {
	
	//static attribute
	private static final TableNodeSearcher tableNodeSearcher = new TableNodeSearcher();
	
	//method
	public FlatTableDTO createFlatTableDTOFromTableNode(final BaseNode tableNode) {
		
		final var name = tableNodeSearcher.getNameNodeFromTableNode(tableNode).getOneAttributeHeader();
		
		return new FlatTableDTO(name);
	}
}
