//package declaration
package ch.nolix.system.gui.framevisualizer;

//Java imports
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

//own imports
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.systemapi.guiapi.mainapi.IBaseGUI;
import ch.nolix.systemapi.guiapi.processproperty.RotationDirection;

//class
final class FrameVisualizerMouseWheelListener implements MouseWheelListener {
	
	//attribute
	private final IBaseGUI<?> parentGUI;
	
	//constructor
	public FrameVisualizerMouseWheelListener(final IBaseGUI<?> parentGUI) {
		
		GlobalValidator.assertThat(parentGUI).thatIsNamed("parent GUI").isNotNull();
		
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
