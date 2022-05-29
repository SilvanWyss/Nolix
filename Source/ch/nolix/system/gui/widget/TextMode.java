//package declaration
package ch.nolix.system.gui.widget;

//Java imports
import java.util.Locale;

//own imports
import ch.nolix.core.commontype.commontypehelper.GlobalStringHelper;
import ch.nolix.core.container.LinkedList;
import ch.nolix.core.document.node.BaseNode;
import ch.nolix.core.document.node.Node;
import ch.nolix.systemapi.elementapi.mainuniversalapi.Specified;

//enum
public enum TextMode implements Specified {
	NORMAL,
	SECRET;
	
	//static method
	public static TextMode fromSpecification(final BaseNode specification) {
		return valueOf(specification.getOneAttributeHeader().toUpperCase(Locale.ENGLISH));
	}
	
	//method
	@Override
	public void fillUpAttributesInto(LinkedList<Node> list) {
		list.addAtEnd(Node.withHeader(GlobalStringHelper.toPascalCase(toString())));
	}
}
