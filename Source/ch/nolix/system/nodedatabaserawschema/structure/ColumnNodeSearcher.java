//package declaration
package ch.nolix.system.nodedatabaserawschema.structure;

//own imports
import ch.nolix.core.document.node.BaseNode;

//class
public final class ColumnNodeSearcher {
	
	//method
	public boolean columnNodeContainsEntityNode(final BaseNode columnNode) {
		return columnNode.containsChildNodeWithHeader(SubNodeHeaderCatalogue.ENTITY);
	}
	
	//method
	public BaseNode getRefIdNodeFromColumnNode(final BaseNode columnNode) {
		return columnNode.getRefFirstChildNodeWithHeader(SubNodeHeaderCatalogue.ID);
	}
	
	//method
	public BaseNode getRefNameNodeFromColumnNode(final BaseNode columnNode) {
		return columnNode.getRefFirstChildNodeWithHeader(SubNodeHeaderCatalogue.NAME);
	}
	
	//method
	public BaseNode getRefParametrizedPropertyTypeNodeFromColumnNode(final BaseNode columnNode) {
		return columnNode.getRefFirstChildNodeWithHeader(SubNodeHeaderCatalogue.PARAMETRIZED_PROPERTY_TYPE);
	}
}
