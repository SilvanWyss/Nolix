//package declaration
package ch.nolix.element.font;

//Java imports
import java.awt.Canvas;
import java.awt.Graphics;

//own imports
import ch.nolix.core.constants.CharacterCatalogue;
import ch.nolix.core.constants.StringCatalogue;
import ch.nolix.core.container.List;
import ch.nolix.core.enums.TextStyle;
import ch.nolix.core.specification.StandardSpecification;
import ch.nolix.element.color.Color;
import ch.nolix.element.core.Element;
import ch.nolix.element.core.PositiveInteger;
import ch.nolix.primitive.validator2.Validator;

//class
/**
 * A font can paint texts with a specific
 * -text font
 * -text style
 * -text size
 * -text color
 * 
 * A font is not mutable.
 * 
 * @author Silvan Wyss
 * @month 2017-08
 * @lines 350
 */
public final class Font extends Element {
	
	//default values
	public static final TextFont DEFAULT_TEXT_FONT = TextFont.Verdana;
	public static final TextStyle DEFAULT_TEXT_STYLE = TextStyle.Default;
	public static final int DEFAULT_TEXT_SIZE = 20;
	public static final Color DEFAULT_TEXT_COLOR = Color.BLACK;

	//constants
	private static final String TEXT_SIZE_HEADER = "TextSize";
	private static final String TEXT_COLOR_HEADER = "TextColor";
	
	//attributes
	private final TextFont textFont;
	private final TextStyle textStyle;
	private final PositiveInteger textSize;
	private final Color textColor;
	private final java.awt.Font javaFont;
	private final Canvas helperCanvas = new Canvas();
	
	//constructor
	/**
	 * Creates a new font.
	 */
	public Font() {
		
		//Calls other constructor.
		this(DEFAULT_TEXT_FONT, DEFAULT_TEXT_STYLE, DEFAULT_TEXT_SIZE, DEFAULT_TEXT_COLOR);
	}
	
	//constructor
	/**
	 * Creates a new font with the given text color.
	 * 
	 * @param textColor
	 * @throws NullArgumentException if the given text color is not an instance.
	 */
	public Font(final Color text_color) {
		
		//Calls other constructor.
		this(DEFAULT_TEXT_FONT, DEFAULT_TEXT_STYLE, DEFAULT_TEXT_SIZE, text_color);
	}
	
	//constructor
	/**
	 * Creates a new font with the given text size.
	 * 
	 * @param textSize
	 * @throws NonPositiveArgumentException if the given text size is not positive.
	 */
	public Font(final int textSize) {
		
		//Calls other constructor.
		this(DEFAULT_TEXT_FONT, DEFAULT_TEXT_STYLE, textSize, DEFAULT_TEXT_COLOR);
	}
	
	//constructor
	/**
	 * Creates a new font with the given text font.
	 * 
	 * @param textFont
	 * @throws NullArgumentException if the given text font is not an instance.
	 */
	public Font(final TextFont textFont) {
		
		//Calls other constructor.
		this(textFont, DEFAULT_TEXT_STYLE, DEFAULT_TEXT_SIZE, DEFAULT_TEXT_COLOR);
	}
	
	//constructor
	/**
	 * Creates a new font with the given text style.
	 * 
	 * @param textStyle
	 * @throws NullArgumentException if the given text style is not an instance.
	 */
	public Font(final TextStyle textStyle) {
		
		//Calls other constructor.
		this(DEFAULT_TEXT_FONT, textStyle, DEFAULT_TEXT_SIZE, DEFAULT_TEXT_COLOR);
	}
	
	//constructor
	/**
	 * Creates a new font with the given text font, text size and text color.
	 * 
	 * @param textFont
	 * @param textSize
	 * @param textColor
	 * @throws NullArgumentException if the given text font is not an instance.
	 * @throws NonPositiveArgumentException if the given text size is not positive.
	 * @throws NullArgumentException if the given text color is not an instance.
	 */
	public Font(
		final TextFont textFont,
		final TextStyle textStyle,
		final int textSize,
		final Color textColor) {
		
		//Checks if the given text font is an instance.
		Validator.suppose(textFont).isInstanceOf(TextFont.class);
		
		//Checks if the given text style is an instance.
		Validator.suppose(textStyle).isInstanceOf(TextStyle.class);
		
		//Checks if the given text color is an instance.
		Validator.suppose(textColor).thatIsNamed(TEXT_COLOR_HEADER).isInstance();
		
		this.textFont = textFont;
		this.textStyle = textStyle;
		this.textSize = new PositiveInteger(textSize);
		this.textColor = textColor;
		
		this.javaFont
		= new java.awt.Font(
			getTextFont().getSwingFontFamily(),
			java.awt.Font.PLAIN,
			getTextSize()
		);
	}
	
	//method
	/**
	 * @return the attributes of the current {@link Font}.
	 */
	public List<StandardSpecification> getAttributes() {
		return new List<StandardSpecification>(
			textFont.getSpecification(),
			textStyle.getSpecificationAs(TextStyle.TYPE_NAME),
			textSize.getSpecificationAs(TEXT_SIZE_HEADER),
			textColor.getSpecificationAs(TEXT_COLOR_HEADER)
		);
	}
	
