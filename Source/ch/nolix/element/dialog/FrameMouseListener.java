/*
 * file:	FrameMouseListener
 * author:	Silvan Wyss
 * month:	2015
 * lines:	50
 */

//package declaration
package ch.nolix.element.dialog;

//Java imports
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

//class
final class FrameMouseListener implements MouseListener {
	
	//attribute
	private final Frame frame;
	
	//constructor
	/**
	 * Creates new frame mouse listener that belongs to the given frame.
	 * @param frame
	 */
	public FrameMouseListener(Frame frame) {
		this.frame = frame;
	}

	//method
	public final void mouseClicked(MouseEvent arg0) {
		
		//frame.noteLeftClicked();
	}

	//method
	public final void mouseEntered(MouseEvent arg0) {
		
	}

	//method
	public final void mouseExited(MouseEvent arg0) {}

	//method
	public final void mousePressed(MouseEvent mouseEvent) {
		if (mouseEvent.getButton() == MouseEvent.BUTTON1) {
			frame.noteLeftMouseButtonPress();
		}
	}

	//method
	public final void mouseReleased(MouseEvent arg0) {}
}
