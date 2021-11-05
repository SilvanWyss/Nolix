//package declaration
package ch.nolix.system.nodeintermediatedata.structure;

//own imports
import ch.nolix.common.document.node.BaseNode;
import ch.nolix.system.noderawobjectschema.structure.SubNodeHeaderCatalogue;

//class
public final class ColumnNodeSearcher {
	
	//method
	public BaseNode getRefHeaderNodeFromColumnNode(final BaseNode columnNode) {
		return columnNode.getRefFirstAttribute(SubNodeHeaderCatalogue.HEADER);
	}
	
	//method
	public BaseNode getRefParametrizedPropertyTypeNodeFromColumnNode(final BaseNode columnNode) {
		return columnNode.getRefFirstAttribute(SubNodeHeaderCatalogue.PARAMETRIZED_PROPERTY_TYPE);
	}
}
