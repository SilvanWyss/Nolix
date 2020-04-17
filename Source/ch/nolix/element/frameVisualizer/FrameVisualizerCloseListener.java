//package declaration
package ch.nolix.element.frameVisualizer;

//Java imports
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

//own imports
import ch.nolix.common.validator.Validator;
import ch.nolix.element.GUI.GUI;

//class
/**
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 60
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
		Validator.assertThat(frameVisualizer).isOfType(GUI.class);
		
		//Sets the frame of this frame close listener.
		this.frameVisualizer = frameVisualizer;
	}
	
	//method
	@Override
	public void windowActivated(final WindowEvent windowEvent) {}

	//method
	@Override
	public void windowClosed(final WindowEvent windowEvent) {}
		
	//method
	@Override
	public void windowClosing(final WindowEvent windowEvent) {
		frameVisualizer.close();
	}

	//method
	@Override
	public void windowDeactivated(final WindowEvent windowEvent) {}

	// method
	@Override
	public void windowDeiconified(final WindowEvent windowEvent) {}

	//method
	@Override
	public void windowIconified(final WindowEvent windowEvent) {}

	//method
	@Override
	public void windowOpened(final WindowEvent windowEvent) {}
}
