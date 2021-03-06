//package declaration
package ch.nolix.element.gui.base;

//own imports
import ch.nolix.common.state.Visibility;
import ch.nolix.element.input.IResizableInputTaker;

//class
public final class InvisibleCanvasGUI extends CanvasGUI<InvisibleCanvasGUI> {
		
	//constructor
	public InvisibleCanvasGUI(final IResizableInputTaker inputTaker) {
		super(inputTaker, Visibility.INVISIBLE);
	}
	
	//method
	@Override
	public int getHeight() {
		return getViewAreaHeight();
	}
	
	//method
	@Override
	public int getWidth() {
		return getViewAreaWidth();
	}
	
	//method
	@Override
	public boolean isRootGUI() {
		return true;
	}
}
