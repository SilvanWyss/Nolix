//package declaration
package ch.nolix.system.nodedatabaserawdata.structure;

//own imports
import ch.nolix.core.document.node.BaseNode;
import ch.nolix.system.nodedatabaserawdata.tabledefinition.FieldIndexCatalogue;

//class
public final class EntityNodeSearcher {
	
	//method
	public String getEntitySaveStampFromEntityNode(final BaseNode entityNode) {
		return getRefSaveStampNodeFromRecordNode(entityNode).getSingleChildNodeHeader();
	}
	
	//method
	public BaseNode getRefIdNodeFromRecordNode(final BaseNode recordNode) {
		return recordNode.getRefChildNodeAt1BasedIndex(FieldIndexCatalogue.ID_INDEX);
	}
	
	//method
	public BaseNode getRefSaveStampNodeFromRecordNode(final BaseNode recordNode) {
		return recordNode.getRefChildNodeAt1BasedIndex(FieldIndexCatalogue.ID_INDEX);
	}
}
