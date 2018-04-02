//package declaration
package ch.nolix.element.GUI;

//Java imports
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

//own import
import ch.nolix.primitive.validator2.Validator;

//package-visible class
final class FrameMouseWheelListener
implements MouseWheelListener {

	//attribute
	private final Frame frame;
	
	//constructor
	public FrameMouseWheelListener(final Frame frame) {
		
		Validator
		.suppose(frame)
		.thatIsOfType(Frame.class)
		.isNotNull();
		
		this.frame = frame;
	}
	
	//method
	public void mouseWheelMoved(
		final MouseWheelEvent mouseWheelEvent
	) {		
		frame.noteMouseWheelRotationSteps(
			(int)mouseWheelEvent.getPreciseWheelRotation()
		);
	}
}
