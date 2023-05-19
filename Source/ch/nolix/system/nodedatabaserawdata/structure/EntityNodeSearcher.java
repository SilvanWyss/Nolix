//package declaration
package ch.nolix.system.nodedatabaserawdata.structure;

//own imports
import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;
import ch.nolix.system.nodedatabaserawdata.tabledefinition.FieldIndexCatalogue;

//class
public final class EntityNodeSearcher {
	
	//method
	public String getEntitySaveStampFromEntityNode(final IMutableNode<?> entityNode) {
		return getOriSaveStampNodeFromEntityNode(entityNode).getSingleChildNodeHeader();
	}
	
	//method
	public IMutableNode<?> getOriIdNodeFromEntityNode(final IMutableNode<?> entityNode) {
		return entityNode.getOriChildNodeAt1BasedIndex(FieldIndexCatalogue.ID_INDEX);
	}
	
	//method
	public IMutableNode<?> getOriSaveStampNodeFromEntityNode(final IMutableNode<?> entityNode) {
		return entityNode.getOriChildNodeAt1BasedIndex(FieldIndexCatalogue.SAVE_STAMP_INDEX);
	}
}
