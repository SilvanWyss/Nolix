//package declaration
package ch.nolix.systemapi.guiapi.processproperty;

//own imports
import ch.nolix.coreapi.documentapi.nodeapi.INode;

//enum
public enum KeyInputType {
	PRESS,
	RELEASE,
	TYPING;
	
	//static method
	public static KeyInputType fromSpecification(final INode<?> specification) {
		return valueOf(specification.getSingleChildNodeHeader());
	}
}
