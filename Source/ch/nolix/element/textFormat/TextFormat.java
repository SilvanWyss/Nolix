//package declaration
package ch.nolix.element.textFormat;

//Java imports
import java.awt.Canvas;
import java.awt.Graphics;

//own imports
import ch.nolix.core.constants.CharacterCatalogue;
import ch.nolix.core.constants.StringCatalogue;
import ch.nolix.core.container.List;
import ch.nolix.core.documentNode.DocumentNode;
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
 * @lines 330
 */
public final class TextFormat extends Element {
	
	//default values
	public static final Font DEFAULT_TEXT_FONT = Font.Verdana;
	public static final int DEFAULT_TEXT_SIZE = 20;
	public static final Color DEFAULT_TEXT_COLOR = Color.BLACK;

	//constants
	private static final String TEXT_SIZE_HEADER = "TextSize";
	private static final String TEXT_COLOR_HEADER = "TextColor";
	
	//attributes
	private final Font font;
	private final PositiveInteger textSize;
	private final Color textColor;
	private final java.awt.Font javaFont;
	private final Canvas helperCanvas = new Canvas();
	
	//constructor
	/**
	 * Creates a new font.
	 */
	public TextFormat() {
		
		//Calls other constructor.
		this(DEFAULT_TEXT_FONT, DEFAULT_TEXT_SIZE, DEFAULT_TEXT_COLOR);
	}
	
	//constructor
	/**
	 * Creates a new font with the given text color.
	 * 
	 * @param textColor
	 * @throws NullArgumentException if the given text color is not an instance.
	 */
	public TextFormat(final Color text_color) {
		
		//Calls other constructor.
		this(DEFAULT_TEXT_FONT, DEFAULT_TEXT_SIZE, text_color);
	}
	
	//constructor
	/**
	 * Creates a new font with the given text size.
	 * 
	 * @param textSize
	 * @throws NonPositiveArgumentException if the given text size is not positive.
	 */
	public TextFormat(final int textSize) {
		
		//Calls other constructor.
		this(DEFAULT_TEXT_FONT, textSize, DEFAULT_TEXT_COLOR);
	}
	
	//constructor
	/**
	 * Creates a new font with the given text font.
	 * 
	 * @param font
	 * @throws NullArgumentException if the given text font is not an instance.
	 */
	public TextFormat(final Font font) {
		
		//Calls other constructor.
		this(font, DEFAULT_TEXT_SIZE, DEFAULT_TEXT_COLOR);
	}
	
	//constructor
	/**
	 * Creates a new font with the given text font, text size and text color.
	 * 
	 * @param font
	 * @param textSize
	 * @param textColor
	 * @throws NullArgumentException if the given text font is not an instance.
	 * @throws NonPositiveArgumentException if the given text size is not positive.
	 * @throws NullArgumentException if the given text color is not an instance.
	 */
	public TextFormat(
		final Font font,
		final int textSize,
		final Color textColor) {
		
		//Checks if the given text font is an instance.
		Validator.suppose(font).isInstanceOf(Font.class);
		
		//Checks if the given text color is an instance.
		Validator.suppose(textColor).thatIsNamed(TEXT_COLOR_HEADER).isInstance();
		
		this.font = font;
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
	 * @return the attributes of the current {@link TextFormat}.
	 */
	public List<DocumentNode> getAttributes() {
		return new List<DocumentNode>(
			font.getSpecification(),
			textSize.getSpecificationAs(TEXT_SIZE_HEADER),
			textColor.getSpecificationAs(TEXT_COLOR_HEADER)
		);
	}
	
	//method
	/**
	 * @param text
	 * @param maxWidth
	 * @return the first part of the given text the current {@link TextFormat} can paint
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
	 * @return the first part of the given text the current {@link TextFormat} can paint,
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
	 * @return the first part of the given text the current {@link TextFormat} can paint,
	 * that is not longer than the given max width,
	 * with an ellipsis if the given text is longer than the given max width.
	 * @throws NegativeArgumentException if the given max width is negative.
	 */
	public String getFirstPartWithProbableEllipsis(final String text, final int maxWidth) {
		return getFirstPart(text, maxWidth, true);
	}
	
	//method
	/**
	 * @return the text color of the current {@link TextFormat}.
	 */
	public Color getTextColor() {
		return textColor;
	}
	
	//method
	/**
	 * @return the text font of the current {@link TextFormat}.
	 */
	public Font getTextFont() {
		return font;
	}
	
	//method
	/**
	 * The text size is normative.
	 * The text height depends on the text font and can differ.
	 * 
	 * @return the text height of the current {@link TextFormat}.
	 */
	public final int getTextHeight() {
		return helperCanvas.getFontMetrics(javaFont).getHeight();
	}
	
	//method
	/**
	 * @return the text size of the current {@link TextFormat}.
	 */
	public int getTextSize() {
		return textSize.getValue();
	}
	
	//method
	/**
	 * @return the width of the given character when the current {@link TextFormat} would be applied to it.
	 */
	public final int getSwingTextWidth(final char character) {
		return getSwingTextWidth(String.valueOf(character));
	}
	
	//method
	/**
	 * @return the width of the given text when the current {@link TextFormat} would be applied to it.
	 */
	public final int getSwingTextWidth(final String text) {
		return helperCanvas.getFontMetrics(javaFont).stringWidth(text);
	}
	
	//method
	/**
	 * Lets the current {@link TextFormat} paint the given text using the given graphics.
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
	 * Lets the current {@link TextFormat} paint the given text at the given position using the given graphics.
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
	 * Lets the current {@link TextFormat} paint the given text using the given graphics.
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
	 * Lets the current {@link TextFormat} paint the given text at the given position using the given graphics.
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
