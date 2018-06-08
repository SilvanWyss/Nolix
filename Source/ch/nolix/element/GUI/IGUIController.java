//package declaration
package ch.nolix.element.GUI;

//interface
public interface IGUIController {

	//abstract method
	public abstract void noteLeftMouseButtonPressCommand(Widget<?, ?> widget);
	
	//abstract method
	public abstract void noteLeftMouseButtonReleaseCommand(Widget<?, ?> widget);
	
	//abstract method
	public abstract void noteRightMouseButtonPressCommand(Widget<?, ?> widget);
	
	//abstract method
	public abstract void noteRightMouseButtonReleaseCommand(Widget<?, ?> widget);
}
