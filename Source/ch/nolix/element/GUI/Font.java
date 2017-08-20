//package declaration
package ch.nolix.element.GUI;

//Java imports
import java.awt.Canvas;
import java.awt.Graphics;

import ch.nolix.core.constants.StringManager;
//own imports
import ch.nolix.core.container.List;
import ch.nolix.core.specification.StandardSpecification;
import ch.nolix.core.validator2.Validator;
import ch.nolix.element.basic.Color;
import ch.nolix.element.basic.Element;
import ch.nolix.element.basic.PositiveInteger;

//class
/**
 * A font can write texts with a specific font-family, text size and text color.
 * A font is not mutable.
 * 
 * @author Silvan Wyss
 * @month 2017-08
 * @lines 160
 */
public class Font extends Element {

	//attribute headers
	private static final String TEXT_SIZE_HEADER = "TextSize";
	private static final String TEXT_COLOR_HEADER = "TextColor";
	
	//attributes
	private final FontFamily fontFamily;
	private final PositiveInteger textSize;
	private final Color textColor;
	private final java.awt.Font javaFont;
	private final Canvas helperCanvas = new Canvas();
	
	//constructor
	/**
	 * Creates new font with the given font family, text size and text color.
	 * 
	 * @param fontFamily
	 * @param textSize
	 * @param textColor
	 * @throws NullArgumentException if the given font family is null.
	 * @throws NonPositiveArgumentException if the given text size is not positive.
	 * @throws NullArgumentException if the given text color is null.
	 */
	public Font(
		final FontFamily fontFamily,
		final int textSize,
		final Color textColor) {
		
		//Checks if the given font family is not null.
		Validator.supposeThat(fontFamily).thatIsInstanceOf(FontFamily.class).isNotNull();
		
		//Checks if the given text color is not null.
		Validator.supposeThat(textColor).thatIsNamed(TEXT_COLOR_HEADER).isNotNull();
		
		this.fontFamily = fontFamily;
		this.textSize = new PositiveInteger(textSize);
		this.textColor = textColor;
		
		this.javaFont
		= new java.awt.Font(
			getFontFamily().getJavaFontFamily(),
			java.awt.Font.PLAIN,
			getTextSize()
		);
	}
	
	//method
	/**
	 * @return the attributes of this font.
	 */
	public List<StandardSpecification> getAttributes() {
		
		final List<StandardSpecification> attributes = new List<StandardSpecification>();
		
		attributes.addAtEnd(
			fontFamily.getSpecification(),
			textSize.getSpecificationAs(TEXT_SIZE_HEADER),
			textColor.getSpecificationAs(TEXT_COLOR_HEADER)
		);
		
		return attributes;
	}
	
	//method
	/**
	 * @param text
	 * @param maxWidth
	 * @return the first part of the given text this font can paint
	 * and that is not longer than the given max width.
	 * @throws NegativeArgumentException if the given max width is negative.
	 */
	public String getFirstTextPart(final String text, final int maxWidth) {
		
		//Checks if the given max width is not negative.
		Validator.supposeThat(maxWidth).thatIsNamed("max width").isNotNegative();
		
		String firstPart = StringManager.EMPTY_STRING;
		
		int endIndex = 0;
		while (endIndex < text.length() && getTextWidth(firstPart) < maxWidth) {
			firstPart = text.substring(0, endIndex);
			endIndex++;
		}
		
		if (getTextWidth(text) < maxWidth) {
			return text;
		}
		
		endIndex--;
		return text.substring(0, endIndex);
	}
	
	//method
	/**
	 * @return the font family of this font.
	 */
	public FontFamily getFontFamily() {
		return fontFamily;
	}
	
	//method
	/**
	 * @return the text color of this font.
	 */
	public Color getTextColor() {
		return textColor;
	}
	
	//method
	/**
	 * The text size is normative.
	 * The text height depends on the font family and can differ.
	 * 
	 * @return the text height of this font.
	 */
	public final int getTextHeight() {
		return helperCanvas.getFontMetrics(javaFont).getHeight();
	}
	
	//method
	/**
	 * @return the text size of this font.
	 */
	public int getTextSize() {
		return textSize.getValue();
	}
	
	//method
	/**
	 * @return the width of the given text when this font would be applied to it.
	 */
	public final int getTextWidth(final String text) {
		return helperCanvas.getFontMetrics(javaFont).stringWidth(text);
	}
	
	//method
	/**
	 * Lets this font paint the given text using the given graphics.
	 * 
	 * @param text
	 * @param graphics
	 */
	public void paintText(final String text, final Graphics graphics) {
		
		//Calls other method.
		paintText(text, graphics, 0, 0);
	}
	
	//method
	/**
	 * Lets this font paint the given text using the given graphics.
	 * Only the first part of the given text that is not longer than the given max width will be painted.
	 * 
	 * @param text
	 * @param graphics
	 */
	public void paintText(
		final String text,
		final int maxWidth,
		final Graphics graphics) {
		
		//Calls other method.
		paintText(text, maxWidth, graphics, 0, 0);
	}
	
	//method
	/**
	 * Lets this font paint the given text at the given position using the given graphics.
	 * 
	 * @param text
	 * @param graphics
	 * @param xPosition
	 * @param yPosition
	 */
	public void paintText(
		final String text,
		final Graphics graphics,
		final int xPosition,
		final int yPosition
	) {
		graphics.setFont(javaFont);
		graphics.setColor(getTextColor().getJavaColor());
		graphics.drawString(text, xPosition, yPosition + getTextSize());		
	}
	
	//method
	/**
	 * Lets this font paint the given text at the given position using the given graphics.
	 * Only the first part of the given text that is not longer than the given max width will be painted.
	 * 
	 * @param text
	 * @param maxWidth
	 * @param graphics
	 * @param xPosition
	 * @param yPosition
	 * @throws NegativeArgumentException if the given max width is negative.
	 */
	public void paintText(
		final String text,
		final int maxWidth,
		final Graphics graphics,
		final int xPosition,
		final int yPosition
	) {
		final String firstPart = getFirstTextPart(text, maxWidth);
		
		graphics.setFont(javaFont);
		graphics.setColor(getTextColor().getJavaColor());
		graphics.drawString(firstPart, xPosition, yPosition + getTextSize());		
	}
}
