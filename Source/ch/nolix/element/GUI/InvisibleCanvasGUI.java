//package declaration
package ch.nolix.element.GUI;

//own imports
import ch.nolix.common.states.Visibility;
import ch.nolix.common.validator.Validator;
import ch.nolix.element.input.IInputTaker;

//class
public final class InvisibleCanvasGUI extends CanvasGUI<InvisibleCanvasGUI> {
	
	//attributes
	private int viewAreaWidth;
	private int viewAreaHeight;
	private int viewAreaCursorXPosition;
	private int viewAreaCursorYPosition;
	
	//constructor
	public InvisibleCanvasGUI(final IInputTaker inputTaker) {
		super(inputTaker, Visibility.INVISIBLE);
	}
	
	//method
	@Override
	public int getHeight() {
		return viewAreaHeight;
	}
	
	//method
	@Override
	public int getWidth() {
		return viewAreaWidth;
	}
	
	//method
	@Override
	public int getViewAreaCursorXPosition() {
		return viewAreaCursorXPosition;
	}

	//method
	@Override
	public int getViewAreaCursorYPosition() {
		return viewAreaCursorYPosition;
	}

	//method
	@Override
	public int getViewAreaHeight() {
		return viewAreaHeight;
	}

	//method
	@Override
	public int getViewAreaWidth() {
		return viewAreaWidth;
	}

	//method
	@Override
	public boolean isRootGUI() {
		return true;
	}
	
	//method
	@Override
	public void noteResize(int viewAreaWidth, int viewAreaHeight) {
		
		super.noteResize(viewAreaWidth, viewAreaHeight);
		
		this.viewAreaWidth = viewAreaWidth;
		
		Validator.suppose(viewAreaHeight).thatIsNamed("view arew height").isNotNegative();
		
		this.viewAreaHeight = viewAreaHeight;
	}

	//method
	public void setViewAreaCursorXPosition(final int viewAreaCursorXPosition) {
		this.viewAreaCursorXPosition = viewAreaCursorXPosition;
	}
	
	//method
	public void setViewAreaCursorYPosition(final int viewAreaCursorYPosition) {
		this.viewAreaCursorYPosition = viewAreaCursorYPosition;
	}
}
