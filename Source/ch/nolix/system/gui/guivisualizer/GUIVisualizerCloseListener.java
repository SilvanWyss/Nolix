//package declaration
package ch.nolix.system.gui.guivisualizer;

//Java imports
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

//own imports
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.systemapi.guiapi.mainapi.IBaseGUI;

//class
final class GUIVisualizerCloseListener implements WindowListener {
	
	//attribute
	private final IBaseGUI<?> parentGUI;
	
	//constructor
	public GUIVisualizerCloseListener(final IBaseGUI<?> parentGUI) {
		
		GlobalValidator.assertThat(parentGUI).thatIsNamed("parent GUI").isNotNull();
		
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
