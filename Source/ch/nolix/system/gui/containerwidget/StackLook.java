//package declaration
package ch.nolix.system.gui.containerwidget;

//own imports
import ch.nolix.core.document.node.BaseNode;
import ch.nolix.system.gui.widget.BorderWidgetLook;

//class
public final class StackLook extends BorderWidgetLook<StackLook> {
	
	//static method
	public static StackLook fromSpecification(final BaseNode specification) {
		
		final var stackLook = new StackLook();
		stackLook.resetFromSpecification(specification);
		
		return stackLook;
	}
}
