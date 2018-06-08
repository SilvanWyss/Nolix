//package declaration
package ch.nolix.element.GUI;

//Java imports
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import ch.nolix.primitive.validator2.Validator;

//package-visible class
/**
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 60
 */
final class FrameCloseListener implements WindowListener {

	//attribute
	//frame this frame close listener belongs to.
	private Frame frame;
	
	//constructor
	/**
	 * Creates a new frame close listener that belongs to the given frame.
	 * 
	 * @param frame
	 * @throws NullArgumentException if the given frame is null.
	 */
	public FrameCloseListener(final Frame frame) {
		
		//Checks if the given frame is not null.
		Validator.suppose(frame).thatIsOfType(Frame.class).isNotNull();
		
		//Sets the frame of this frame close listener.
		this.frame = frame;
	}
	
	//method
	public void windowActivated(final WindowEvent windowEvent) {}

	//method
	public void windowClosed(final WindowEvent windowEvent) {}
		
	//method
	public void windowClosing(final WindowEvent windowEvent) {
		frame.close();
	}			

	//method
	public void windowDeactivated(final WindowEvent windowEvent) {}		

	// method
	public void windowDeiconified(final WindowEvent windowEvent) {}			

	//method
	public void windowIconified(final WindowEvent windowEvent) {}			

	//method
	public void windowOpened(final WindowEvent windowEvent) {}	
}
