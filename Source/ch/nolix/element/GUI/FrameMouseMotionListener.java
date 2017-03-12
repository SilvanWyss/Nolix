/*
 * file:	FrameMouseMoitionListener
 * author:	Silvan Wyss
 * month:	2015
 * lines:	30
 */

//package declaration
package ch.nolix.element.GUI;

//Java imports
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

//realizing class
public final class FrameMouseMotionListener implements MouseMotionListener {
	
	//attributes
	private Frame frame;
	
	//constructor
	public FrameMouseMotionListener(Frame frame) {
		this.frame = frame;
	}

	//method
	public void mouseMoved(MouseEvent mouseEvent) {
		frame.setMousePosition(mouseEvent.getX(), mouseEvent.getY());
		frame.noteMouseMove();
	}

	//method
	public void mouseDragged(MouseEvent mouseEvent) {}
}
