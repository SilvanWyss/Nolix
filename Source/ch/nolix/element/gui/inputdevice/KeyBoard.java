//package declaration
package ch.nolix.element.gui.inputdevice;

//own imports
import ch.nolix.core.container.LinkedList;
import ch.nolix.elementapi.guiapi.inputapi.Key;
import ch.nolix.elementapi.guiapi.inputdeviceapi.IMutableKeyBoard;

//class
public final class KeyBoard implements IMutableKeyBoard {
	
	//attribute
	private boolean shiftIsLocked;
	
	//multi-attribute
	private final LinkedList<Key> pressedKeys = new LinkedList<>();
	
	//method
	@Override
	public boolean keyIsPressed(final Key key) {
		return pressedKeys.containsAnyEqualing(key);
	}
	
	//method
	@Override
	public void noteKeyDown(final Key key) {
		
		if (!pressedKeys.containsAnyEqualing(key)) {
			pressedKeys.addAtEnd(key);
		}
		
		if (key == Key.CAPS_LOCK) {
			shiftIsLocked = !shiftIsLocked;
		}
	}
	
	//method
	@Override
	public void noteKeyRelease(final Key key) {
		pressedKeys.removeFirst(key);
	}
	
	@Override
	public boolean shiftIsLocked() {
		return shiftIsLocked;
	}
}
