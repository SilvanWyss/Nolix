//package declaration
package ch.nolix.system.nodedatabaserawschema.structure;

//own imports
import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;
import ch.nolix.coreapi.documentapi.nodeapi.INode;

//class
public final class ColumnNodeSearcher {
	
	//method
	public boolean columnNodeContainsEntityNode(final IMutableNode<?> columnNode) {
		return columnNode.containsChildNodeWithHeader(SubNodeHeaderCatalogue.ENTITY);
	}
	
	//method
	public IMutableNode<?> getRefIdNodeFromColumnNode(final IMutableNode<?> columnNode) {
		return columnNode.getRefFirstChildNodeWithHeader(SubNodeHeaderCatalogue.ID);
	}
	
	//method
	public IMutableNode<?> getRefNameNodeFromColumnNode(final INode<?> columnNode) {
		
		//TODO: Refactor this.
		return (IMutableNode<?>)columnNode.getRefFirstChildNodeWithHeader(SubNodeHeaderCatalogue.NAME);
	}
	
	//method
	public IMutableNode<?> getRefParametrizedPropertyTypeNodeFromColumnNode(final IMutableNode<?> columnNode) {
		return columnNode.getRefFirstChildNodeWithHeader(SubNodeHeaderCatalogue.PARAMETRIZED_PROPERTY_TYPE);
	}
}
