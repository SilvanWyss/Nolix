//package declaration
package ch.nolix.element.frameVisualizer;

//Java imports
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

//own imports
import ch.nolix.common.validator.Validator;
import ch.nolix.element.GUI.GUI;
import ch.nolix.element.input.Key;

//class
/**
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 50
 */
final class FrameVisualizerKeyListener implements KeyListener {
	
	//attribute
	private final GUI<?> parentFrame;
	
	//constructor
	public FrameVisualizerKeyListener(final GUI<?> parentFrame) {
		
		//Checks if the given frame is not null.
		Validator.suppose(parentFrame).thatIsNamed("parent Frame").isNotNull();
		
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
		parentFrame.noteKeyTyping(Key.fromAWTKeyEvent(keyEvent));
	}
}
