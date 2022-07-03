//package declaration
package ch.nolix.system.gui.widget;

//Java imports
import java.util.Locale;

//own imports
import ch.nolix.core.commontype.commontypehelper.GlobalStringHelper;
import ch.nolix.core.container.main.LinkedList;
import ch.nolix.core.document.node.Node;
import ch.nolix.coreapi.documentapi.nodeapi.INode;
import ch.nolix.systemapi.elementapi.mainuniversalapi.Specified;

//enum
public enum TextMode implements Specified {
	NORMAL,
	SECRET;
	
	//static method
	public static TextMode fromSpecification(final INode<?> specification) {
		return valueOf(specification.getSingleChildNodeHeader().toUpperCase(Locale.ENGLISH));
	}
	
	//method
	@Override
	public void fillUpAttributesInto(LinkedList<INode<?>> list) {
		list.addAtEnd(Node.withHeader(GlobalStringHelper.toPascalCase(toString())));
	}
}
