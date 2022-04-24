//package declaration
package ch.nolix.system.gui.base;

//own imports
import ch.nolix.core.state.Visibility;
import ch.nolix.system.gui.widget.Widget;

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
