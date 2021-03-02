//package declaration
package ch.nolix.element.framevisualizer;

//Java imports
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import ch.nolix.common.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.common.errorcontrol.validator.Validator;
import ch.nolix.element.gui.GUI;

//class
/**
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 70
 */
final class FrameVisualizerMouseListener implements MouseListener {
	
	//attribute
	//Frame a frame mouse listener belongs to.
	private final GUI<?> frameVisualizer;
	
	//constructor
	/**
	 * Creates a new frame mouse listener that belongs to the given frame.
	 * 
	 * @param frameVisualizer
	 * @throws ArgumentIsNullException if the given frame is null.
	 */
	public FrameVisualizerMouseListener(final GUI<?> frameVisualizer) {
		
		//Asserts that the given frame is not null.
		Validator.assertThat(frameVisualizer).isOfType(GUI.class);
		
		//Sets the frame of this frame mouse listener.
		this.frameVisualizer = frameVisualizer;
	}

	//method
	@Override
	public void mouseClicked(final MouseEvent mouseEvent) {}

	//method
	@Override
	public void mouseEntered(final MouseEvent mouseEvent) {}

	//method
	@Override
	public void mouseExited(final MouseEvent mouseEvent0) {}

	//method
	@Override
	public void mousePressed(final MouseEvent mouseEvent) {
		
		//Enumerates the button of the given mouse event.
		switch (mouseEvent.getButton()) {
			case MouseEvent.BUTTON1:
				frameVisualizer.noteLeftMouseButtonPress();
				break;
			case MouseEvent.BUTTON3:
				frameVisualizer.noteRightMouseButtonPress();
				break;
			default:
		}
	}

	//method
	@Override
	public void mouseReleased(final MouseEvent mouseEvent) {
		
		//Enumerates the button of the given mouse event.
		switch (mouseEvent.getButton()) {
			case MouseEvent.BUTTON1:
				frameVisualizer.noteLeftMouseButtonRelease();
				break;
			case MouseEvent.BUTTON3:
				frameVisualizer.noteRightMouseButtonRelease();
				break;
			default:
		}
	}
}
