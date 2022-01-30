//package declaration
package ch.nolix.element.gui.base;

import ch.nolix.core.state.Visibility;
import ch.nolix.element.gui.input.IResizableInputTaker;

//class
public final class Frame extends WidgetGUI<Frame> {
	
	//constructor
	public Frame() {
		
		super(Visibility.VISIBLE);
		
		initialize();
	}
	
	//constructor
	public Frame(final IResizableInputTaker inputTaker) {
		
		super(Visibility.VISIBLE, inputTaker);
		
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
