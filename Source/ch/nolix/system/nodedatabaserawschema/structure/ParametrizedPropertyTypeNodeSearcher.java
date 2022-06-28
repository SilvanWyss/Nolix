//package declaration
package ch.nolix.system.nodedatabaserawschema.structure;

//own imports
import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;

//class
public final class ParametrizedPropertyTypeNodeSearcher {
	
	//method
	public IMutableNode<?> getRefBackReferencedColumnIdNodeFromPropertyTypeNode(final IMutableNode<?> parametrizedPropertyTypeNode) {
		return parametrizedPropertyTypeNode.getRefFirstChildNodeWithHeader(SubNodeHeaderCatalogue.BACK_REFERENCED_COLUMN_ID);
	}
	
	//method
	public IMutableNode<?> getRefDataTypeNodeFromParametriedPropertyTypeNode(final IMutableNode<?> parametrizedPropertyTypeNode) {
		return parametrizedPropertyTypeNode.getRefFirstChildNodeWithHeader(SubNodeHeaderCatalogue.DATA_TYPE);
	}
	
	//method
	public IMutableNode<?> getRefPropertyTypeNodeFromParametrizedPropertyTypeNode(final IMutableNode<?> parametrizedPropertyTypeNode) {
		return parametrizedPropertyTypeNode.getRefFirstChildNodeWithHeader(SubNodeHeaderCatalogue.PROPERTY_TYPE);
	}
	
	//method
	public IMutableNode<?> getRefReferencedTableIdNodeFromParametrizedPropertyTypeNode(IMutableNode<?> parametrizedPropertyTypeNode) {
		return parametrizedPropertyTypeNode.getRefFirstChildNodeWithHeader(SubNodeHeaderCatalogue.REFERENCED_TABLE_ID);
	}
}
