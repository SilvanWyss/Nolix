//package declaration
package ch.nolix.system.gui.guivisualizer;

//Java imports
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

//own imports
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.systemapi.guiapi.inputapi.Key;
import ch.nolix.systemapi.guiapi.mainapi.IGUI;

//class
final class GUIVisualizerKeyListener implements KeyListener {
	
	//attribute
	private final IGUI<?> parentGUI;
	
	//constructor
	public GUIVisualizerKeyListener(final IGUI<?> parentFrame) {
		
		GlobalValidator.assertThat(parentFrame).thatIsNamed("parent Frame").isNotNull();
		
		this.parentGUI = parentFrame;
	}
	
	//method
	@Override
	public void keyPressed(final KeyEvent keyEvent) {
		parentGUI.noteKeyPress(Key.fromAWTKeyEvent(keyEvent));
	}
	
	//method
	@Override
	public void keyReleased(final KeyEvent keyEvent) {
		parentGUI.noteKeyRelease(Key.fromAWTKeyEvent(keyEvent));
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
			parentGUI.noteKeyTyping(Key.fromCharacter(character));
		}
	}
}
