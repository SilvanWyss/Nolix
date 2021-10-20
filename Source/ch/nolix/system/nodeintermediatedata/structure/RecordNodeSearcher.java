//package declaration
package ch.nolix.system.nodeintermediatedata.structure;

//own imports
import ch.nolix.common.document.node.BaseNode;
import ch.nolix.system.nodeintermediatedata.tabledefinition.FieldIndexCatalogue;

//class
public final class RecordNodeSearcher {
	
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
