//package declaration
package ch.nolix.element.input;

import ch.nolix.common.commontypehelper.StringHelper;
import ch.nolix.common.container.LinkedList;
//own imports
import ch.nolix.common.node.BaseNode;
import ch.nolix.common.node.Node;
import ch.nolix.element.elementapi.IElementEnum;

//enum
public enum KeyInputType implements IElementEnum {
	PRESS,
	RELEASE,
	TYPING;
	
	//static method
	public static KeyInputType fromSpecification(final BaseNode specification) {
		return valueOf(StringHelper.toCapitalSnakeCase(specification.getOneAttributeHeader()));
	}
	
	//method
	@Override
	public LinkedList<Node> getAttributes() {
		return new Node(StringHelper.toPascalCase(toString())).intoList();
	}
}
