//package declaration
package ch.nolix.system.gui.framevisualizer;

//Java imports
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.system.gui.main.GUI;

//class
/**
 * @author Silvan Wyss
 * @date 2016-01-01
 */
final class FrameVisualizerCloseListener implements WindowListener {

	//attribute
	//frame this frame close listener belongs to.
	private GUI<?> frameVisualizer;
	
	//constructor
	/**
	 * Creates a new frame close listener that belongs to the given frame.
	 * 
	 * @param frameVisualizer
	 * @throws ArgumentIsNullException if the given frame is null.
	 */
	public FrameVisualizerCloseListener(final GUI<?> frameVisualizer) {
		
		//Asserts that the given frame is not null.
		GlobalValidator.assertThat(frameVisualizer).isOfType(GUI.class);
		
		//Sets the frame of this frame close listener.
		this.frameVisualizer = frameVisualizer;
	}
	
	//method
	@Override
	public void windowActivated(final WindowEvent windowEvent) {
		//Does nothing.
	}

	//method
	@Override
	public void windowClosed(final WindowEvent windowEvent) {
		//Does nothing.
	}
		
	//method
	@Override
	public void windowClosing(final WindowEvent windowEvent) {
		frameVisualizer.close();
	}

	//method
	@Override
	public void windowDeactivated(final WindowEvent windowEvent) {
		//Does nothing.
	}

	// method
	@Override
	public void windowDeiconified(final WindowEvent windowEvent) {
		//Does nothing.
	}

	//method
	@Override
	public void windowIconified(final WindowEvent windowEvent) {
		//Does nothing.
	}

	//method
	@Override
	public void windowOpened(final WindowEvent windowEvent) {
		//Does nothing.
	}
}
