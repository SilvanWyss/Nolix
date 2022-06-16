//package declaration
package ch.nolix.system.gui.base;

import ch.nolix.core.programatom.stateproperty.Visibility;
import ch.nolix.systemapi.guiapi.inputapi.IResizableInputTaker;

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
