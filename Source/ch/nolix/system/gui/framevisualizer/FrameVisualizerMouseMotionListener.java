//package declaration
package ch.nolix.system.gui.framevisualizer;

//Java imports
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

//own imports
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.core.programcontrol.sequencer.Sequencer;
import ch.nolix.system.gui.base.GUI;

//class
public final class FrameVisualizerMouseMotionListener implements MouseMotionListener {
	
	//attribute
	private final GUI<?> mGUI;
	private boolean isNew = true;
	
	//constructor
	public FrameVisualizerMouseMotionListener(final GUI<?> frameVisualizer) {
		
		Validator.assertThat(frameVisualizer).isOfType(FrameVisualizer.class);
		
		this.mGUI = frameVisualizer;
	}
	
	//method
	@Override
	public void mouseMoved(final MouseEvent mouseEvent) {
		
		//This is important because events can be fired before the GUI is created completely.
		if (isNew) {
			Sequencer.waitForMilliseconds(200);
			isNew = false;
		}
		
		mGUI.noteMouseMove(mouseEvent.getX(), mouseEvent.getY());
	}
	
	//method
	@Override
	public void mouseDragged(final MouseEvent mouseEvent) {
		mGUI.noteMouseMove(mouseEvent.getX(), mouseEvent.getY());
	}
}
