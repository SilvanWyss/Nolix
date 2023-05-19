//package declaration
package ch.nolix.system.nodedatabaserawschema.structure;

//own imports
import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;

//class
public final class ParametrizedPropertyTypeNodeSearcher {
	
	//method
	public IMutableNode<?> getOriBackReferencedColumnIdNodeFromPropertyTypeNode(
		final IMutableNode<?> parametrizedPropertyTypeNode
	) {
		return parametrizedPropertyTypeNode.getOriFirstChildNodeWithHeader(SubNodeHeaderCatalogue.BACK_REFERENCED_COLUMN_ID);
	}
	
	//method
	public IMutableNode<?> getOriDataTypeNodeFromParametriedPropertyTypeNode(
		final IMutableNode<?> parametrizedPropertyTypeNode
	) {
		return parametrizedPropertyTypeNode.getOriFirstChildNodeWithHeader(SubNodeHeaderCatalogue.DATA_TYPE);
	}
	
	//method
	public IMutableNode<?> getOriPropertyTypeNodeFromParametrizedPropertyTypeNode(
		final IMutableNode<?> parametrizedPropertyTypeNode
	) {
		return parametrizedPropertyTypeNode.getOriFirstChildNodeWithHeader(SubNodeHeaderCatalogue.PROPERTY_TYPE);
	}
	
	//method
	public IMutableNode<?> getOriReferencedTableIdNodeFromParametrizedPropertyTypeNode(
		IMutableNode<?> parametrizedPropertyTypeNode
	) {
		return parametrizedPropertyTypeNode.getOriFirstChildNodeWithHeader(SubNodeHeaderCatalogue.REFERENCED_TABLE_ID);
	}
}
