package ch.nolix.element.jmonkeygui;

import com.jme3.input.event.KeyInputEvent;

import ch.nolix.element.input.Key;

public final class JMonkeyKeyMapper {
	
	public Key getKeyFrom(final KeyInputEvent keyInputEvent) {
		return Key.fromCharacter(keyInputEvent.getKeyChar());
	}
}
