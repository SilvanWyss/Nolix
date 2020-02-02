//package declaration
package ch.nolix.element.GUI;

//own imports
import ch.nolix.common.states.Visibility;
import ch.nolix.common.validator.Validator;

//class
public class InvisibleLayerGUI extends LayerGUI<InvisibleLayerGUI> {
	
	//attributes
	private int viewAreaWidth;
	private int viewAreaHeight;
	private int viewAreaCursorXPosition;
	private int viewAreaCursorYPosition;
	
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
	public int getViewAreaCursorXPosition() {
		return viewAreaCursorXPosition;
	}
	
	//method
	@Override
	public int getViewAreaCursorYPosition() {
		return viewAreaCursorYPosition;
	}
	
	//method
	@Override
	public int getViewAreaHeight() {
		return viewAreaHeight;
	}
	
	//method
	@Override
	public int getViewAreaWidth() {
		return viewAreaWidth;
	}
	
	//method
	@Override
	public boolean isRootGUI() {
		return true;
	}
	
	//method
	@Override
	public void noteMouseMove(final int viewAreaCursorXPosition, final int viewAreaCursorYPosition) {
		
		super.noteMouseMove(viewAreaCursorXPosition, viewAreaCursorYPosition);
		
		this.viewAreaCursorXPosition = viewAreaCursorXPosition;
		this.viewAreaCursorYPosition = viewAreaCursorYPosition;
	}
	
	//method
	@Override
	public void noteResize(final int viewAreaWidth, final int viewAreaHeight) {
		
		super.noteResize(viewAreaWidth, viewAreaHeight);
		
		Validator.suppose(viewAreaWidth).thatIsNamed("view area width").isNotNegative();
		Validator.suppose(viewAreaHeight).thatIsNamed("view area height").isNotNegative();
		
		this.viewAreaWidth = viewAreaWidth;
		this.viewAreaHeight = viewAreaHeight;
	}
}
