//package declaration
package ch.nolix.element.input;

//own import
import ch.nolix.element.elementFactory.ElementFactory;

//class
public final class InputFactory extends ElementFactory<IInput<?>> {
	
	//constructor
	public InputFactory() {
		registerElementClass_(KeyInput.class, KeyInput::fromSpecification);
		registerElementClass_(MouseInput.class, MouseInput::fromSpecification);
	}
}
