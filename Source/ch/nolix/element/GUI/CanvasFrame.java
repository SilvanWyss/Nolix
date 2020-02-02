//package declaration
package ch.nolix.element.GUI;

//own imports
import ch.nolix.common.states.Visibility;
import ch.nolix.element.input.IInputTaker;

//class
public final class CanvasFrame extends CanvasGUI<CanvasFrame> {
	
	//constructor
	public CanvasFrame(final IInputTaker inputTaker) {
		
		super(inputTaker, Visibility.VISIBLE);
		
		initialize();
	}
	
	//constructor
	public CanvasFrame(final String title, final IInputTaker inputTaker) {
		
		this(inputTaker);
		
		setTitle(title);
	}
	
	//method
	@Override
	public int getHeight() {
		return getRefVisualizer().getHeight();
	}
	
	//method
	@Override
	public int getViewAreaCursorXPosition() {
		return getRefVisualizer().getViewAreaCursorXPosition();
	}

	//method
	@Override
	public int getViewAreaCursorYPosition() {
		return getRefVisualizer().getViewAreaCursorYPosition();
	}
	
	//method
	@Override
	public int getWidth() {
		return getRefVisualizer().getWidth();
	}
	
	//method
	@Override
	public boolean isRootGUI() {
		return true;
	}
	
	//method
	@Override
	public int getViewAreaHeight() {
		return getRefVisualizer().getViewAreaHeight();
	}
	
	//method
	@Override
	public int getViewAreaWidth() {
		return getRefVisualizer().getViewAreaWidth();
	}
}
