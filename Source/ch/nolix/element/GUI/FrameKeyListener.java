//package declaration
package ch.nolix.element.GUI;

//Java imports
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import ch.nolix.core.validator2.Validator;

//package-visible class
/**
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 40
 */
final class FrameKeyListener implements KeyListener {
	
	//attribute
	//frame this frame key listener belongs to
	private final Frame frame;
	
	//constructor
	/**
	 * Creates a new frame key listener that belongs to the given frame.
	 * 
	 * @param frame
	 * @throws NullArgumentException if the given frame is not an instance.
	 */
	public FrameKeyListener(final Frame frame) {
		
		//Checks if the given frame is an instance.
		Validator.suppose(frame).isInstanceOf(Frame.class);
		
		//Sets the frame of this frame key listener.
		this.frame = frame;
	}

	//method
	public void keyPressed(final KeyEvent keyEvent) {
		frame.noteKeyTyping(keyEvent);
	}

	//method
	public void keyReleased(final KeyEvent keyEvent) {}

	//method
	public void keyTyped(final KeyEvent keyEvent) {}
}
