//package declaration
package ch.nolix.element.frameVisualizer;

//Java imports
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

//own imports
import ch.nolix.core.validator.Validator;
import ch.nolix.element.GUI.GUI;

//class
public final class FrameVisualizerMouseMotionListener implements MouseMotionListener {
	
	//attribute
	private final GUI<?> frameVisualizer;
	
	//constructor
	public FrameVisualizerMouseMotionListener(final GUI<?> frameVisualizer) {
		
		Validator.suppose(frameVisualizer).isOfType(FrameVisualizer.class);
		
		this.frameVisualizer = frameVisualizer;
	}

	//method
	@Override
	public void mouseMoved(final MouseEvent mouseEvent) {
		frameVisualizer.noteMouseMove(mouseEvent.getX(), mouseEvent.getY());
	}
	
	//method
	@Override
	public void mouseDragged(final MouseEvent mouseEvent) {
		frameVisualizer.noteMouseMove(mouseEvent.getX(), mouseEvent.getY());
	}
}
