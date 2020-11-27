//package declaration
package ch.nolix.element.input;

//own import
import ch.nolix.element.elementFactory.ElementFactory;

//class
public final class InputFactory extends ElementFactory<IInput<?>> {
	
	//constant
	public static final InputFactory INSTANCE = new InputFactory();
	
	//visibility-reduced constructor
	private InputFactory() {
		registerElementClass_(KeyInput.class, KeyInput::fromSpecification);
		registerElementClass_(MouseInput.class, MouseInput::fromSpecification);
		registerElementClass_(ResizeInput.class, ResizeInput::fromSpecification);
	}
}
