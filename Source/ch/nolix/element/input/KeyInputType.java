//package declaration
package ch.nolix.element.input;

//own imports
import ch.nolix.common.node.BaseNode;
import ch.nolix.element.elementAPI.IElementEnum;

//enum
public enum KeyInputType implements IElementEnum {
	Press,
	Release,
	Typing;
	
	//static method
	public static KeyInputType fromSpecification(final BaseNode specification) {
		return valueOf(specification.getOneAttributeHeader());
	}
}
