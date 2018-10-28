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
import ch.nolix.core.validator2.Validator;
import ch.nolix.element.color.Color;
import ch.nolix.element.core.Boolean;
import ch.nolix.element.core.Element;
import ch.nolix.element.core.PositiveInteger;

//class
/**
 * A {@link TextFormat} can paint texts with a specific
 * -text font
 * -bold property
 * -italic property
 * -text size
 * -text color
 * 
 * A {@link TextFormat} is not mutable.
 * 
 * @author Silvan Wyss
 * @month 2017-08
 * @lines 430
 */
public final class TextFormat extends Element {
	
	//default values
	public static final Font DEFAULT_TEXT_FONT = Font.Verdana;
	public static final int DEFAULT_TEXT_SIZE = 20;
	public static final Color DEFAULT_TEXT_COLOR = Color.BLACK;
	
	//constants
	private static final String TEXT_FONT_HEADER = "TextFont";
	private static final String BOLD_FLAG_HEADER = "Bold";
	private static final String ITALIC_FLAG_HEADER = "Italic";
	private static final String TEXT_SIZE_HEADER = "TextSize";
	private static final String TEXT_COLOR_HEADER = "TextColor";
	
	//attributes
	private final Font textFont;
	private final boolean bold;
	private final boolean italic;
	private final PositiveInteger textSize;
	private final Color textColor;
	private final java.awt.Font swingFont;
	private final Canvas canvas = new Canvas();
	
	//constructor
	/**
	 * Creates a new {@link TextFormat}.
	 */
	public TextFormat() {
		
		//Calls other constructor.
		this(DEFAULT_TEXT_FONT, false, false, DEFAULT_TEXT_SIZE, DEFAULT_TEXT_COLOR);
	}
	
	//constructor
	/**
	 * Creates a new {@link TextFormat} with the given text color.
	 * 
	 * @param textColor
	 * @throws NullArgumentException if the given text color is null.
	 */
	public TextFormat(final Color textColor) {
		
		//Calls other constructor.
		this(DEFAULT_TEXT_FONT, false, false, DEFAULT_TEXT_SIZE, textColor);
	}
	
	//constructor
	/**
	 * Creates a new {@link TextFormat} with the given text font.
	 * 
	 * @param textFont
	 * @throws NullArgumentException if the given text font is null.
	 */
	public TextFormat(final Font textFont) {
		
		//Calls other constructor.
		this(textFont, false, false, DEFAULT_TEXT_SIZE, DEFAULT_TEXT_COLOR);
	}
	
	//constructor
	/**
	 * Creates a new {@link TextFormat} with the given text font, text size and text color.
	 * 
	 * @param textFont
	 * @param bold
	 * @param textSize
	 * @param textColor
	 * @throws NullArgumentException if the given text font is null.
	 * @throws NonPositiveArgumentException if the given text size is not positive.
	 * @throws NullArgumentException if the given text color is null.
	 */
	public TextFormat(
		final Font textFont,
		final boolean bold,
		final int textSize,
		final Color textColor
	) { 
		//Calls other constructor.
		this(textFont, bold, false, textSize, textColor);
	}
	
	//constructor
	/**
	 * Creates a new {@link TextFormat} with the given text font, text size and text color.
	 * 
	 * @param textFont
	 * @param bold
	 * @param italic
	 * @param textSize
	 * @param textColor
	 * @throws NullArgumentException if the given text font is null.
	 * @throws NonPositiveArgumentException if the given text size is not positive.
	 * @throws NullArgumentException if the given text color is null.
	 */
	public TextFormat(
		final Font textFont,
		final boolean bold,
		final boolean italic,
		final int textSize,
		final Color textColor
	) {
		
		//Checks if the given text font is not null.
		Validator.suppose(textFont).isInstanceOf(Font.class);
		
		//Checks if the given text size is positive.
		Validator.suppose(textSize).thatIsNamed("text size").isPositive();
		
		//Checks if the given text color is not null.
		Validator.suppose(textColor).thatIsNamed(TEXT_COLOR_HEADER).isInstance();
		
		this.textFont = textFont;
		this.bold = bold;
		this.italic = italic;
		this.textSize = new PositiveInteger(textSize);
		this.textColor = textColor;
		
		//Extracts the swing text style of the current text format.
			var swingTextStyle = java.awt.Font.PLAIN;
			
			//Handles the case that the bold flag of the current text format is true.
			if (getBoldFlag()) {
				swingTextStyle |= java.awt.Font.BOLD;
			}
			
			//Handles the case that the italic flag of the current text format is true.
			if (getItalicFlag()) {
				swingTextStyle |= java.awt.Font.ITALIC;
			}
		
		//Creates the swing font of the current text format.
		this.swingFont
		= new java.awt.Font(
			getTextFont().getSwingFontFamily(),
			swingTextStyle,
			getTextSize()
		);
	}
	
