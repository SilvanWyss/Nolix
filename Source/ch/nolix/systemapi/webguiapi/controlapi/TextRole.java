//package declaration
package ch.nolix.systemapi.webguiapi.controlapi;

//own imports
import ch.nolix.coreapi.documentapi.nodeapi.INode;

//enum
public enum TextRole {
	TITLE,
	SUB_TITLE,
	LEVEL1_HEADER,
	LEVEL2_HEADER,
	LEVEL3_HEADER,
	LEVEL4_HEADER,
	LABEL;
	
	//static method
	public static TextRole fromSpecification(final INode<?> specification) {
		return valueOf(specification.getSingleChildNodeHeader());
	}
}
