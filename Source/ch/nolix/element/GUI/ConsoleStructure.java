//package declaration
package ch.nolix.element.GUI;

//own imports
import ch.nolix.core.container.List;
import ch.nolix.core.specification.Specification;
import ch.nolix.core.specification.StandardSpecification;
import ch.nolix.element.color.Color;
import ch.nolix.element.core.PositiveInteger;
import ch.nolix.element.intData.Height;
import ch.nolix.element.intData.Width;

//class
/**
 * @author Silvan Wyss
 * @month 2017-03
 * @lines 330
 */
public final class ConsoleStructure extends BorderWidgetStructure<ConsoleStructure> {
		
	//default values
	public static final int DEFAULT_WIDTH = 400;
	public static final int DEFAULT_HEIGHT = 400;
	public static final int DEFAULT_TEXT_SIZE = ValueCatalogue.MEDIUM_TEXT_SIZE;
	public static final Color DEFAULT_TEXT_COLOR = Color.BLACK;
	
	//attribute headers
	private static final String WIDTH_HEADER = "Width";
	private static final String HEIGHT_HEADER = "Height";
	private static final String TEXT_SIZE_HEADER = "TextSize";
	private static final String TEXT_COLOR_HEADER = "TextColor";
	
	//optional attribute
	private PositiveInteger width;
	private PositiveInteger height;
	private PositiveInteger textSize;
	private Color textColor;
	
	//method
	/**
	 * @return the active height of this console structure.
	 */
	public int getActiveHeight() {
		
		//Handles the case that this console structure has a height.
		if (hasHeight()) {
			return height.getValue();
		}
		
		//Handles the case that this console has no height but a normal structure.
		if (hasNormalStructure()) {
			return getRefNormalStructure().getActiveHeight();
		}
		
		//Handles the case that this console has no height and no normal structure.
		return DEFAULT_HEIGHT;
	}
	
	//method
	/**
	 * @return active text color of this console structure.
	 */
	/*
	public Color getActiveTextColor() {
		
		//Handles the case that this console structure has a text color.
		if (hasTextColor()) {
			return textColor;
		}
		
		//Handles the case that this console structure has no text color but a normal structure.
		if (hasNormalStructure()) {
			return getRefNormalStructure().getActiveTextColor();
		}
		
		//Handles the case that this console structure has no text color and no normal structure.
		return DEFAULT_TEXT_COLOR;
	}
	*/
	
	//method
	/**
	 * @return the active text size of this console structure.
	 */
	/*
	public int getActiveTextSize() {
		
		//Handles the case that this console structure has a text size.
		if (hasTextSize()) {
			return textSize.getValue();
		}
		
		//Handles the case that this console structure has no text size but a normal structure.
		if (hasNormalStructure()) {
			return getRefNormalStructure().getActiveTextSize();
		}
		
		//Handles the case that this console structure has no text size and no normal structure.
		return DEFAULT_TEXT_SIZE;
	}
	*/
	
	//method
	/**
	 * @return the active width of this console structure.
	 */
	public int getActiveWidth() {
		
		//Handles the case that this console structure has a width.
		if (hasWidth()) {
			return width.getValue();
		}
		
		//Handles the case that this console has no width but a normal structure.
		if (hasNormalStructure()) {
			return getRefNormalStructure().getActiveWidth();
		}
		
		//Handles the case that this console has no width and no normal structure.
		return DEFAULT_WIDTH;
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
	 * Removes the width of this console structure.
	 * 
	 * @return this console structure.
	 */
	public ConsoleStructure removeWidth() {
		
		width = null;
		
		return this;
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
	
	public void addOrChangeAttribute(final Specification attribute) {
		
		//Enumerates the header of the given attribute.
		switch (attribute.getHeader()) {
			case WIDTH_HEADER:
				setWidth(attribute.getOneAttributeAsInt());
				break;
			case HEIGHT_HEADER:
				setHeight(attribute.getOneAttributeAsInt());
				break;
				/*
			case TEXT_SIZE_HEADER:
				setTextSize(attribute.getOneAttributeToInteger());
				break;
				
			case TEXT_COLOR_HEADER:
				setTextColor(new Color(attribute.getOneAttributeToString()));
				break;
				*/
			default:
				
				//Calls method of the base class.
				super.addOrChangeAttribute(attribute);
		}
	}
	
	//method
	/**
	 * @return the attributes of this console structure.
	 */
	public List<StandardSpecification> getAttributes() {
		
		//Calls method of the base class.
		final List<StandardSpecification> attributes = super.getAttributes();
		
		//Handles the case that this console structure has a width.
		if (hasWidth()) {
			attributes.addAtEnd(width.getSpecificationAs(WIDTH_HEADER));
		}
		
		//Handles the case that this console structure has a height.
		if (hasHeight()) {
			attributes.addAtEnd(height.getSpecificationAs(HEIGHT_HEADER));
		}
		
		//Handles the case that this console structure has a text size.
		if (hasTextSize()) {
			attributes.addAtEnd(textSize.getSpecificationAs(TEXT_SIZE_HEADER));
		}
		
		//Handles the case that this console structure has a text color.
		if (hasTextColor()) {
			attributes.addAtEnd(textColor.getSpecificationAs(TEXT_COLOR_HEADER));
		}
		
		return attributes;
	}
	
	//method
	/**
	 * Removes all attributes of this console structure.
	 */
	public void clearProperties() {
		
		//Calls method of the base class.
		super.removeValues();
		
		removeWidth();
		removeHeight();
		removeTextSize();
		removeTextColor();
	}
}
