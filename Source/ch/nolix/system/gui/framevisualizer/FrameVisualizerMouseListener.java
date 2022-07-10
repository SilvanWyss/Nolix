//package declaration
package ch.nolix.system.gui.framevisualizer;

//Java imports
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

//own imports
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.system.gui.main.GUI;

//class
final class FrameVisualizerMouseListener implements MouseListener {
	
	//attribute
	private final GUI<?> parentGUI;
	
	//constructor
	public FrameVisualizerMouseListener(final GUI<?> parentGUI) {
		
		GlobalValidator.assertThat(parentGUI).thatIsNamed("parent GUI").isNotNull();
		
		this.parentGUI = parentGUI;
	}
	
	//method
	@Override
	public void mouseClicked(final MouseEvent mouseEvent) {
		//Does nothing.
	}
	
	//method
	@Override
	public void mouseEntered(final MouseEvent mouseEvent) {
		//Does nothing.
	}
	
	//method
	@Override
	public void mouseExited(final MouseEvent mouseEvent0) {
		//Does nothing.
	}
	
	//method
	@Override
	public void mousePressed(final MouseEvent mouseEvent) {
		
		//Enumerates the button of the given mouse event.
		switch (mouseEvent.getButton()) {
			case MouseEvent.BUTTON1:
				parentGUI.noteLeftMouseButtonPress();
				break;
			case MouseEvent.BUTTON3:
				parentGUI.noteRightMouseButtonPress();
				break;
			default:
		}
	}
	
	//method
	@Override
	public void mouseReleased(final MouseEvent mouseEvent) {
		
		//Enumerates the button of the given mouse event.
		switch (mouseEvent.getButton()) {
			case MouseEvent.BUTTON1:
				parentGUI.noteLeftMouseButtonRelease();
				break;
			case MouseEvent.BUTTON3:
				parentGUI.noteRightMouseButtonRelease();
				break;
			default:
		}
	}
}
