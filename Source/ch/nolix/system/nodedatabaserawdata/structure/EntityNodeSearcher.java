//package declaration
package ch.nolix.system.nodedatabaserawdata.structure;

//own imports
import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;
import ch.nolix.system.nodedatabaserawdata.tabledefinition.FieldIndexCatalogue;

//class
public final class EntityNodeSearcher {
	
	//method
	public String getEntitySaveStampFromEntityNode(final IMutableNode<?> entityNode) {
		return getRefSaveStampNodeFromEntityNode(entityNode).getSingleChildNodeHeader();
	}
	
	//method
	public IMutableNode<?> getRefIdNodeFromEntityNode(final IMutableNode<?> recordNode) {
		return recordNode.getRefChildNodeAt1BasedIndex(FieldIndexCatalogue.ID_INDEX);
	}
	
	//method
	public IMutableNode<?> getRefSaveStampNodeFromEntityNode(final IMutableNode<?> recordNode) {
		return recordNode.getRefChildNodeAt1BasedIndex(FieldIndexCatalogue.SAVE_STAMP_INDEX);
	}
}
