//package declaration
package ch.nolix.element.keyBoard;

//own imports
import ch.nolix.common.node.BaseNode;
import ch.nolix.element.baseAPI.IElementEnum;

//enum
public enum KeyInputType implements IElementEnum {
	Press,
	Release,
	Typing;
	
	//static method
	public static KeyInputType fromSpecification(final BaseNode specification) {
		return valueOf(specification.getOneAttributeAsString());
	}
}
