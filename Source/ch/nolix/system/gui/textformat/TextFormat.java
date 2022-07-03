//package declaration
package ch.nolix.system.gui.textformat;

//Java imports
import java.awt.Canvas;
import java.awt.Graphics;

import ch.nolix.core.commontype.constant.CharacterCatalogue;
import ch.nolix.core.commontype.constant.StringCatalogue;
import ch.nolix.core.container.main.LinkedList;
import ch.nolix.core.document.node.Node;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.NegativeArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.NonPositiveArgumentException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.documentapi.nodeapi.INode;
import ch.nolix.system.gui.color.Color;
import ch.nolix.systemapi.guiapi.textformatapi.ITextFormat;

//class
/**
 * A {@link TextFormat} can paint texts with a specific
 * -font
 * -bold style
 * -italic style
 * -text size
 * -text color
 * 
 * A {@link TextFormat} is not mutable.
 * 
 * @author Silvan Wyss
 * @date 2017-08-26
 */
public final class TextFormat implements ITextFormat {
	
	//constants
	public static final Font DEFAULT_TEXT_FONT = Font.VERDANA;
	public static final int DEFAULT_TEXT_SIZE = 20;
	public static final Color DEFAULT_TEXT_COLOR = Color.BLACK;
	
	//constants
	private static final String TEXT_FONT_HEADER = "TextFont";
	private static final String BOLD_FLAG_HEADER = "Bold";
	private static final String ITALIC_FLAG_HEADER = "Italic";
	private static final String TEXT_SIZE_HEADER = "TextSize";
	private static final String TEXT_COLOR_HEADER = "TextColor";
	
	//attributes
	private final Font font;
	private final boolean bold;
	private final boolean italic;
	private final int textSize;
	private final Color textColor;
	private final java.awt.Font swingFont;
	private final Canvas canvas = new Canvas();
	
	//static method
	/**
	 * Creates a new {@link TextFormat} from the given specification.
	 * The given specification must specify all attributes of the {@link TextFormat}.
	 * 
	 * @param specification
	 * @return a new {@link TextFormat} from the given specification
	 * @throws InvalidArgumentException if the given specification is not valid.
	 */
	public static TextFormat fromSpecification(final INode<?> specification) {
		
		//Extracts the attributes of the given specification.
		final var attributes = specification.getRefChildNodes();
		
		return
		new TextFormat(
			Font.fromSpecification(attributes.getRefAt(1)),
			attributes.getRefAt(2).getSingleChildNodeAsBoolean(),
			attributes.getRefAt(3).getSingleChildNodeAsBoolean(),
			attributes.getRefAt(4).getSingleChildNodeAsInt(),
			Color.fromSpecification(attributes.getRefAt(5))
		);
	}
	
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
	 * @throws ArgumentIsNullException if the given text color is null.
	 */
	public TextFormat(final Color textColor) {
		
		//Calls other constructor.
		this(DEFAULT_TEXT_FONT, false, false, DEFAULT_TEXT_SIZE, textColor);
	}
	
	//constructor
	/**
	 * Creates a new {@link TextFormat} with the given font.
	 * 
	 * @param font
	 * @throws ArgumentIsNullException if the given font is null.
	 */
	public TextFormat(final Font font) {
		
		//Calls other constructor.
		this(font, false, false, DEFAULT_TEXT_SIZE, DEFAULT_TEXT_COLOR);
	}
	
	//constructor
	/**
	 * Creates a new {@link TextFormat} with the given font, text size and text color.
	 * 
	 * @param font
	 * @param bold
	 * @param textSize
	 * @param textColor
	 * @throws ArgumentIsNullException if the given font is null.
	 * @throws NonPositiveArgumentException if the given text size is not positive.
	 * @throws ArgumentIsNullException if the given text color is null.
	 */
	public TextFormat(
		final Font font,
		final boolean bold,
		final int textSize,
		final Color textColor
	) {
		//Calls other constructor.
		this(font, bold, false, textSize, textColor);
	}
	
