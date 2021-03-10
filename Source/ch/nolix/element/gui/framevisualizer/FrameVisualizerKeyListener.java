//package declaration
package ch.nolix.element.gui.framevisualizer;

//Java imports
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import ch.nolix.common.errorcontrol.validator.Validator;
import ch.nolix.element.gui.base.GUI;
import ch.nolix.element.gui.input.Key;

//class
/**
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 60
 */
final class FrameVisualizerKeyListener implements KeyListener {
	
	//attribute
	private final GUI<?> parentFrame;
	
	//constructor
	public FrameVisualizerKeyListener(final GUI<?> parentFrame) {
		
		//Asserts that the given frame is not null.
		Validator.assertThat(parentFrame).thatIsNamed("parent Frame").isNotNull();
		
		//Sets the frame of the current FrameKeyListener.
		this.parentFrame = parentFrame;
	}
	
	//method
	@Override
	public void keyPressed(final KeyEvent keyEvent) {
		parentFrame.noteKeyPress(Key.fromAWTKeyEvent(keyEvent));
	}
	
	//method
	@Override
	public void keyReleased(final KeyEvent keyEvent) {
		parentFrame.noteKeyRelease(Key.fromAWTKeyEvent(keyEvent));
	}
	
	//method
	@Override
	public void keyTyped(final KeyEvent keyEvent) {
		
		/*
		 * The keyTypes method is special.
		 * The given keyEvent is meaningful only when a key has been typed, that represents a character.
		 * Additionally, the keyEvent will store the typed character as key char whereas the key code will be always 0.
		 */
		final var character = keyEvent.getKeyChar();
		if  (Character.isAlphabetic(character) || Character.isDigit(character)) {
			parentFrame.noteKeyTyping(Key.fromCharacter(character));
		}
	}
}
