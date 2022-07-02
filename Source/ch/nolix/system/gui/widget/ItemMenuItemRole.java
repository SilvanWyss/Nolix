//package declaration
package ch.nolix.system.gui.widget;

//own imports
import ch.nolix.core.commontype.commontypehelper.GlobalStringHelper;
import ch.nolix.core.container.main.LinkedList;
import ch.nolix.core.document.node.BaseNode;
import ch.nolix.core.document.node.Node;
import ch.nolix.coreapi.documentapi.nodeapi.INode;
import ch.nolix.systemapi.elementapi.mainuniversalapi.Specified;

//enum
public enum ItemMenuItemRole implements Specified {
	NORMAL_ITEM,
	EMPTY_ITEM;
	
	//static method
	public static ItemMenuItemRole fromSpecification(final BaseNode<?> specification) {
		return valueOf(GlobalStringHelper.toUpperSnakeCase(specification.getSingleChildNodeHeader()));
	}
	
	//method
	@Override
	public final void fillUpAttributesInto(final LinkedList<INode<?>> list) {
		list.addAtEnd(Node.withHeader(GlobalStringHelper.toPascalCase(toString())));
	}
}
