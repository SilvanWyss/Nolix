//package declaration
package ch.nolix.element.GUI;

//own import
import ch.nolix.element.input.IInputTaker;

//class
public final class Frame extends LayerGUI<Frame> {
	
	//constructor
	public Frame() {
		
		super(true);
		
		initialize();
	}
	
	//constructor
	public Frame(final IInputTaker inputTaker) {
		
		super(true, inputTaker);
		
		initialize();
	}
	
	//constructor
	public Frame(final String title) {
		
		this();
		
		setTitle(title);
	}
	
	//constructor
	public Frame(final String title, final Widget<?, ?> rootWidget) {
		
		this(title);
		
		addLayerOnTop(rootWidget);
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
	public int getViewAreaHeight() {
		return getRefVisualizer().getViewAreaHeight();
	}
	
	//method
	@Override
	public int getViewAreaWidth() {
		return getRefVisualizer().getViewAreaWidth();
	}
}
