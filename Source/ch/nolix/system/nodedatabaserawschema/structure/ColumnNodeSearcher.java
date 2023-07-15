//package declaration
package ch.nolix.system.nodedatabaserawschema.structure;

//own imports
import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;

//class
public final class ColumnNodeSearcher {
	
	//method
	public boolean columnNodeContainsEntityNode(final IMutableNode<?> columnNode) {
		return columnNode.containsChildNodeWithHeader(SubNodeHeaderCatalogue.ENTITY);
	}
	
	//method
	public IMutableNode<?> getStoredIdNodeFromColumnNode(final IMutableNode<?> columnNode) {
		return columnNode.getStoredFirstChildNodeWithHeader(SubNodeHeaderCatalogue.ID);
	}
	
	//method
	public IMutableNode<?> getStoredNameNodeFromColumnNode(final IMutableNode<?> columnNode) {
		return columnNode.getStoredFirstChildNodeWithHeader(SubNodeHeaderCatalogue.NAME);
	}
	
	//method
	public IMutableNode<?> getStoredParametrizedPropertyTypeNodeFromColumnNode(final IMutableNode<?> columnNode) {
		return columnNode.getStoredFirstChildNodeWithHeader(SubNodeHeaderCatalogue.PARAMETRIZED_PROPERTY_TYPE);
	}
}
