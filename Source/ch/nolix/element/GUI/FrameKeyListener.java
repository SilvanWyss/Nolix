//package declaration
package ch.nolix.element.GUI;

//Java imports
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

//own imports
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
	 * @throws NullArgumentException if the given frame is null.
	 */
	public FrameKeyListener(final Frame frame) {
		
		//Checks if the given frame is not null.
		Validator.suppose(frame).isInstanceOf(Frame.class);
		
		//Sets the frame of this frame key listener.
		this.frame = frame;
	}

	//method
	@Override
	public void keyPressed(final KeyEvent keyEvent) {
		frame.noteKeyTyping(keyEvent);
	}

	//method
	@Override
	public void keyReleased(final KeyEvent keyEvent) {}

	//method
	@Override
	public void keyTyped(final KeyEvent keyEvent) {}
}
