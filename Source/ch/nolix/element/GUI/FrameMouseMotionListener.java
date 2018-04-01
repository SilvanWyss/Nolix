//package declaration
package ch.nolix.element.GUI;

//Java imports
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

//own import
import ch.nolix.primitive.validator2.Validator;

//class
public final class FrameMouseMotionListener
implements MouseMotionListener {
	
	//attribute
	private final Frame frame;
	
	//constructor
	public FrameMouseMotionListener(final Frame frame) {
		
		Validator
		.suppose(frame)
		.thatIsOfType(Frame.class)
		.isNotNull();
		
		this.frame = frame;
	}

	//method
	public void mouseMoved(final MouseEvent mouseEvent) {
		frame.noteMouseMove();	
	}

	//method
	public void mouseDragged(final MouseEvent mouseEvent) {
		frame.noteMouseMove();
	}
}
