//package declaration
package ch.nolix.element.gui.widget;

//Java imports
import java.util.Locale;

//own imports
import ch.nolix.common.commontype.commontypehelper.GlobalStringHelper;
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.document.node.BaseNode;
import ch.nolix.common.document.node.Node;
import ch.nolix.element.elementapi.IElement;

//enum
public enum TextMode implements IElement<TextMode> {
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
