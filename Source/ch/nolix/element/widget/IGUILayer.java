//package declaration
package ch.nolix.element.widget;

//own imports
import ch.nolix.core.container.List;
import ch.nolix.core.skillAPI.Clearable;
import ch.nolix.core.specificationAPI.Specifiable;
import ch.nolix.element.painter.IPainter;

//interface
public interface IGUILayer<GL extends IGUILayer<GL>> extends Clearable<GL>, Specifiable<GL> {
	
	//abstract methods
	public abstract CursorIcon getCursorIcon();
	
	//abstract method
	public abstract Widget<?, ?> getRefRootWidget();
	
	//default method
	public default List<Widget<?, ?>> getRefTriggerableWidgetsRecursively() {
		
		if (isEmpty()) {
			return new List<>();
		}
		
		final var rootWidget = getRefRootWidget();
		
		return rootWidget.getTriggerableChildWidgetsRecursively().addAtEnd(rootWidget);
	}
	
	//abstract method
	public abstract void paint(IPainter painter);
	
	//abstract method
	public abstract void recalculate();
	
	//abstract method
	public abstract void resetConfiguration();
	
	//abstract method
	public abstract GL setParentGUI(IGUI<?> parentGUI);

	//abstract method
	public abstract GL setRootWidget(Widget<?, ?> rootWidget);
}
