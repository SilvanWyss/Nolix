//package declaration
package ch.nolix.system.gui.containerwidget;

//own imports
import ch.nolix.coreapi.documentapi.nodeapi.INode;
import ch.nolix.system.gui.widget.BorderWidgetStyle;

//class
public final class StackStyle extends BorderWidgetStyle<StackStyle> {
	
	//static method
	public static StackStyle fromSpecification(final INode<?> specification) {
		
		final var stackLook = new StackStyle();
		stackLook.resetFromSpecification(specification);
		
		return stackLook;
	}
}
