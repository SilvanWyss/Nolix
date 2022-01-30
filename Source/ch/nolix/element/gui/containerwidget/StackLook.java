//package declaration
package ch.nolix.element.gui.containerwidget;

import ch.nolix.core.document.node.BaseNode;
import ch.nolix.element.gui.widget.BorderWidgetLook;

//class
public final class StackLook extends BorderWidgetLook<StackLook> {
	
	//static method
	public static StackLook fromSpecification(final BaseNode specification) {
		
		final var stackLook = new StackLook();
		stackLook.resetFrom(specification);
		
		return stackLook;
	}
}
