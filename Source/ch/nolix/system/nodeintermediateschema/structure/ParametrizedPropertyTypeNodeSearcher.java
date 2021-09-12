//package declaration
package ch.nolix.system.nodeintermediateschema.structure;

//own imports
import ch.nolix.common.document.node.BaseNode;

//class
public final class ParametrizedPropertyTypeNodeSearcher {
	
	//method
	public BaseNode getBackReferencedColumnNodeFromPropertyTypeNode(final BaseNode parametrizedPropertyTypeNode) {
		return parametrizedPropertyTypeNode.getRefFirstAttribute(SubNodeHeaderCatalogue.BACK_REFERENCED_COLUMN);
	}
	
	//method
	public BaseNode getBackReferencedTableNodeFromPropertyTypeNode(final BaseNode parametrizedPropertyTypeNode) {
		return parametrizedPropertyTypeNode.getRefFirstAttribute(SubNodeHeaderCatalogue.BACK_REFERENCED_TABLE);
	}
	
	//method
	public BaseNode getDataTypeNodeFromParametriedPropertyTypeNode(final BaseNode parametrizedPropertyTypeNode) {
		return parametrizedPropertyTypeNode.getRefFirstAttribute(SubNodeHeaderCatalogue.DATA_TYPE);
	}
	
	//method
	public BaseNode getPropertyTypeNodeFromParametrizedPropertyTypeNode(final BaseNode parametrizedPropertyTypeNode) {
		return parametrizedPropertyTypeNode.getRefFirstAttribute(SubNodeHeaderCatalogue.PROPERTY_TYPE);
	}
	
	//method
	public BaseNode getReferencedTableNodeFromParametrizedPropertyTypeNode(BaseNode parametrizedPropertyTypeNode) {
		return parametrizedPropertyTypeNode.getRefFirstAttribute(SubNodeHeaderCatalogue.REFERENCED_TABLE);
	}
}
