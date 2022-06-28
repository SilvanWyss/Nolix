//package declaration
package ch.nolix.system.nodedatabaserawdata.structure;

//own imports
import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;
import ch.nolix.system.nodedatabaserawdata.tabledefinition.FieldIndexCatalogue;

//class
public final class EntityNodeSearcher {
	
	//method
	public String getEntitySaveStampFromEntityNode(final IMutableNode<?> entityNode) {
		return getRefSaveStampNodeFromRecordNode(entityNode).getSingleChildNodeHeader();
	}
	
	//method
	public IMutableNode<?> getRefIdNodeFromRecordNode(final IMutableNode<?> recordNode) {
		return recordNode.getRefChildNodeAt1BasedIndex(FieldIndexCatalogue.ID_INDEX);
	}
	
	//method
	public IMutableNode<?> getRefSaveStampNodeFromRecordNode(final IMutableNode<?> recordNode) {
		return recordNode.getRefChildNodeAt1BasedIndex(FieldIndexCatalogue.ID_INDEX);
	}
}
