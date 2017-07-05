/*
 * file:	TextLineRectangleStrucutre.java
 * author:	Silvan Wyss
 * month:	2015-12
 * lines:	170
 */

//package declaration
package ch.nolix.element.GUI;

//own imports
import ch.nolix.core.container.List;
import ch.nolix.core.specification.StandardSpecification;
import ch.nolix.element.basic.Color;
import ch.nolix.element.basic.PositiveInteger;
import ch.nolix.element.data.Size;

//class
public final class TextLineWidgetStructure extends BorderWidgetStructure<TextLineWidgetStructure> {
	
	//attribute headers
	private static final String TEXT_SIZE = "TextSize";
	private static final String TEXT_COLOR = "TextColor";

	//optional attributes
	private PositiveInteger textSize;
	private Color textColor;
	
	//method
	/**
	 * @return the current text color of this rectangle rectangle structure
	 */
	public final Color getCurrentTextColor() {
		
		if (hasTextColor()) {
			return textColor.getCopy();
		}
		
		if (hasNormalStructure()) {
			return getRefNormalStructure().getCurrentTextColor();
		}
		
		return new Color();
	}
	
	//method
	/**
	 * @return the current text size of this text line rectangle structure
	 */
	public final int getCurrentTextSize() {
		
		if (hasTextSize()) {
			return textSize.getValue();
		}
		
		if (hasNormalStructure()) {
			return getRefNormalStructure().getCurrentTextSize();
		}
		
		return new Size().getValue();
	}

	//method
	/**
	 * @return true if this text line rectangle structure has a text color
	 */
	public final boolean hasTextColor() {
		return (textColor != null);
	}
	
	//method
	/**
	 * @return true if this text line rectangle structure has a text size
	 */
	public final boolean hasTextSize() {
		return (textSize != null);
	}
	
	//method
	/**
	 * Removes the text color of this text line rectangle structure.
	 */
	public final void removeTextColor() {
		textColor = null;
	}
	
	//method
	/**
	 * Removes the text size of this text line rectangle structure.
	 */
	public final void removeTextSize() {
		
		if (!hasNormalStructure()) {
			throw new RuntimeException("Text line rectangle cannot not remove text size.");
		}
		
		textSize = null;
	}
	
	//method
	/**
	 * Sets the text color of this text line rectangle structure.
	 * 
	 * @param textColor
	 * @throws Exception if the given text color is no color name or no true color value
	 */
	public final void setTextColor(int textColor) {
		this.textColor = new Color(textColor);
	}
	
	//method
	/**
	 * Sets the text size of this text line rectangle structure.
	 * 
	 * @param textSize
	 * @throws Exception if the given text size is not positive
	 */
	public void setTextSize(int textSize) {
		this.textSize = new PositiveInteger(textSize);
	}
	
	//method
	/**
	 * @return the attributes of this text line rectangle structure
	 */
	protected final List<StandardSpecification> getAttributes() {
		
		//Calls method of the base class.
		List<StandardSpecification> attributes = super.getAttributes();
		
		if (hasTextSize()) {
			attributes.addAtEnd(textSize.getSpecificationAs(TEXT_SIZE));
		}
		
		if (hasTextColor()) {
			attributes.addAtEnd(textColor.getSpecificationAs(TEXT_COLOR));
		}
		
		return attributes;
	}
	
	//method
	/**
	 * Sets the given attribute to this text line rectangle structure.
	 * 
	 * @param attribute
	 * @throws Exception if the given attribute is not valid
	 */
	protected final void addOrChangeAttribute(StandardSpecification attribute) {
		switch (attribute.getHeader()) {
			case TEXT_SIZE:
				setTextSize(attribute.getOneAttributeToInteger());
				break;
			case TEXT_COLOR:
				setTextColor(new Color(attribute.getOneAttributeToString()).getValue());
				break;
			default:
				
				//Calls method of the base class.
				super.addOrChangeAttribute(attribute);
		}
	}
}
