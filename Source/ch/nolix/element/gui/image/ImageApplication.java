//package declaration
package ch.nolix.element.gui.image;

import ch.nolix.common.commontype.commontypehelper.StringHelper;
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.document.node.BaseNode;
import ch.nolix.common.document.node.Node;
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
