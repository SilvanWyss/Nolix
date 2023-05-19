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
	public IMutableNode<?> getOriIdNodeFromColumnNode(final IMutableNode<?> columnNode) {
		return columnNode.getOriFirstChildNodeWithHeader(SubNodeHeaderCatalogue.ID);
	}
	
	//method
	public IMutableNode<?> getOriNameNodeFromColumnNode(final IMutableNode<?> columnNode) {
		return columnNode.getOriFirstChildNodeWithHeader(SubNodeHeaderCatalogue.NAME);
	}
	
	//method
	public IMutableNode<?> getOriParametrizedPropertyTypeNodeFromColumnNode(final IMutableNode<?> columnNode) {
		return columnNode.getOriFirstChildNodeWithHeader(SubNodeHeaderCatalogue.PARAMETRIZED_PROPERTY_TYPE);
	}
}
