//package declaration
package ch.nolix.element.graphic;

//own imports
import ch.nolix.common.commontypehelper.StringHelper;
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.node.BaseNode;
import ch.nolix.common.node.Node;
import ch.nolix.element.elementapi.IElement;

//enum
public enum ImageApplication implements IElement {
	SCALE_TO_FRAME,
	REPEATE;
	
	//static method
	public static ImageApplication fromSpecification(final BaseNode specification) {
		return valueOf(StringHelper.toCapitalSnakeCase(specification.getOneAttributeHeader()));
	}
	
	//method
	@Override
	public void fillUpAttributesInto(final LinkedList<Node> list) {
		list.addAtEnd(Node.withHeader(StringHelper.toPascalCase(toString())));
	}
}
