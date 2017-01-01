/*
 * file:	FrameKeyListener.java
 * author:	Silvan Wyss
 * month:	2015
 * lines:	50
 */

//package declaration
package ch.nolix.element.dialog;

//Java imports
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

//class
final class FrameKeyListener implements KeyListener {
	
	//attribute
	private final Frame frame;
	
	//constructor
	/**
	 * Creates new frame key listener that belongs to the given frame.
	 * @param frame
	 */
	public FrameKeyListener(Frame frame) {
		this.frame = frame;
	}

	//method
	public void keyPressed(KeyEvent keyEvent) {
		frame.notePressedKey(keyEvent);
	}

	//method
	public void keyReleased(KeyEvent arg0) {}

	//method
	public void keyTyped(KeyEvent keyEvent) {
		frame.noteTypedKey(keyEvent);
	}
}
