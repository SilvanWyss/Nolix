//package declaration
package ch.nolix.system.gui.framevisualizer;

//Java imports
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.system.gui.main.GUI;

//class
/**
 * @author Silvan Wyss
 * @date 2016-01-01
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
		GlobalValidator.assertThat(frameVisualizer).isOfType(GUI.class);
		
		//Sets the frame of this frame mouse listener.
		this.frameVisualizer = frameVisualizer;
	}

	//method
	@Override
	public void mouseClicked(final MouseEvent mouseEvent) {
		//Does nothing.
	}

	//method
	@Override
	public void mouseEntered(final MouseEvent mouseEvent) {
		//Does nothing.
	}

	//method
	@Override
	public void mouseExited(final MouseEvent mouseEvent0) {
		//Does nothing.
	}

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
