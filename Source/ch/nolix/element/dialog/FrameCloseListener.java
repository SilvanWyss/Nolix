//package declaration
package ch.nolix.element.dialog;

//Java imports
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

//class
public final class FrameCloseListener implements WindowListener {

	//attributes
	private Frame frame;
	
	//constructor
	public FrameCloseListener(Frame frame) {
		this.frame = frame;
	}
	
	//method
	public void windowActivated(WindowEvent windowEvent) {}

	//method
	public void windowClosed(WindowEvent windowEvent) {}
		
	//method
	public void windowClosing(WindowEvent windowEvent) {
		try {
			frame.noteCloseButtonClick();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}			

	//method
	public void windowDeactivated(WindowEvent windowEvent) {}		

	// method
	public void windowDeiconified(WindowEvent windowEvent) {}			

	//method
	public void windowIconified(WindowEvent windowEvent) {}			

	//method
	public void windowOpened(WindowEvent arg0) {}	
}
