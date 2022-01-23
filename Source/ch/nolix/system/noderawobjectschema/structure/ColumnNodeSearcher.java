//package declaration
package ch.nolix.system.noderawobjectschema.structure;

//own imports
import ch.nolix.common.document.node.BaseNode;

//class
public final class ColumnNodeSearcher {
	
	//method
	public boolean columnNodeContainsEntityNode(final BaseNode columnNode) {
		return columnNode.containsAttributeWithHeader(SubNodeHeaderCatalogue.ENTITY);
	}
	
	//method
	public BaseNode getRefIdNodeFromColumnNode(final BaseNode columnNode) {
		return columnNode.getRefFirstAttribute(SubNodeHeaderCatalogue.ID);
	}
	
	//method
	public BaseNode getRefNameNodeFromColumnNode(final BaseNode columnNode) {
		return columnNode.getRefFirstAttribute(SubNodeHeaderCatalogue.NAME);
	}
	
	//method
	public BaseNode getRefParametrizedPropertyTypeNodeFromColumnNode(final BaseNode columnNode) {
		return columnNode.getRefFirstAttribute(SubNodeHeaderCatalogue.PARAMETRIZED_PROPERTY_TYPE);
	}
}
