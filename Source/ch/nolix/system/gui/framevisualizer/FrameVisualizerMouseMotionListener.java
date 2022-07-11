//package declaration
package ch.nolix.system.gui.framevisualizer;

//Java imports
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

//own imports
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programcontrol.sequencer.GlobalSequencer;
import ch.nolix.systemapi.guiapi.mainapi.IBaseGUI;

//class
final class FrameVisualizerMouseMotionListener implements MouseMotionListener {
	
	//attribute
	private boolean isNew = true;
	
	//attribute
	private final IBaseGUI<?> parentGUI;
		
	//constructor
	public FrameVisualizerMouseMotionListener(final IBaseGUI<?> parentGUI) {
		
		GlobalValidator.assertThat(parentGUI).thatIsNamed("parent GUI").isNotNull();
		
		this.parentGUI = parentGUI;
	}
	
	//method
	@Override
	public void mouseMoved(final MouseEvent mouseEvent) {
		
		//This is important because events can be fired before the GUI is created completely.
		if (isNew) {
			GlobalSequencer.waitForMilliseconds(200);
			isNew = false;
		}
		
		parentGUI.noteMouseMove(mouseEvent.getX(), mouseEvent.getY());
	}
	
	//method
	@Override
	public void mouseDragged(final MouseEvent mouseEvent) {
		parentGUI.noteMouseMove(mouseEvent.getX(), mouseEvent.getY());
	}
}
