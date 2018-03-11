//package declaration
package ch.nolix.element.GUI;

//Java imports
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import ch.nolix.primitive.validator2.Validator;

//package-visible class
/**
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 70
 */
final class FrameMouseListener implements MouseListener {
	
	//attribute
	//Frame a frame mouse listener belongs to.
	private final Frame frame;
	
	//constructor
	/**
	 * Creates new frame mouse listener that belongs to the given frame.
	 * 
	 * @param frame
	 * @throws NullArgumentException if the given frame is null.
	 */
	public FrameMouseListener(final Frame frame) {
		
		//Checks if the given frame is not null.
		Validator.suppose(frame).thatIsInstanceOf(Frame.class).isNotNull();
		
		//Sets the frame of this frame mouse listener.
		this.frame = frame;
	}

	//method
	public final void mouseClicked(final MouseEvent mouseEvent) {}

	//method
	public final void mouseEntered(final MouseEvent mouseEvent) {}

	//method
	public final void mouseExited(final MouseEvent mouseEvent0) {}

	//method
	public final void mousePressed(final MouseEvent mouseEvent) {
		
		//Enumerates the button of the given mouse event.
		switch (mouseEvent.getButton()) {
			case MouseEvent.BUTTON1:
				frame.noteLeftMouseButtonPress();
				break;
			case MouseEvent.BUTTON3:
				frame.noteRightMouseButtonPress();
				break;
		}
	}

	//method
	public final void mouseReleased(final MouseEvent mouseEvent) {
		
		//Enumerates the button of the given mouse event.
		switch (mouseEvent.getButton()) {
			case MouseEvent.BUTTON1:
				frame.noteLeftMouseButtonRelease();
				break;
			case MouseEvent.BUTTON3:
				frame.noteRightMouseButtonRelease();
				break;
		}
	}
}
