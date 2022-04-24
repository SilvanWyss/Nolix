//package declaration
package ch.nolix.system.gui.framevisualizer;

//Java imports
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

//own imports
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.system.gui.base.GUI;
import ch.nolix.systemapi.guiapi.inputapi.Key;

//class
/**
 * @author Silvan Wyss
 * @date 2016-01-01
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
		parentFrame.noteKeyDown(Key.fromAWTKeyEvent(keyEvent));
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
