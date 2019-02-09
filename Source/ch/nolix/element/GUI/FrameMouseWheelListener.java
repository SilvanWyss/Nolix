//package declaration
package ch.nolix.element.GUI;

//Java imports
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import ch.nolix.core.validator.Validator;

//package-visible class
final class FrameMouseWheelListener
implements MouseWheelListener {

	//attribute
	private final Frame frame;
	
	//constructor
	public FrameMouseWheelListener(final Frame frame) {
		
		Validator.suppose(frame).isOfType(Frame.class);
		
		this.frame = frame;
	}
	
	//method
	@Override
	public void mouseWheelMoved(
		final MouseWheelEvent mouseWheelEvent
	) {
		frame.noteMouseWheelRotationSteps(
			(int)mouseWheelEvent.getPreciseWheelRotation()
		);
	}
}
