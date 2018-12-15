//package declaration
package ch.nolix.element.GUI;

//Java imports
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

//own imports
import ch.nolix.core.validator2.Validator;

//class
public final class FrameMouseMotionListener
implements MouseMotionListener {
	
	//attribute
	private final Frame frame;
	
	//constructor
	public FrameMouseMotionListener(final Frame frame) {
		
		Validator.suppose(frame).isInstanceOf(Frame.class);
		
		this.frame = frame;
	}

	//method
	@Override
	public void mouseMoved(final MouseEvent mouseEvent) {
		frame.noteMouseMove();
	}

	//method
	@Override
	public void mouseDragged(final MouseEvent mouseEvent) {
		frame.noteMouseMove();
	}
}
