//package declaration
package ch.nolix.system.GUIClientoid;

//own imports
import ch.nolix.core.constants.VariableNameCatalogue;
import ch.nolix.core.validator2.Validator;
import ch.nolix.element.GUI.GUI;

//package-visible abstract class
abstract class BackGUIClientoidGUI extends GUI<BackGUIClientoidGUI> {
	
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
}
