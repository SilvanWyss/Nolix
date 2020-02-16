//package declaration
package ch.nolix.element.GUI;

//own import
import ch.nolix.common.states.Visibility;

//class
public class InvisibleLayerGUI extends LayerGUI<InvisibleLayerGUI> {
	
	//constructor
	public InvisibleLayerGUI() {
		
		super(Visibility.INVISIBLE);
		
		reset();
	}
	
	//constructor
	public InvisibleLayerGUI(final Widget<?, ?> rootWidget) {
		
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
