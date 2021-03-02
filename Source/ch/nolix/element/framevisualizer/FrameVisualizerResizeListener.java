//package declaration
package ch.nolix.element.framevisualizer;

//Java imports
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import ch.nolix.common.errorcontrol.validator.Validator;
//own imports
import ch.nolix.common.sequencer.Sequencer;
import ch.nolix.element.gui.GUI;

//class
public class FrameVisualizerResizeListener implements ComponentListener {
	
	//attributes
	private GUI<?> mParentGUI;
	private boolean isNew = true;
	
	//constructor
	public FrameVisualizerResizeListener(GUI<?> mParentGUI) {
		
		Validator.assertThat(mParentGUI).thatIsNamed("parent GUI").isNotNull();
		
		this.mParentGUI = mParentGUI;
	}
	
	//method
	@Override
	public void componentHidden(ComponentEvent arg0) {}
	
	//method
	@Override
	public void componentMoved(ComponentEvent arg0) {}
	
	//method
	@Override
	public void componentResized(ComponentEvent arg0) {
		
		//This is important because events can be fired before the GUI is created completely.
		if (isNew) {
			Sequencer.waitForMilliseconds(200);
			isNew = false;
		}
		
		this.mParentGUI.noteResize(
			arg0.getComponent().getWidth(),
			arg0.getComponent().getHeight()
		);
	}
	
	//method
	@Override
	public void componentShown(ComponentEvent arg0) {}
}
