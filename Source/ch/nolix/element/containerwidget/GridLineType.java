//package declaration
package ch.nolix.element.containerwidget;

//own imports
import ch.nolix.common.commontypehelper.StringHelper;
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.node.BaseNode;
import ch.nolix.common.node.Node;
import ch.nolix.element.elementapi.IElement;

//enum
public enum GridLineType implements IElement {
	INNER_LINES,
	INNER_AND_OUTER_LINES;

	//static method
	public static GridLineType fromSpecification(final BaseNode specification) {
		return valueOf(StringHelper.toCapitalSnakeCase(specification.getOneAttributeHeader()));
	}
	
	//method
	@Override
	public LinkedList<Node> getAttributes() {
		return Node.withHeader(StringHelper.toPascalCase(toString())).intoList();
	}
}
