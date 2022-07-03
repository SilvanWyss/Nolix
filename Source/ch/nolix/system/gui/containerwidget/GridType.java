//package declaration
package ch.nolix.system.gui.containerwidget;

//own imports
import ch.nolix.core.commontype.commontypehelper.GlobalStringHelper;
import ch.nolix.core.container.main.LinkedList;
import ch.nolix.core.document.node.Node;
import ch.nolix.coreapi.documentapi.nodeapi.INode;
import ch.nolix.systemapi.elementapi.mainuniversalapi.Specified;

//enum
public enum GridType implements Specified {
	INNER_LINES,
	INNER_AND_OUTER_LINES;

	//static method
	public static GridType fromSpecification(final INode<?> specification) {
		return valueOf(GlobalStringHelper.toUpperSnakeCase(specification.getSingleChildNodeHeader()));
	}
	
	//method
	@Override
	public void fillUpAttributesInto(final LinkedList<INode<?>> list) {
		list.addAtEnd(Node.withHeader(GlobalStringHelper.toPascalCase(toString())));
	}
}
