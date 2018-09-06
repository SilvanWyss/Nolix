//package declaration
package ch.nolix.system.GUIClient;

//own imports
import ch.nolix.core.constants.VariableNameCatalogue;
import ch.nolix.core.functionInterfaces.IElementGetter;
import ch.nolix.element.GUI.GUI;
import ch.nolix.primitive.validator2.Validator;

//package-visible class
final class BrowserGUI extends GUI<BrowserGUI> {
	
	//attribute
	private final IElementGetter<BrowserGUIPainter> painterCreator;
	
	public BrowserGUI() {
		
		reset();
		approveProperties();
		
		painterCreator = null;
	}
	
	//constructor
	public BrowserGUI(IElementGetter<BrowserGUIPainter> painterCreator) {
		
		reset();
		approveProperties();
		
		//Checks if the given painter creator is an instance.
		Validator
		.suppose(painterCreator)
		.thatIsNamed("painter creator")
		.isInstance();
		
		//Sets the painter creator of the current back browser GUI.
		this.painterCreator = painterCreator;
	}
	
	//attributes
	private int width;
	private int height;
	
	//attributes
	private int contentWidth;
	private int contentHeight;
	
	//attributes
	private int cursorXPosition;
	private int cursorYPosition;
	
	//method
	public int getCursorXPosition() {
		return cursorXPosition;
	}

	//method
	public int getCursorYPosition() {
		return cursorYPosition;
	}

	//method
	public int getContentHeight() {
		return contentHeight;
	}

	//method
	public int getContentWidth() {
		return contentWidth;
	}
	
	//method
	public int getHeight() {
		return height;
	}

	//method
	public int getWidth() {
		return width;
	}
	
	//method
	public void setContentHeight(final int contentHeight) {
		
		//Checks if the given content height is not negative.
		Validator
		.suppose(contentHeight)
		.thatIsNamed("content height")
		.isNotNegative();
		
		//Sets the content height of the current back web GUI.
		this.contentHeight = contentHeight;
	}
	
	//method
	public void setContentWidth(final int contentWidth) {
		
		//Checks if the given content width is not negative.
		Validator
		.suppose(contentWidth)
		.thatIsNamed("content width")
		.isNotNegative();
		
		//Sets the content width of the current back web GUI.
		this.contentWidth = contentWidth;
	}
	
	//method
	public void setCursorXPosition(final int cursorXPosition) {
		this.cursorXPosition = cursorYPosition;
	}
	
	//method
	public void setCursorYPosition(final int cursorYPosition) {
		this.cursorYPosition = cursorYPosition;
	}
	
	//method
	public void setHeight(final int height) {
		
		//Checks if the given height is not negative.
		Validator
		.suppose(height)
		.thatIsNamed(VariableNameCatalogue.HEIGHT)
		.isNotNegative();
		
		//Sets the height of the current back web GUI.
		this.height = height;
	}
	
	//method
	public void setWidth(final int width) {
		
		//Checks if the given width is not negative.
		Validator
		.suppose(width)
		.thatIsNamed(VariableNameCatalogue.WIDTH)
		.isNotNegative();
		
		//Sets the width of the current back web GUI.
		this.width = width;
	}
	
	//method
	protected void paint() {
		
		if (painterCreator == null) {
			return;
		}
		
		//Creates a painter.
		final var painter = painterCreator.getOutput();
		
		//Paints the background color of the current back web GUI.
		painter.paintFilledRectangle(getContentWidth(), getContentHeight());
		
		//Handles the case that the current back web GUI has a root widget.
		if (hasRootWidget()) {
			getRefRootWidget().paintUsingPositionOnParent(painter);
		}
		
		//Flushes the painter.
		painter.flush();
	}
}
