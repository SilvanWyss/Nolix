//package declaration
package ch.nolix.system.nodedatabaserawschema.structure;

//own imports
import ch.nolix.core.document.node.BaseNode;

//class
public final class ParametrizedPropertyTypeNodeSearcher {
	
	//method
	public BaseNode<?> getRefBackReferencedColumnIdNodeFromPropertyTypeNode(final BaseNode<?> parametrizedPropertyTypeNode) {
		return parametrizedPropertyTypeNode.getRefFirstChildNodeWithHeader(SubNodeHeaderCatalogue.BACK_REFERENCED_COLUMN_ID);
	}
	
	//method
	public BaseNode<?> getRefDataTypeNodeFromParametriedPropertyTypeNode(final BaseNode<?> parametrizedPropertyTypeNode) {
		return parametrizedPropertyTypeNode.getRefFirstChildNodeWithHeader(SubNodeHeaderCatalogue.DATA_TYPE);
	}
	
	//method
	public BaseNode<?> getRefPropertyTypeNodeFromParametrizedPropertyTypeNode(final BaseNode<?> parametrizedPropertyTypeNode) {
		return parametrizedPropertyTypeNode.getRefFirstChildNodeWithHeader(SubNodeHeaderCatalogue.PROPERTY_TYPE);
	}
	
	//method
	public BaseNode<?> getRefReferencedTableIdNodeFromParametrizedPropertyTypeNode(BaseNode<?> parametrizedPropertyTypeNode) {
		return parametrizedPropertyTypeNode.getRefFirstChildNodeWithHeader(SubNodeHeaderCatalogue.REFERENCED_TABLE_ID);
	}
}
