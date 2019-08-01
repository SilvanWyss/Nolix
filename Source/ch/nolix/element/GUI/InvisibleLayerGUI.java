//package declaration
package ch.nolix.element.GUI;

//own imports
import ch.nolix.core.validator.Validator;

//class
public class InvisibleLayerGUI extends LayerGUI<InvisibleLayerGUI> {

	//attributes
	private int viewAreaWidth;
	private int viewAreaHeight;
	private int viewAreaCursorXPosition;
	private int viewAreaCursorYPosition;
	
	//constructor
	public InvisibleLayerGUI() {
		
		super(false);
		
		reset();
	}
	
	//method
	@Override
	public int getHeight() {
		return viewAreaHeight;
	}
	
	//method
	@Override
	public int getWidth() {
		return viewAreaWidth;
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
	public void noteResize(int viewAreaWidth, int viewAreaHeight) {
		
		super.noteResize(viewAreaWidth, viewAreaHeight);
		
		this.viewAreaWidth = viewAreaWidth;
		
		Validator.suppose(viewAreaHeight).thatIsNamed("view arew height").isNotNegative();
		
		this.viewAreaHeight = viewAreaHeight;
	}
	
	//method
	public void setViewAreaCursorXPosition(final int viewAreaCursorXPosition) {
		this.viewAreaCursorXPosition = viewAreaCursorXPosition;
	}
	
	//method
	public void setViewAreaCursorYPosition(final int viewAreaCursorYPosition) {
		this.viewAreaCursorYPosition = viewAreaCursorYPosition;
	}
	
	//method
	public void setViewAreaHeight(final int viewAreaHeight) {
		
		Validator.suppose(viewAreaHeight).thatIsNamed("view arew height").isNotNegative();
		
		this.viewAreaHeight = viewAreaHeight;
	}

	//method
	public void setViewAreaWidth(final int viewAreaWidth) {
		
		Validator.suppose(viewAreaWidth).thatIsNamed("view area width").isNotNegative();
		
		this.viewAreaWidth = viewAreaWidth;
	}
}
