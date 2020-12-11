//package declaration
package ch.nolix.element.gui;

//own imports
import ch.nolix.common.commontypehelper.StringHelper;
import ch.nolix.common.container.LinkedList;
//own imports
import ch.nolix.common.node.BaseNode;
import ch.nolix.common.node.Node;
import ch.nolix.element.elementapi.IElementEnum;

//enum
public enum LayerRole implements IElementEnum {
	BACKGROUND_LAYER,
	MAIN_LAYER,
	SUP_LAYER,
	DIALOG_LAYER;
	
	//static method
	public static LayerRole fromSpecification(final BaseNode specification) {
		return valueOf(StringHelper.toCapitalSnakeCase(specification.getOneAttributeHeader()));
	}
	
	//method
	@Override
	public LinkedList<Node> getAttributes() {
		return Node.withHeader(StringHelper.toPascalCase(toString())).intoList();
	}
}
