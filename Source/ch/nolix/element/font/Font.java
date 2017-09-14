//package declaration
package ch.nolix.element.font;

//Java imports
import java.awt.Canvas;
import java.awt.Graphics;

//own imports
import ch.nolix.core.constants.CharacterCatalogue;
import ch.nolix.core.constants.StringCatalogue;
import ch.nolix.core.container.List;
import ch.nolix.core.specification.StandardSpecification;
import ch.nolix.core.validator2.Validator;
import ch.nolix.element.basic.Color;
import ch.nolix.element.basic.Element;
import ch.nolix.element.basic.PositiveInteger;

//class
/**
 * A font can paint texts with a specific text font, text size and text color.
 * A font is not mutable.
 * 
 * @author Silvan Wyss
 * @month 2017-08
 * @lines 360
 */
public final class Font extends Element {
	
	//default values
	public static final TextFont DEFAULT_TEXT_FONT = TextFont.Verdana;
	public static final int DEFAULT_TEXT_SIZE = 20;
	public static final Color DEFAULT_TEXT_COLOR = Color.BLACK;

	//attribute headers
	private static final String TEXT_SIZE_HEADER = "TextSize";
	private static final String TEXT_COLOR_HEADER = "TextColor";
	
	//attributes
	private final TextFont textFont;
	private final PositiveInteger textSize;
	private final Color textColor;
	private final java.awt.Font javaFont;
	private final Canvas helperCanvas = new Canvas();
	
	//constructor
	/**
	 * Creates new font.
	 */
	public Font() {
		
		//Calls other constructor.
		this(DEFAULT_TEXT_FONT, DEFAULT_TEXT_SIZE, DEFAULT_TEXT_COLOR);
	}
	
	//constructor
	/**
	 * Creates new font with the given text color.
	 * 
	 * @param textColor
	 * @throws NullArgumentException if the given text color is null.
	 */
	public Font(final Color text_color) {
		
		//Calls other constructor.
		this(DEFAULT_TEXT_FONT, DEFAULT_TEXT_SIZE, text_color);
	}
	
	//constructor
	/**
	 * Creates new font with the given text font.
	 * 
	 * @param textFont
	 * @throws NullArgumentException if the given text font is null.
	 */
	public Font(final TextFont textFont) {
		
		//Calls other constructor.
		this(textFont, DEFAULT_TEXT_SIZE, DEFAULT_TEXT_COLOR);
	}
	
	//constructor
	/**
	 * Creates new font with the given text font and text size.
	 * 
	 * @param textFont
	 * @param textSize
	 * @throws NullArgumentException if the given text font is null.
	 * @throws NonPositiveArgumentException if the given text size is not positive.
	 */
	public Font(final TextFont textFont, final int textSize) {
		
		//Calls other constructor.
		this(textFont, textSize, DEFAULT_TEXT_COLOR);
	}
	
	//constructor
	/**
	 * Creates new font with the given text font, text size and text color.
	 * 
	 * @param textFont
	 * @param textSize
	 * @param textColor
	 * @throws NullArgumentException if the given text font is null.
	 * @throws NonPositiveArgumentException if the given text size is not positive.
	 * @throws NullArgumentException if the given text color is null.
	 */
	public Font(
		final TextFont textFont,
		final int textSize,
		final Color textColor) {
		
		//Checks if the given text font is not null.
		Validator.supposeThat(textFont).thatIsInstanceOf(TextFont.class).isNotNull();
		
		//Checks if the given text color is not null.
		Validator.supposeThat(textColor).thatIsNamed(TEXT_COLOR_HEADER).isNotNull();
		
		this.textFont = textFont;
		this.textSize = new PositiveInteger(textSize);
		this.textColor = textColor;
		
		this.javaFont
		= new java.awt.Font(
			getTextFont().getJavaFontFamily(),
			java.awt.Font.PLAIN,
			getTextSize()
		);
	}
	
	//constructor
	/**
	 * Creates new font with the given text size.
	 * 
	 * @param textSize
	 * @throws NonPositiveArgumentException if the given text size is not positive.
	 */
	public Font(final int textSize) {
		
		//Calls other constructor.
		this(DEFAULT_TEXT_FONT, textSize, DEFAULT_TEXT_COLOR);
	}
	
	//constructor
	/**
	 * Creates new font with the given text size and text color.
	 * 
	 * @param textSize
	 * @param textColor
	 * @throws NonPositiveArgumentException if the given text size is not positive.
	 * @throws NullArgumentException if the given text color is null.
	 */
	public Font(
		final int textSize,
		final Color textColor) {
		
		//Calls other constructor
		this(DEFAULT_TEXT_FONT, textSize, textColor);
	}
	

	
	//method
	/**
	 * @return the attributes of this font.
	 */
	public List<StandardSpecification> getAttributes() {
		
		final List<StandardSpecification> attributes = new List<StandardSpecification>();
		
		attributes.addAtEnd(
			textFont.getSpecification(),
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
	 * that is not longer than the given max width.
	 * @throws NegativeArgumentException if the given max width is negative.
	 */
	public String getFirstPart(final String text, final int maxWidth) {		
		return getFirstPart(text, maxWidth, true);
	}
	
	//method
	/**
	 * @param text
	 * @param maxWidth
	 * @param ellipsis
	 * @return the first part of the given text this font can paint,
	 * that is not longer than the given max width,
	 * optionally with an ellipsis if the given text is longer than the given max width.
	 */
	public String getFirstPart(String text, final int maxWidth, final boolean attachEllipsis) {
		
		//Checks if the given max width is not negative.
		Validator.supposeThat(maxWidth).thatIsNamed("max width").isNotNegative();
		
		if (text == null) {
			text = StringCatalogue.NULL_NAME;
		}
		
		String firstPart = StringCatalogue.EMPTY_STRING;
				
		while (firstPart.length() < text.length() && getTextWidth(firstPart) < maxWidth) {
			firstPart = text.substring(0, firstPart.length() + 1);
		}
		
		//Handles the option that an ellipsis has to be attached to the first part.
		if (attachEllipsis) {
			if (firstPart.length() < text.length()) {		
				
				while (getTextWidth(firstPart + CharacterCatalogue.ELLIPSIS) > maxWidth) {
					firstPart = firstPart.substring(0, firstPart.length() - 1);
				}
			
				firstPart += CharacterCatalogue.ELLIPSIS;
			}
		}
		
		return firstPart;
	}
	
	//method
	/**
	 * @param text
	 * @param maxWidth
	 * @return the first part of the given text this font can paint,
	 * that is not longer than the given max width,
	 * with an ellipsis if the given text is longer than the given max width.
	 * @throws NegativeArgumentException if the given max width is negative.
	 */
	public String getFirstPartWithProbableEllipsis(final String text, final int maxWidth) {
		return getFirstPart(text, maxWidth, true);
	}
	
	//method
	/**
	 * @return the text font of this font.
	 */
	public TextFont getTextFont() {
		return textFont;
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
	 * The text height depends on the text font and can differ.
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
	 * Lets this font paint the given text using the given graphics.
	 * Only the first part of the given text
	 * that is not longer than the given max width will be painted.
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
		final String firstPart = getFirstPart(text, maxWidth);
		
		graphics.setFont(javaFont);
		graphics.setColor(getTextColor().getJavaColor());
		graphics.drawString(firstPart, xPosition, yPosition + getTextSize());		
	}
}
