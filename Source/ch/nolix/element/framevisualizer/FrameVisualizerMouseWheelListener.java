//package declaration
package ch.nolix.element.framevisualizer;

//Java imports
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import ch.nolix.common.errorcontrol.validator.Validator;
import ch.nolix.element.elementenum.RotationDirection;
import ch.nolix.element.gui.base.GUI;

//class
final class FrameVisualizerMouseWheelListener implements MouseWheelListener {
	
	//attribute
	private final GUI<?> parentGUI;
	
	//constructor
	public FrameVisualizerMouseWheelListener(final GUI<?> parentGUI) {
		
		Validator.assertThat(parentGUI).thatIsNamed(GUI.class).isNotNull();
		
		this.parentGUI = parentGUI;
	}
	
	//method
	@Override
	public void mouseWheelMoved(final MouseWheelEvent mouseWheelEvent) {
		parentGUI.noteMouseWheelRotationStep(getRotationDirection(mouseWheelEvent));
	}
	
	//method
	private RotationDirection getRotationDirection(final MouseWheelEvent mouseWheelEvent) {
		
		if (mouseWheelEvent.getPreciseWheelRotation() > 0) {
			return RotationDirection.FORWARD;
		}
		
		return RotationDirection.BACKWARD;
	}
}