	//constructor
	/**
	 * Creates a new {@link TextFormat} with the given font, text size and text color.
	 * 
	 * @param font
	 * @param bold
	 * @param italic
	 * @param textSize
	 * @param textColor
	 * @throws ArgumentIsNullException if the given font is null.
	 * @throws NonPositiveArgumentException if the given text size is not positive.
	 * @throws ArgumentIsNullException if the given text color is null.
	 */
	public TextFormat(
		final Font font,
		final boolean bold,
		final boolean italic,
		final int textSize,
		final Color textColor
	) {
		
		//Asserts that the given font is not null.
		GlobalValidator.assertThat(font).isOfType(Font.class);
		
		//Asserts that the given text size is positive.
		GlobalValidator.assertThat(textSize).thatIsNamed("text size").isPositive();
		
		//Asserts that the given text color is not null.
		GlobalValidator.assertThat(textColor).thatIsNamed(TEXT_COLOR_HEADER).isNotNull();
		
		this.font = font;
		this.bold = bold;
		this.italic = italic;
		this.textSize = textSize;
		this.textColor = textColor;
		
		//Extracts the swing text style of the current text format.
			var swingTextStyle = java.awt.Font.PLAIN;
			
			//Handles the case that the bold flag of the current text format is true.
			if (isBold()) {
				swingTextStyle |= java.awt.Font.BOLD;
			}
			
			//Handles the case that the italic flag of the current text format is true.
			if (isItalic()) {
				swingTextStyle |= java.awt.Font.ITALIC;
			}
		
		//Creates the swing font of the current text format.
		this.swingFont
		= new java.awt.Font(
			getTextFont().getCode(),
			swingTextStyle,
			getTextSize()
		);
	}
	
	//constructor
	/**
	 * Creates a new {@link TextFormat} with the given font, text size and text color.
	 * 
	 * @param font
	 * @param textSize
	 * @param textColor
	 * @throws ArgumentIsNullException if the given font is null.
	 * @throws NonPositiveArgumentException if the given text size is not positive.
	 * @throws ArgumentIsNullException if the given text color is null.
	 */
	public TextFormat(
		final Font font,
		final int textSize,
		final Color textColor
	) {
		
		//Calls other constructor.
		this(font, false, false, textSize, textColor);
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
	 * {@inheritDoc}
	 */
	@Override
	public void fillUpAttributesInto(final LinkedList<INode<?>> list) {
		list.addAtEnd(
			font.getSpecificationWithHeader(TEXT_FONT_HEADER),
			Node.withHeaderAndChildNode(BOLD_FLAG_HEADER, bold),
			Node.withHeaderAndChildNode(ITALIC_FLAG_HEADER, italic),
			Node.withHeaderAndChildNode(TEXT_SIZE_HEADER, textSize),
			textColor.getSpecificationWithHeader(TEXT_COLOR_HEADER)
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
	 * @param attachEllipsis
	 * @return the first part of the given text the current {@link TextFormat} can paint,
	 * that is not longer than the given max width,
	 * optionally with an ellipsis if the given text is longer than the given max width.
	 */
	public String getFirstPart(String text, final int maxWidth, final boolean attachEllipsis) {
		
		//Asserts that the given max width is not negative.
		GlobalValidator.assertThat(maxWidth).thatIsNamed("max width").isNotNegative();
		
		//Handles the case that the given text is null.
		if (text == null) {
			text = StringCatalogue.NULL_HEADER;
		}
		
		//Extracts the first part of the given text.
		String firstPart = StringCatalogue.EMPTY_STRING;
		while (firstPart.length() < text.length() && getSwingTextWidth(firstPart) < maxWidth) {
			firstPart = text.substring(0, firstPart.length() + 1);
		}
		
		//Handles the case that an ellipsis has to be attached to the first part.
		if (attachEllipsis && firstPart.length() < text.length()) {
				
			while (getSwingTextWidth(firstPart + CharacterCatalogue.ELLIPSIS) > maxWidth) {
				firstPart = firstPart.substring(0, firstPart.length() - 1);
			}
		
			firstPart += CharacterCatalogue.ELLIPSIS;
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
	 * @return the font of the current {@link TextFormat}.
	 */
	public Font getTextFont() {
		return font;
	}
	
	//method
	/**
	 * The text size is normative.
	 * The text height depends on the font and can differ.
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
		return textSize;
	}
	
	//method
	/**
	 * @param character
	 * @return the width of the given character when the current {@link TextFormat} would be applied to it.
	 */
	public int getSwingTextWidth(final char character) {
		return getSwingTextWidth(String.valueOf(character));
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getSwingTextWidth(final String text) {
		return canvas.getFontMetrics(swingFont).stringWidth(text);
	}
	
	//method
	/**
	 * @return true if the current {@link TextFormat} is bold.
	 */
	public boolean isBold() {
		return bold;
	}
	
	//method
	/**
	 * @return true if the current {@link TextFormat} is italic.
	 */
	public boolean isItalic() {
		return italic;
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void paintSwingText(final Graphics graphics, final String text) {
		
		//Calls other method.
		paintSwingText(graphics, 0, 0, text);
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void paintSwingText(final Graphics graphics,	final int xPosition, final int yPosition, final String text) {
		graphics.setFont(swingFont);
		graphics.setColor(getTextColor().toSwingColor());
		graphics.drawString(text, xPosition, yPosition + getTextSize());
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void paintSwingText(final Graphics graphics, final String text, final int maxWidth) {
		
		//Calls other method.
		paintSwingText(graphics, getFirstPart(text, maxWidth));
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
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
