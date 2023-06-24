//package declaration
package ch.nolix.system.nodedatabaserawschema.schemareader;

//own imports
import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;
import ch.nolix.system.nodedatabaserawschema.structure.TableNodeSearcher;
import ch.nolix.system.objectschema.flatschemadto.FlatTableDto;

//class
final class FlatTableDtoMapper {
	
	//static attribute
	private static final TableNodeSearcher tableNodeSearcher = new TableNodeSearcher();
	
	//method
	public FlatTableDto createFlatTableDtoFromTableNode(final IMutableNode<?> tableNode) {
		
		final var id = tableNodeSearcher.getOriIdNodeFromTableNode(tableNode).getSingleChildNodeHeader();
		final var name = tableNodeSearcher.getOriNameNodeFromTableNode(tableNode).getSingleChildNodeHeader();
		
		return new FlatTableDto(id, name);
	}
}
