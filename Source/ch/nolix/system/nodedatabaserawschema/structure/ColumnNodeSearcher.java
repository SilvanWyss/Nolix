//package declaration
package ch.nolix.system.nodedatabaserawschema.structure;

//own imports
import ch.nolix.core.document.node.BaseNode;
import ch.nolix.coreapi.documentapi.nodeapi.INode;

//class
public final class ColumnNodeSearcher {
	
	//method
	public boolean columnNodeContainsEntityNode(final BaseNode<?> columnNode) {
		return columnNode.containsChildNodeWithHeader(SubNodeHeaderCatalogue.ENTITY);
	}
	
	//method
	public BaseNode<?> getRefIdNodeFromColumnNode(final BaseNode<?> columnNode) {
		return columnNode.getRefFirstChildNodeWithHeader(SubNodeHeaderCatalogue.ID);
	}
	
	//method
	public BaseNode<?> getRefNameNodeFromColumnNode(final INode<?> columnNode) {
		
		//TODO: Refactor this.
		return (BaseNode<?>)columnNode.getRefFirstChildNodeWithHeader(SubNodeHeaderCatalogue.NAME);
	}
	
	//method
	public BaseNode<?> getRefParametrizedPropertyTypeNodeFromColumnNode(final BaseNode<?> columnNode) {
		return columnNode.getRefFirstChildNodeWithHeader(SubNodeHeaderCatalogue.PARAMETRIZED_PROPERTY_TYPE);
	}
}
