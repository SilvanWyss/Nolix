//package declaration
package ch.nolix.element.GUI;

//own imports
import ch.nolix.common.state.Visibility;
import ch.nolix.element.input.IResizableInputTaker;

//class
public final class Frame extends LayerGUI<Frame> {
	
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
	
	//constructor
	public Frame(final String title) {
		
		super(Visibility.VISIBLE);
		
		setTitle(title);
		
		initialize();
	}
	
	//constructor
	public Frame(final String title, final Widget<?, ?> rootWidget) {
		
		super(Visibility.VISIBLE);
		
		setTitle(title);
		addLayerOnTop(rootWidget);
		
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
