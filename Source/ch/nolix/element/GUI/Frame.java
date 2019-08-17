//package declaration
package ch.nolix.element.GUI;

import ch.nolix.element.baseGUI_API.IEventTaker;

//class
public final class Frame extends LayerGUI<Frame> {
	
	//constructor
	public Frame() {
		
		super(true);
		
		initialize();
	}
	
	//constructor
	public Frame(final IEventTaker eventTaker) {
		
		super(true, eventTaker);
		
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
	protected int getViewAreaCursorXPosition() {
		return getRefVisualizer().getViewAreaCursorXPosition();
	}
	
	//method
	@Override
	protected int getViewAreaCursorYPosition() {
		return getRefVisualizer().getViewAreaCursorYPosition();
	}
	
	//method
	@Override
	protected int getViewAreaHeight() {
		return getRefVisualizer().getViewAreaHeight();
	}
	
	//method
	@Override
	protected int getViewAreaWidth() {
		return getRefVisualizer().getViewAreaWidth();
	}
}
