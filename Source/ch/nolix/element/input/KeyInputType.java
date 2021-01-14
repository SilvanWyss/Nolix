//package declaration
package ch.nolix.element.input;

//own imports
import ch.nolix.common.commontypehelper.StringHelper;
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.node.BaseNode;
import ch.nolix.common.node.Node;
import ch.nolix.element.elementapi.IElement;

//enum
public enum KeyInputType implements IElement {
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
		return Node.withHeader(StringHelper.toPascalCase(toString())).intoList();
	}
}
