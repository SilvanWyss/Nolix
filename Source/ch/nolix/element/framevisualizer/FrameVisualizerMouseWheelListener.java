//package declaration
package ch.nolix.element.framevisualizer;

//Java imports
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

//own imports
import ch.nolix.common.validator.Validator;
import ch.nolix.element.elementenum.RotationDirection;
import ch.nolix.element.gui.GUI;

//class
final class FrameVisualizerMouseWheelListener implements MouseWheelListener {

	//attribute
	private final GUI<?> frameVisualizer;
	
	//constructor
	public FrameVisualizerMouseWheelListener(final GUI<?> frameVisualizer) {
		
		Validator.assertThat(frameVisualizer).isOfType(FrameVisualizer.class);
		
		this.frameVisualizer = frameVisualizer;
	}
	
	//method
	@Override
	public void mouseWheelMoved(
		final MouseWheelEvent mouseWheelEvent
	) {
		frameVisualizer.noteMouseWheelRotationStep(
			mouseWheelEvent.getPreciseWheelRotation() > 0 ? RotationDirection.Forward : RotationDirection.Backward
		);
	}
}
