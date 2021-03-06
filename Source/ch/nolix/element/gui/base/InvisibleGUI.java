//package declaration
package ch.nolix.element.gui.base;

//own imports
import ch.nolix.common.state.Visibility;

//class
public class InvisibleGUI extends WidgetGUI<InvisibleGUI> {
	
	//constructor
	public InvisibleGUI() {
		
		super(Visibility.INVISIBLE);
		
		reset();
	}
	
	//constructor
	public InvisibleGUI(final Widget<?, ?> rootWidget) {
		
		this();
		
		addLayerOnTop(rootWidget);
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
