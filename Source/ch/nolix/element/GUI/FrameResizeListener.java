/*
 * file:	FrameResizeListener.java
 * author:	Silvan Wyss
 * month:	2015
 * lines:	30
 */

//package declaration
package ch.nolix.element.GUI;

//Java imports
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

//class
public class FrameResizeListener implements ComponentListener {
	
	//attributes
	private Frame frame;
	
	//constructor
	public FrameResizeListener(Frame frame) {
		this.frame = frame;
	}

	//method
	@Override
	public void componentHidden(ComponentEvent arg0) {}

	//method
	@Override
	public void componentMoved(ComponentEvent arg0) {}

	//method
	@Override
	public void componentResized(ComponentEvent arg0) {
		getRefFrame().noteResizing();
	}

	//method
	@Override
	public void componentShown(ComponentEvent arg0) {}
	
	private Frame getRefFrame() {
		return frame;
	}
}
