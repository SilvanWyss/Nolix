//package declaration
package ch.nolix.element.gui.input;

//own imports
import ch.nolix.element.elementfactory.ElementFactory;
import ch.nolix.elementapi.guiapi.inputapi.IInput;

//class
public final class InputFactory extends ElementFactory<IInput<?>> {
	
	//constant
	public static final InputFactory INSTANCE = new InputFactory();
	
	//constructor
	private InputFactory() {
		registerElementClass_(KeyInput.class, KeyInput::fromSpecification);
		registerElementClass_(MouseInput.class, MouseInput::fromSpecification);
		registerElementClass_(ResizeInput.class, ResizeInput::fromSpecification);
	}
}
