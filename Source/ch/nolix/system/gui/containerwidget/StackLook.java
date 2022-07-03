//package declaration
package ch.nolix.system.gui.containerwidget;

//own imports
import ch.nolix.coreapi.documentapi.nodeapi.INode;
import ch.nolix.system.gui.widget.BorderWidgetLook;

//class
public final class StackLook extends BorderWidgetLook<StackLook> {
	
	//static method
	public static StackLook fromSpecification(final INode<?> specification) {
		
		final var stackLook = new StackLook();
		stackLook.resetFromSpecification(specification);
		
		return stackLook;
	}
}
