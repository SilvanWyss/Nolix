/*
 * file:	FrameContext.java
 * author:	Silvan Wyss
 * month:	2015
 * lines:	40
 */

//package declaration
package ch.nolix.element.GUI;

//class
final class FrameContext {
	
	//attribute
	private final Frame frame;
	
	//constructor
	/**
	 * Creates new frame context that belongs to the given frame.
	 * @param frame
	 */
	public FrameContext(Frame frame) {
		this.frame = frame;
	}
	
	public int getMouseXPosition() {
		if (frame.getRefPanel().getMousePosition() != null)
		return (int)frame.getRefPanel().getMousePosition().getX();
		return 0;
	}
	
	public int getMouseYPosition() {
		if (frame.getRefPanel().getMousePosition() != null)
		return (int)frame.getRefPanel().getMousePosition().getY();
		return 0;
	}
		
	//method
	/**
	 * Lets the frame of this frame context set the given cursor icon.
	 * @param cursorIcon
	 */
	public final void setCurrentCursorIcon(CursorIcon cursorIcon) {
		getRefFrame().showCursorIcon(cursorIcon);
	}
	
	//method
	/**
	 * @return the frame of this frame context
	 */
	private Frame getRefFrame() {
		return frame;
	}
}
