//package declaration
package ch.nolix.element.dialog;

//own imports
import ch.nolix.common.container.List;
import ch.nolix.common.specification.Specification;
import ch.nolix.element.data.Height;
import ch.nolix.element.data.TextColor;
import ch.nolix.element.data.TextSize;
import ch.nolix.element.data.Width;

//class
/**
 * @author Silvan Wyss
 * @month 2017-03
 * @lines 270
 */
public final class ConsoleStructure extends BorderableRectangleStructure<ConsoleStructure> {
		
	//optional attribute
	private Width width;
	private Height height;
	private TextSize textSize;
	private TextColor textColor;
	
	//method
	/**
	 * @return the active height of this console structure.
	 */
	public int getActiveHeight() {
		
		//Handles the case if this console structure has a height.
		if (hasHeight()) {
			return height.getValue();
		}
		
		//Handles the case if this console has no height but a normal structure.
		if (hasNormalStructure()) {
			return getRefNormalStructure().getActiveHeight();
		}
		
		//Handles the case if this console has no height and no normal structure.
		return Console.DEFAULT_HEIGHT;
	}
	
	//method
	/**
	 * @return active text color of this console structure.
	 */
	public TextColor getActiveTextColor() {
		
		//Handles the case if this console structure has a text color.
		if (hasTextColor()) {
			return textColor;
		}
		
		//Handles the case if this console structure has no text color but a normal structure.
		if (hasNormalStructure()) {
			return getRefNormalStructure().getActiveTextColor();
		}
		
		//Handles the case if this console structure has no text color and no normal structure.
		return new TextColor(Console.DEFAULT_TEXT_COLOR);
	}
	
	//method
	/**
	 * @return the active text size of this console structure.
	 */
	public int getActiveTextSize() {
		
		//Handles the case if this console structure has a text size.
		if (hasTextSize()) {
			return textSize.getValue();
		}
		
		//Handles the case if this console structure has no text size but a normal structure.
		if (hasNormalStructure()) {
			return getRefNormalStructure().getActiveTextSize();
		}
		
		//Handles the case if this console structure has no text size and no normal structure.
		return Console.DEFAULT_TEXT_SIZE;
	}
	
	//method
	/**
	 * @return the active width of this console structure.
	 */
	public int getActiveWidth() {
		
		//Handles the case if this console structure has a width.
		if (hasWidth()) {
			return width.getValue();
		}
		
		//Handles the case if this console has no width but a normal structure.
		if (hasNormalStructure()) {
			return getRefNormalStructure().getActiveWidth();
		}
		
		//Handles the case if this console has no width and no normal structure.
		return Console.DEFAULT_WIDTH;
	}
	
	//method
	/**
	 * @return the attributes of this console structure.
	 */
	public List<Specification> getAttributes() {
		
		//Calls method of the base class.
		final List<Specification> attributes = super.getAttributes();
		
		if (hasTextSize()) {
			attributes.addAtEnd(textSize.getSpecification());
		}
		
		if (hasTextColor()) {
			attributes.addAtEnd(textColor.getSpecification());
		}
		
		return attributes;
	}
	
	//method
	/**
	 * @return true if this console structure has a height.
	 */
	public boolean hasHeight() {
		return (height != null);
	}
	
	//method
	/**
	 * @return true if this console structure has a text color.
	 */
	public boolean hasTextColor() {
		return (textColor != null);
	}
	
	//method
	/**
	 * @return true if this console structure has a text size.
	 */
	public boolean hasTextSize() {
		return (textSize != null);
	}
	
	//method
	/**
	 * @return true if this console structure has a width.
	 */
	public boolean hasWidth() {
		return (width != null);
	}
	
	//method
	/**
	 * Removes the height of this console structure.
	 * 
	 * @return this console structure.
	 */
	public ConsoleStructure removeHeight() {
		
		height = null;
		
		return this;
	}
	
	//method
	/**
	 * Removes the text color of this console structure.
	 * 
	 * @return this console structure.
	 */
	public ConsoleStructure removeTextColor() {
		
		textColor = null;
		
		return this;
	}
	
	//method
	/**
	 * Removes the text size of this console structure.
	 * 
	 * @return this console structure.
	 */
	public ConsoleStructure removeTextSize() {
		
		textSize = null;
		
		return this;
	}
	
	//method
	/**
	 * Removes the width of this console structure.
	 * 
	 * @return this console structure.
	 */
	public ConsoleStructure removeWidth() {
		
		width = null;
		
		return this;
	}
	
	public void setAttribute(final Specification attribute) {
		
		//Enumerates the header of the given attribute.
		switch (attribute.getHeader()) {
			case TextSize.SIMPLE_CLASS_NAME:
				setTextSize(attribute.getOneAttributeToInteger());
				break;
			case TextColor.SIMPLE_CLASS_NAME:
				
				break;
			default:
				
				
				super.setAttribute(attribute);
		}
	}
	
	//method
	/**
	 * Sets the height of this console structure.
	 * 
	 * @param height
	 * @return this console structure.
	 * @throws NonPositiveArgumentException if the given height is not positive.
	 */
	public ConsoleStructure setHeight(final int height) {
		
		this.height = new Height(height);
		
		return this;
	}
	
	//method
	/**
	 * Sets the text size of this console structure.
	 * 
	 * @param textSize
	 * @return this console structure.
	 * @throws NonPositiveArgumentException if the given text size is not positive.
	 */
	public ConsoleStructure setTextSize(final int textSize) {
		
		this.textSize = new TextSize(textSize);
		
		return this;
	}
	
	//method
	/**
	 * Sets the width of this console structure.
	 * 
	 * @param width
	 * @return this console structure.
	 * @throws NonPositiveArgumentException if the given width is not positive.
	 */
	public ConsoleStructure setWidth(final int width) {
		
		this.width = new Width(width);
		
		return this;
	}
}
