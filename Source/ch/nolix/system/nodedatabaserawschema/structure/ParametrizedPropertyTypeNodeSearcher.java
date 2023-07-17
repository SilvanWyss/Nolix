//package declaration
package ch.nolix.system.nodedatabaserawschema.structure;

//own imports
import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;

//class
public final class ParametrizedPropertyTypeNodeSearcher {
	
	//method
	public IMutableNode<?> getStoredBackReferencedColumnIdNodeFromPropertyTypeNode(
		final IMutableNode<?> parametrizedPropertyTypeNode
	) {
		return
		parametrizedPropertyTypeNode.getStoredFirstChildNodeWithHeader(
			SubNodeHeaderCatalogue.BACK_REFERENCED_COLUMN_ID
		);
	}
	
	//method
	public IMutableNode<?> getStoredDataTypeNodeFromParametriedPropertyTypeNode(
		final IMutableNode<?> parametrizedPropertyTypeNode
	) {
		return parametrizedPropertyTypeNode.getStoredFirstChildNodeWithHeader(SubNodeHeaderCatalogue.DATA_TYPE);
	}
	
	//method
	public IMutableNode<?> getStoredPropertyTypeNodeFromParametrizedPropertyTypeNode(
		final IMutableNode<?> parametrizedPropertyTypeNode
	) {
		return parametrizedPropertyTypeNode.getStoredFirstChildNodeWithHeader(SubNodeHeaderCatalogue.PROPERTY_TYPE);
	}
	
	//method
	public IMutableNode<?> getStoredReferencedTableIdNodeFromParametrizedPropertyTypeNode(
		IMutableNode<?> parametrizedPropertyTypeNode
	) {
		return parametrizedPropertyTypeNode.getStoredFirstChildNodeWithHeader(SubNodeHeaderCatalogue.REFERENCED_TABLE_ID);
	}
}
