//package declaration
package ch.nolix.element.frameVisualizer;

//Java imports
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import ch.nolix.common.validator.Validator;
import ch.nolix.element.GUI.GUI;
import ch.nolix.element.elementEnums.DirectionOfRotation;

//package-visible class
final class FrameVisualizerMouseWheelListener implements MouseWheelListener {

	//attribute
	private final GUI<?> frameVisualizer;
	
	//constructor
	public FrameVisualizerMouseWheelListener(final GUI<?> frameVisualizer) {
		
		Validator.suppose(frameVisualizer).isOfType(FrameVisualizer.class);
		
		this.frameVisualizer = frameVisualizer;
	}
	
	//method
	@Override
	public void mouseWheelMoved(
		final MouseWheelEvent mouseWheelEvent
	) {
		frameVisualizer.noteMouseWheelRotationStep(
			mouseWheelEvent.getPreciseWheelRotation() > 0 ? DirectionOfRotation.Forward : DirectionOfRotation.Backward
		);
	}
}
