//package declaration
package ch.nolix.element.gui.widget;

//own imports
import ch.nolix.common.commontype.commontypehelper.GlobalStringHelper;
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.document.node.BaseNode;
import ch.nolix.common.document.node.Node;
import ch.nolix.element.elementapi.IElement;

//enum
public enum ItemMenuItemRole implements IElement<ItemMenuItemRole> {
	NORMAL_ITEM,
	EMPTY_ITEM;
	
	//static method
	public static ItemMenuItemRole fromSpecification(final BaseNode specification) {
		return valueOf(GlobalStringHelper.toCapitalSnakeCase(specification.getOneAttributeHeader()));
	}
	
	//method
	@Override
	public final void fillUpAttributesInto(final LinkedList<Node> list) {
		list.addAtEnd(Node.withHeader(GlobalStringHelper.toPascalCase(toString())));
	}
}
