//package declaration
package ch.nolix.system.gui.containerwidget;

//own imports
import ch.nolix.core.commontype.commontypehelper.GlobalStringHelper;
import ch.nolix.core.container.LinkedList;
import ch.nolix.core.document.node.BaseNode;
import ch.nolix.core.document.node.Node;
import ch.nolix.systemapi.elementuniversalapi.mainuniversalapi.Specified;

//enum
public enum GridType implements Specified {
	INNER_LINES,
	INNER_AND_OUTER_LINES;

	//static method
	public static GridType fromSpecification(final BaseNode specification) {
		return valueOf(GlobalStringHelper.toUpperSnakeCase(specification.getOneAttributeHeader()));
	}
	
	//method
	@Override
	public void fillUpAttributesInto(final LinkedList<Node> list) {
		list.addAtEnd(Node.withHeader(GlobalStringHelper.toPascalCase(toString())));
	}
}
