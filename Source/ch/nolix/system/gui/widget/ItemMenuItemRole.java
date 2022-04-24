//package declaration
package ch.nolix.system.gui.widget;

//own imports
import ch.nolix.core.commontype.commontypehelper.GlobalStringHelper;
import ch.nolix.core.container.LinkedList;
import ch.nolix.core.document.node.BaseNode;
import ch.nolix.core.document.node.Node;
import ch.nolix.systemapi.elementapi.IElement;

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
