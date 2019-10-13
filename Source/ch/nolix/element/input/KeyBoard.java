//package declaration
package ch.nolix.element.input;

//own imports
import ch.nolix.common.containers.List;

//class
public final class KeyBoard {
	
	//multi-attribute
	private final List<Key> pressedKeys = new List<>();
	
	//method
	public boolean isPressed(final Key key) {
		return pressedKeys.containsEqualing(key);
	}
	
	//method
	public void noteKeyPress(final Key key) {
		if (!pressedKeys.containsEqualing(key)) {
			pressedKeys.addAtEnd(key);
		}
	}
	
	//method
	public void noteKeyRelease(final Key key) {
		pressedKeys.removeFirst(k -> k.equals(key));
	}
}
