//package declaration
package ch.nolix.element.input;

//own imports
import ch.nolix.common.containers.LinkedList;

//class
public final class KeyBoard {
	
	//attribute
	private boolean shiftIsLocked = false;
	
	//multi-attribute
	private final LinkedList<Key> pressedKeys = new LinkedList<>();
	
	//method
	public boolean isPressed(final Key key) {
		return pressedKeys.containsEqualing(key);
	}
	
	//method
	public void noteKeyPress(final Key key) {
		
		if (!pressedKeys.containsEqualing(key)) {
			pressedKeys.addAtEnd(key);
		}
		
		if (key == Key.CAPS_LOCK) {
			shiftIsLocked = !shiftIsLocked;
		}
	}
	
	//method
	public void noteKeyRelease(final Key key) {
		pressedKeys.removeFirst(k -> k.equals(key));
	}
	
	//method
	public boolean shiftIsLocked() {
		return shiftIsLocked;
	}
}
