//package declaration
package ch.nolix.element.GUI;

//own imports
import ch.nolix.core.container.List;
import ch.nolix.core.specification.StandardSpecification;
import ch.nolix.core.validator2.Validator;
import ch.nolix.element.basic.Color;
import ch.nolix.element.basic.PositiveInteger;

//class
/**
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 190
 */
public final class TextLineWidgetStructure
extends BorderWidgetStructure<TextLineWidgetStructure> {
	
	//attribute headers
	private static final String TEXT_SIZE_HEADER = "TextSize";
	private static final String TEXT_COLOR_HEADER = "TextColor";
	
	//default values
	private static final int DEFAULT_TEXT_SIZE = 10;
	private static final Color DEFAULT_TEXT_COLOR = new Color(Color.BLACK);
	
	//optional attributes
	private PositiveInteger textSize;
	private Color textColor;
	
	//method
	/**
	 * @return the active text color of this text line widget structure
	 */
	/*
	public Color getActiveTextColor() {
		
		//Handles the case if this text line widget structure has a text color.
		if (hasTextColor()) {
			return textColor.getCopy();
		}
		
		//Handles the case if this text line widget
		//has no text color but a normal structure.
		if (hasNormalStructure()) {
			return getRefNormalStructure().getActiveTextColor();
		}
		
		//Handles the case if this text line widget
		//has no text color and no normal structure.
		return DEFAULT_TEXT_COLOR;
	}
	*/
	
	//method
	/**
	 * @return the active text size of this text line text line widget structure.
	 */
	/*
	public int getActiveTextSize() {
		
		//Handles the case if this text line widget structure has a text size.
		if (hasTextSize()) {
			return textSize.getValue();
		}
		
		//Handles the case if this text line widget structure
		//has no text size but a normal structure.
		if (hasNormalStructure()) {
			return getRefNormalStructure().getActiveTextSize();
		}
		
		//Handles the case if this text line widget structure.
		//has no text size and no normal structure.
		return DEFAULT_TEXT_SIZE;
	}
	*/

	//method
	/**
	 * @return true if this text line widget structure has a text color.
	 */
	public boolean hasTextColor() {
		return (textColor != null);
	}
	
	//method
	/**
	 * @return true if this text line widget structure has a text size.
	 */
	public boolean hasTextSize() {
		return (textSize != null);
	}
	
	//method
	/**
	 * Removes all attributes of this text line widget structure.
	 */
	public void removeAttributes() {
		
		//Calls method of the base class.
		super.removeAttributes();
		
		removeTextSize();
		removeTextColor();
	}
	
	//method
	/**
	 * Removes the text color of this text line widget structure.
	 */
	public void removeTextColor() {
		textColor = null;
	}
	
	//method
	/**
	 * Removes the text size of this text line widget structure.
	 */
	public void removeTextSize() {
		textSize = null;
	}
	
	//method
	/**
	 * Sets the text color of this text line widget structure.
	 * 
	 * @param textColor
	 * @throws NullArgumentException if the given text color is null.
	 */
	public void setTextColor(final Color textColor) {
		
		//Validates that the given text color is not null.
		Validator.supposeThat(textColor).thatIsNamed("text color").isNotNull();
		
		//Sets the text color of this text line widget structure.
		this.textColor = textColor;
	}
	
	//method
	/**
	 * Sets the text size of this text line widget structure.
	 * 
	 * @param textSize
	 * @throws NonPositiveArgumentException if the given text size is not positive.
	 */
	public void setTextSize(final int textSize) {
		this.textSize = new PositiveInteger(textSize);
	}
	
	//method
	/**
	 * Adds or changes the given attribute to this text line text structure.
	 * 
	 * @param attribute
	 * @throws InvalidArgumentException if the given attribute is not valid.
	 */
	public final void addOrChangeAttribute(final StandardSpecification attribute) {
		
		//Enumerates the header of the given attribute.
		switch (attribute.getHeader()) {
			case TEXT_SIZE_HEADER:
				setTextSize(attribute.getOneAttributeToInteger());
				break;
			case TEXT_COLOR_HEADER:
				setTextColor(new Color(attribute.getOneAttributeToString()));
				break;
			default:
				
				//Calls method of the base class.
				super.addOrChangeAttribute(attribute);
		}
	}
	
	//method
	/**
	 * @return the attributes of this text line text line widget structure.
	 */
	public final List<StandardSpecification> getAttributes() {
		
		//Calls method of the base class.
		List<StandardSpecification> attributes = super.getAttributes();
		
		//Handles the option that this text line widget structure has a text size.
		if (hasTextSize()) {
			attributes.addAtEnd(textSize.getSpecificationAs(TEXT_SIZE_HEADER));
		}
		
		//Handles the option that this text line widget structure has a text color.
		if (hasTextColor()) {
			attributes.addAtEnd(textColor.getSpecificationAs(TEXT_COLOR_HEADER));
		}
		
		return attributes;
	}
}