	//constructor
	/**
	 * Creates a new {@link TextFormat} with the given text font, text size and text color.
	 * 
	 * @param textFont
	 * @param textSize
	 * @param textColor
	 * @throws NullArgumentException if the given text font is null.
	 * @throws NonPositiveArgumentException if the given text size is not positive.
	 * @throws NullArgumentException if the given text color is null.
	 */
	public TextFormat(
		final Font textFont,
		final int textSize,
		final Color textColor
	) {
		
		//Calls other constructor.
		this(textFont, false, false, textSize, textColor);
	}
	
	//constructor
	/**
	 * Creates a new {@link TextFormat} with the given text size.
	 * 
	 * @param textSize
	 * @throws NonPositiveArgumentException if the given text size is not positive.
	 */
	public TextFormat(final int textSize) {
		
		//Calls other constructor.
		this(DEFAULT_TEXT_FONT, false, false, textSize, DEFAULT_TEXT_COLOR);
	}
	
	//method
	/**
	 * @return the attributes of the current {@link TextFormat}.
	 */
	public List<DocumentNode> getAttributes() {
		return new List<DocumentNode>(
			textFont.getSpecificationAs(TEXT_FONT_HEADER),
			new Boolean(getBoldFlag()).getSpecificationAs(BOLD_FLAG_HEADER),
			new Boolean(getItalicFlag()).getSpecificationAs(ITALIC_FLAG_HEADER),
			textSize.getSpecificationAs(TEXT_SIZE_HEADER),
			textColor.getSpecificationAs(TEXT_COLOR_HEADER)
		);
	}
	
	//method
	/**
	 * @return the bold flag of the current {@TextFormat}.
	 */
	public boolean getBoldFlag() {
		return bold;
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
		
		//Handles the case that the given text is null.
		if (text == null) {
			text = StringCatalogue.NULL_NAME;
		}
		
		//Extracts the first part of the given text.
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
	 * @return the bold flag of the current {@TextFormat}.
	 */
	public boolean getItalicFlag() {
		return italic;
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
		return textFont;
	}
	
	//method
	/**
	 * The text size is normative.
	 * The text height depends on the text font and can differ.
	 * 
	 * @return the text height of the current {@link TextFormat}.
	 */
	public int getTextHeight() {
		return canvas.getFontMetrics(swingFont).getHeight();
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
	public int getSwingTextWidth(final char character) {
		return getSwingTextWidth(String.valueOf(character));
	}
	
	//method
	/**
	 * @return the width of the given text when the current {@link TextFormat} would be applied to it.
	 */
	public int getSwingTextWidth(final String text) {
		return canvas.getFontMetrics(swingFont).stringWidth(text);
	}
	
	//method
	/**
	 * Lets the current {@link TextFormat} paint the given text using the given graphics.
	 * 
	 * @param graphics
	 * @param text
	 */
	public void paintSwingText(final Graphics graphics, final String text) {
		
		//Calls other method.
		paintSwingText(graphics, 0, 0, text);
	}
	
	//method
	/**
	 * Lets the current {@link TextFormat} paint the given text
	 * at the given position using the given graphics.
	 * 
	 * @param graphics
	 * @param xPosition
	 * @param yPosition
	 * @param text
	 */
	public void paintSwingText(		
		final Graphics graphics,
		final int xPosition,
		final int yPosition,
		final String text
	) {
		graphics.setFont(swingFont);
		graphics.setColor(getTextColor().createSwingColor());
		graphics.drawString(text, xPosition, yPosition + getTextSize());		
	}
	
	//method
	/**
	 * Lets the current {@link TextFormat} paint the given text using the given graphics.
	 * 
	 * Only the first part of the given text,
	 * that is not longer than the given max width, will be painted.
	 * 
	 * @param graphics
	 * @param text
	 * @param maxWidth
	 * @throws NegativeArgumentException if the given max width is negative.
	 */
	public void paintSwingText(
		final Graphics graphics,
		final String text,
		final int maxWidth
	) {
		
		//Calls other method.
		paintSwingText(graphics, getFirstPart(text, maxWidth));
	}
	
	//method
	/**
	 * Lets the current {@link TextFormat} paint the given text at the given position using the given graphics.
	 * 
	 * Only the first part of the given text,
	 * that is not longer than the given max width, will be painted.
	 * 
	 * @param graphics
	 * @param xPosition
	 * @param yPosition
	 * @param maxWidth
	 * @param text
	 * @throws NegativeArgumentException if the given max width is negative.
	 */
	public void paintSwingText(	
		final Graphics graphics,
		final int xPosition,
		final int yPosition,
		final String text,
		final int maxWidth
	) {
		
		//Calls other method.
		paintSwingText(graphics, xPosition, yPosition, getFirstPart(text, maxWidth));	
	}
}
