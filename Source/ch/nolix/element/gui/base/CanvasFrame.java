//package declaration
package ch.nolix.element.gui.base;

//own imports
import ch.nolix.core.state.Visibility;
import ch.nolix.element.gui.input.IResizableInputTaker;

//class
public final class CanvasFrame extends CanvasGUI<CanvasFrame> {
	
	//constructor
	public CanvasFrame(final IResizableInputTaker inputTaker) {
		
		super(inputTaker, Visibility.VISIBLE);
		
		initialize();
	}
	
	//method
	@Override
	public int getHeight() {
		return getRefVisualizer().getHeight();
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
}
