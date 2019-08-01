//package declaration
package ch.nolix.element.frameVisualizer;

//Java imports
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

//own import
import ch.nolix.element.GUI.GUI;

//class
public class FrameVisualizerResizeListener implements ComponentListener {
	
	//attributes
	private GUI<?> frameVisualizer;
	
	//constructor
	public FrameVisualizerResizeListener(GUI<?> frameVisualizer) {
		this.frameVisualizer = frameVisualizer;
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
		this.frameVisualizer.noteResize(
			arg0.getComponent().getWidth(),
			arg0.getComponent().getHeight()
		);
	}

	//method
	@Override
	public void componentShown(ComponentEvent arg0) {}
}
