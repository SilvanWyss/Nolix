//package declaration
package ch.nolix.element.GUI;

//Java imports
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

//own import


import ch.nolix.core.validator2.ZetaValidator;

//package-visible class
/**
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 60
 */
final class FrameKeyListener implements KeyListener {
	
	//attribute
	private final Frame frame;
	
	//constructor
	/**
	 * Creates new frame key listener that belongs to the given frame.
	 * 
	 * @param frame
	 * @throws NullArgumentException if the given frame is null.
	 */
	public FrameKeyListener(final Frame frame) {
		
		//Checks if the given frame is not null.
		ZetaValidator.supposeThat(frame).thatIsInstanceOf(Frame.class).isNotNull();
		
		//Sets the frame of this frame key listener.
		this.frame = frame;
	}

	//method
	/**
	 * Lets this frame key listener note a key press.
	 */
	public void keyPressed(final KeyEvent keyEvent) {
		frame.noteKeyTyping(keyEvent);
	}

	//method
	/**
	 * Lets this frame key listener note a key release.
	 */
	public void keyReleased(final KeyEvent keyEvent) {
		//Does nothing.
	}

	//method
	/**
	 * Lets this frame key listener note a key typing.
	 */
	public void keyTyped(final KeyEvent keyEvent) {
		//Does nothing.
	}
}
