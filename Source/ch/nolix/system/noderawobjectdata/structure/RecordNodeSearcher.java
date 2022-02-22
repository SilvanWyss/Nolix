//package declaration
package ch.nolix.system.noderawobjectdata.structure;

//own imports
import ch.nolix.core.document.node.BaseNode;
import ch.nolix.system.noderawobjectdata.tabledefinition.FieldIndexCatalogue;

//class
public final class RecordNodeSearcher {
	
	//method
	public String getEntitySaveStampFromEntityNode(final BaseNode entityNode) {
		return getRefSaveStampNodeFromRecordNode(entityNode).getOneAttributeHeader();
	}
	
	//method
	public BaseNode getRefContentFieldNodeFromRecordNodeAtIndex(final BaseNode recordNode, final int index) {
		return recordNode.getRefAttributeAt(2 + index);
	}
	
	//method
	public BaseNode getRefIdNodeFromRecordNode(final BaseNode recordNode) {
		return recordNode.getRefAttributeAt(FieldIndexCatalogue.ID_INDEX);
	}
	
	//method
	public BaseNode getRefSaveStampNodeFromRecordNode(final BaseNode recordNode) {
		return recordNode.getRefAttributeAt(FieldIndexCatalogue.ID_INDEX);
	}
}
