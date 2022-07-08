//package declaration
package ch.nolix.system.gui.input;

//own imports
import ch.nolix.system.elementfactory.ElementFactory;
import ch.nolix.systemapi.guiapi.inputapi.IInput;

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