	//method
	/**
	 * @param text
	 * @param maxWidth
	 * @return the first part of the given text the current {@link Font} can paint
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
	 * @return the first part of the given text the current {@link Font} can paint,
	 * that is not longer than the given max width,
	 * optionally with an ellipsis if the given text is longer than the given max width.
	 */
	public String getFirstPart(String text, final int maxWidth, final boolean attachEllipsis) {
		
		//Checks if the given max width is not negative.
		Validator.suppose(maxWidth).thatIsNamed("max width").isNotNegative();
		
		if (text == null) {
			text = StringCatalogue.NULL_NAME;
		}
		
		String firstPart = StringCatalogue.EMPTY_STRING;
				
		while (firstPart.length() < text.length() && getSwingTextWidth(firstPart) < maxWidth) {
			firstPart = text.substring(0, firstPart.length() + 1);
		}
		
		//Handles the case that an ellipsis has to be attached to the first part.
		if (attachEllipsis) {
			if (firstPart.length() < text.length()) {		
				
				while (getSwingTextWidth(firstPart + CharacterCatalogue.ELLIPSIS) > maxWidth) {
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
	 * @return the first part of the given text the current {@link Font} can paint,
	 * that is not longer than the given max width,
	 * with an ellipsis if the given text is longer than the given max width.
	 * @throws NegativeArgumentException if the given max width is negative.
	 */
	public String getFirstPartWithProbableEllipsis(final String text, final int maxWidth) {
		return getFirstPart(text, maxWidth, true);
	}
	
	//method
	/**
	 * @return the text color of the current {@link Font}.
	 */
	public Color getTextColor() {
		return textColor;
	}
	
	//method
	/**
	 * @return the text font of the current {@link Font}.
	 */
	public TextFont getTextFont() {
		return textFont;
	}
	
	//method
	/**
	 * @return the text style of the current {@link Font}.
	 */
	public TextStyle getTextStyle() {
		return textStyle;
	}
	
	//method
	/**
	 * The text size is normative.
	 * The text height depends on the text font and can differ.
	 * 
	 * @return the text height of the current {@link Font}.
	 */
	public final int getTextHeight() {
		return helperCanvas.getFontMetrics(javaFont).getHeight();
	}
	
	//method
	/**
	 * @return the text size of the current {@link Font}.
	 */
	public int getTextSize() {
		return textSize.getValue();
	}
	
	//method
	/**
	 * @return the width of the given character when the current {@link Font} would be applied to it.
	 */
	public final int getSwingTextWidth(final char character) {
		return getSwingTextWidth(String.valueOf(character));
	}
	
	//method
	/**
	 * @return the width of the given text when the current {@link Font} would be applied to it.
	 */
	public final int getSwingTextWidth(final String text) {
		return helperCanvas.getFontMetrics(javaFont).stringWidth(text);
	}
	
	//method
	/**
	 * Lets the current {@link Font} paint the given text using the given graphics.
	 * 
	 * @param text
	 * @param graphics
	 */
	public void paintSwingText(final String text, final Graphics graphics) {
		
		//Calls other method.
		paintSwingText(text, graphics, 0, 0);
	}
	
	//method
	/**
	 * Lets the current {@link Font} paint the given text at the given position using the given graphics.
	 * 
	 * @param text
	 * @param graphics
	 * @param xPosition
	 * @param yPosition
	 */
	public void paintSwingText(
		final String text,
		final Graphics graphics,
		final int xPosition,
		final int yPosition
	) {
		graphics.setFont(javaFont);
		graphics.setColor(getTextColor().createSwingColor());
		graphics.drawString(text, xPosition, yPosition + getTextSize());		
	}
	
	//method
	/**
	 * Lets the current {@link Font} paint the given text using the given graphics.
	 * Only the first part of the given text
	 * that is not longer than the given max width will be painted.
	 * 
	 * @param text
	 * @param graphics
	 */
	public void paintSwingText(
		final String text,
		final int maxWidth,
		final Graphics graphics) {
		
		//Calls other method.
		paintSwingText(text, maxWidth, graphics, 0, 0);
	}
	
	//method
	/**
	 * Lets the current {@link Font} paint the given text at the given position using the given graphics.
	 * Only the first part of the given text that is not longer than the given max width will be painted.
	 * 
	 * @param text
	 * @param maxWidth
	 * @param graphics
	 * @param xPosition
	 * @param yPosition
	 * @throws NegativeArgumentException if the given max width is negative.
	 */
	public void paintSwingText(
		final String text,
		final int maxWidth,
		final Graphics graphics,
		final int xPosition,
		final int yPosition
	) {
		final String firstPart = getFirstPart(text, maxWidth);
		
		graphics.setFont(javaFont);
		graphics.setColor(getTextColor().createSwingColor());
		graphics.drawString(firstPart, xPosition, yPosition + getTextSize());		
	}
}
