//package declaration
package ch.nolix.element.GUI;

import ch.nolix.common.state.Visibility;
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
	public void noteMouseMove(final int viewAreaCursorXPosition, final int viewAreaCursorYPosition) {
		
		super.noteMouseMove(viewAreaCursorXPosition, viewAreaCursorYPosition);
		
		this.viewAreaCursorXPosition = viewAreaCursorXPosition;
		this.viewAreaCursorYPosition = viewAreaCursorYPosition;
	}
	
	//method
	@Override
	public void noteResize(final int viewAreaWidth,final int viewAreaHeight) {
		
		super.noteResize(viewAreaWidth, viewAreaHeight);
		
		this.viewAreaWidth = viewAreaWidth;
		
		Validator.assertThat(viewAreaHeight).thatIsNamed("view arew height").isNotNegative();
		
		this.viewAreaHeight = viewAreaHeight;
	}
}
