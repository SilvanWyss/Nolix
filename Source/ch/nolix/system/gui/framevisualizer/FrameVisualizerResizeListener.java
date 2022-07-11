//package declaration
package ch.nolix.system.gui.framevisualizer;

//Java imports
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

//own imports
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programcontrol.sequencer.GlobalSequencer;
import ch.nolix.systemapi.guiapi.mainapi.IBaseGUI;

//class
final class FrameVisualizerResizeListener implements ComponentListener {
	
	//attribute
	private boolean isNew = true;
	
	//attribute
	private IBaseGUI<?> parentGUI;
	
	//constructor
	public FrameVisualizerResizeListener(final IBaseGUI<?> parentGUI) {
		
		GlobalValidator.assertThat(parentGUI).thatIsNamed("parent GUI").isNotNull();
		
		this.parentGUI = parentGUI;
	}
	
	//method
	@Override
	public void componentHidden(final ComponentEvent componentEvent) {
		//Does nothing.
	}
	
	//method
	@Override
	public void componentMoved(final ComponentEvent componentEvent) {
		//Does nothing.
	}
	
	//method
	@Override
	public void componentResized(final ComponentEvent componentEvent) {
		
		//This is important because events can be fired before the GUI is created completely.
		if (isNew) {
			GlobalSequencer.waitForMilliseconds(200);
			isNew = false;
		}
		
		this.parentGUI.noteResize(
			componentEvent.getComponent().getWidth(),
			componentEvent.getComponent().getHeight()
		);
	}
	
	//method
	@Override
	public void componentShown(final ComponentEvent componentEvent) {
		//Does nothing.
	}
}
