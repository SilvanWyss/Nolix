//package declaration
package ch.nolix.element.gui.base;

import ch.nolix.core.commontype.commontypehelper.GlobalStringHelper;
import ch.nolix.core.container.LinkedList;
import ch.nolix.core.document.node.BaseNode;
import ch.nolix.core.document.node.Node;
import ch.nolix.element.elementapi.IElement;

//enum
public enum LayerRole implements IElement<LayerRole> {
	BACKGROUND_LAYER,
	MAIN_LAYER,
	SUP_LAYER,
	DIALOG_LAYER;
	
	//static method
	public static LayerRole fromSpecification(final BaseNode specification) {
		return valueOf(GlobalStringHelper.toCapitalSnakeCase(specification.getOneAttributeHeader()));
	}
	
	//method
	@Override
	public void fillUpAttributesInto(final LinkedList<Node> list) {
		list.addAtEnd(Node.withHeader(GlobalStringHelper.toPascalCase(toString())));
	}
}
