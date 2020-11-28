//package declaration
package ch.nolix.element.inputdevice;

//own imports
import ch.nolix.common.container.LinkedList;
import ch.nolix.element.input.Key;
import ch.nolix.element.inputdeviceapi.IMutableKeyBoard;

//class
public final class KeyBoard implements IMutableKeyBoard {
	
	//attribute
	private boolean shiftIsLocked;
	
	//multi-attribute
	private final LinkedList<Key> pressedKeys = new LinkedList<>();
	
	//method
	@Override
	public boolean keyIsPressed(final Key key) {
		return pressedKeys.containsEqualing(key);
	}
	
	//method
	@Override
	public void noteKeyPress(final Key key) {
		
		if (!pressedKeys.containsEqualing(key)) {
			pressedKeys.addAtEnd(key);
		}
		
		if (key == Key.CAPS_LOCK) {
			shiftIsLocked = !shiftIsLocked;
		}
	}
	
	//method
	@Override
	public void noteKeyRelease(final Key key) {
		pressedKeys.removeFirst(k -> k.equals(key));
	}
	
	@Override
	public boolean shiftIsLocked() {
		return shiftIsLocked;
	}
}
