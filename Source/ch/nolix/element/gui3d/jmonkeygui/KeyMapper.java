//package declaration
package ch.nolix.element.gui3d.jmonkeygui;

//JMonkey import
import com.jme3.input.event.KeyInputEvent;

import ch.nolix.element.gui.input.Key;

//class
public final class KeyMapper {
	
	//method
	public Key getKeyFrom(final KeyInputEvent keyInputEvent) {
		return Key.fromCharacter(keyInputEvent.getKeyChar());
	}
}
