//package declaration
package ch.nolix.element.input;

import ch.nolix.element.elementfactory.ElementFactory;

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
