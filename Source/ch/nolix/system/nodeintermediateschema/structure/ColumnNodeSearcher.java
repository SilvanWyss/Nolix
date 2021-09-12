//package declaration
package ch.nolix.system.nodeintermediateschema.structure;

//own imports
import ch.nolix.common.document.node.BaseNode;

//class
public final class ColumnNodeSearcher {
	
	//method
	public boolean columnNodeContainsEntityNode(final BaseNode columnNode) {
		return columnNode.containsAttributeWithHeader(SubNodeHeaderCatalogue.ENTITY);
	}
	
	//method
	public BaseNode getHeaderNodeFromColumnNode(final BaseNode columnNode) {
		return columnNode.getRefFirstAttribute(SubNodeHeaderCatalogue.HEADER);
	}
	
	//method
	public BaseNode getParametrizedPropertyTypeNodeFromColumnNode(final BaseNode columnNode) {
		return columnNode.getRefFirstAttribute(SubNodeHeaderCatalogue.PARAMETRIZED_PROPERTY_TYPE);
	}
	
	//method
	public BaseNode getPropertyTypeNodeFromColumnNode(final BaseNode columnNode) {
		return columnNode.getRefFirstAttribute(SubNodeHeaderCatalogue.PROPERTY_TYPE);
	}
}
