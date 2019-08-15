//package declaration
package ch.nolix.element.GUI;

//own import
import ch.nolix.element.baseGUI_API.IEventTaker;

//class
public final class CanvasFrame extends CanvasGUI<CanvasFrame> {
	
	//constructor
	public CanvasFrame(final IEventTaker eventTaker) {
		
		super(eventTaker, true);
		
		initialize();
	}
	
	//constructor
	public CanvasFrame(final String title, final IEventTaker eventTaker) {
		
		this(eventTaker);
		
		setTitle(title);
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
