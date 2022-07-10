//package declaration
package ch.nolix.system.gui.framevisualizer;

//Java imports
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.systemapi.guiapi.mainapi.IBaseGUI;

//class
/**
 * @author Silvan Wyss
 * @date 2016-01-01
 */
final class FrameVisualizerCloseListener implements WindowListener {
	
	//attribute
	private final IBaseGUI<?> parentGUI;
	
	//constructor
	/**
	 * Creates a new {@link FrameVisualizerCloseListener} that will belong to the given parentGUI.
	 * 
	 * @param parentGUI
	 * @throws ArgumentIsNullException if the given parentGUI.
	 */
	public FrameVisualizerCloseListener(final IBaseGUI<?> parentGUI) {
		
		//Asserts that the given frame is not null.
		GlobalValidator.assertThat(parentGUI).thatIsNamed("parent GUI").isNotNull();
		
		//Sets the frame of this frame close listener.
		this.parentGUI = parentGUI;
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
		parentGUI.close();
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
