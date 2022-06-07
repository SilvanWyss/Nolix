//package declaration
package ch.nolix.systemapi.timeapi.timestructure;

//own imports
import ch.nolix.core.commontype.commontypehelper.GlobalStringHelper;
import ch.nolix.core.container.LinkedList;
import ch.nolix.core.document.node.BaseNode;
import ch.nolix.core.document.node.Node;
import ch.nolix.systemapi.elementapi.mainuniversalapi.Specified;

//enum
public enum Weekday implements Specified {
	MONDAY,
	TUESDAY,
	WEDNESDAY,
	THURSDAY,
	FRIDAY,
	SATURDAY,
	SUNDAY;
	
	//static method
	public static Weekday fromSpecification(final BaseNode specification) {
		return Weekday.valueOf(specification.getOneAttributeHeader());
	}
	
	//method
	@Override
	public void fillUpAttributesInto(final LinkedList<Node> list) {
		list.addAtEnd(Node.withHeader(name()));
	}
	
	//method
	@Override
	public String toString() {
		return GlobalStringHelper.toPascalCase(name());
	}
}
