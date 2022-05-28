//package declaration
package ch.nolix.system.gui.base;

//own imports
import ch.nolix.core.state.Visibility;
import ch.nolix.systemapi.guiapi.inputapi.IResizableInputTaker;

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
