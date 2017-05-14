//package declaration
package ch.nolix.element.GUI;

//Java import
import java.awt.Graphics;

//own imports

import ch.nolix.core.container.IContainer;
import ch.nolix.core.container.List;
import ch.nolix.core.interfaces.Clearable;
import ch.nolix.core.specification.Specification;
import ch.nolix.element.basic.Color;
import ch.nolix.element.data.BackgroundColor;
import ch.nolix.element.data.GraphicText;

//class
/**
 * @author Silvan Wyss
 * @month 2017-03
 * @lines 250
 */
public final class Console
extends BorderWidget<ConsoleStructure, Console>
implements Clearable {
	
	//constant
	public final String SIMPLE_CLASS_NAME = "Console";
	
	//default values
	public static final int DEFAULT_WIDTH = 200;
	public static final int DEFAULT_HEIGHT = 400;
	public static final int DEFAULT_BACKGROUND_COLOR = BackgroundColor.LIGHT_GREY;
	public static final int DEFAULT_TEXT_SIZE = ValueCatalog.MEDIUM_TEXT_SIZE;
	public static final int DEFAULT_TEXT_COLOR = Color.BLACK;
	
	//attribute headers
	private final static String TEXT_LINES_HEADER = "TextLines";
	
	//multiple attribute
	private final List<String> textLines = new List<String>();

	//constructor
	/**
	 * Creates new console with default values.
	 */
	public Console() {
		
		//Calls constructor of the base class.
		super(new ConsoleStructure(), new ConsoleStructure(), new ConsoleStructure());
	}
	
	//method
	/**
	 * Adds or change the given attribute to this console.
	 * 
	 * @param attribute
	 * @throws InvalidArgumentException if the given attribute is not valid.
	 */
	public void addOrChangeAttribute(final Specification attribute) {
		
		//Enumerates the given attribute.
		switch (attribute.getHeader()) {
			case TEXT_LINES_HEADER:
				addTextLines(attribute.getRefAttributes().toContainer(a -> a.toString()));
				break;
			default:
				
				//Calls method of the base class.
				super.addOrChangeAttribute(attribute);
		}
	}
	
	//method
	/**
	 * Adds the given text line to this console.
	 * 
	 * @param textLine
	 * @return this console.
	 * @throws NullArgumentException if the given text line is null.
	 */
	public Console addTextLine(final String textLine) {
		
		textLines.addAtEnd(textLine);
		
		return this;
	}
	
	//method
	/**
	 * Adds the given text lines to this console.
	 * 
	 * @param textLines
	 * @return this console.
	 * @throws NullArgumentException if one of the given text lines is null.
	 */
	public Console addTextLine(final String... textLines) {
		
		this.textLines.addAtEnd(textLines);
		
		return this;
	}
	
	//method
	/**
	 * Adds the given text lines to this console.
	 * 
	 * @param textLines
	 * @return this console.
	 * @throws NullArgumentException if one of the given text lines is null.
	 */
	public Console addTextLines(final IContainer<String> textLines) {

		this.textLines.addAtEnd(textLines);
		
		return this;
	}
	
	//method
	/**
	 * Removes all text lines of this console.
	 */
	public void clear() {
		textLines.clear();
	}
	
	//method
	/**
	 * @return true if this console contains any text lines.
	 */
	public boolean containsTextLines() {
		return textLines.containsAny();
	}
	
	//method
	/**
	 * @return the attributes of this console.
	 */
	public List<Specification> getAttributes() {
		
		//Calls method of the base class.
		final List<Specification> attributes = super.getAttributes();
		
		if (containsTextLines()) {
			attributes.addAtEnd(new Specification(TEXT_LINES_HEADER, textLines.toArray()));
		}
		
		return attributes;
	}
	
	//method
	/**
	 * @return the text lines of this console.
	 */
	public IContainer<String> getTextLines() {
		return textLines;
	}
	
	//method
	/**
	 * @return true if this console has the given role.
	 */
	public boolean hasRole(final String role) {
		return false;
	}
	
	//method
	/**
	 * @return true if this console contains no text line.
	 */
	public boolean isEmpty() {
		return getTextLines().isEmpty();
	}
	
	//method
	/**
	 * Resets the configuration of this console.
	 */
	public void resetConfiguration() {
		
		//Calls method of the base class.
		super.resetConfiguration();
		
		getRefNormalStructure().setBackgroundColor(new BackgroundColor(DEFAULT_BACKGROUND_COLOR));
	}

	//method
	/**
	 * @return the current height of the content of this console.
	 */
	protected int getContentHeight() {
		
		final ConsoleStructure consoleStructure = getRefCurrentStructure();
		
		return (
			consoleStructure.getActiveHeight()
			- consoleStructure.getActiveTopBorderSize()
			- getTopPadding()
			- consoleStructure.getActiveBottomBorderSize()
			- getBottomPadding()
		);
	}

	//method
	/**
	 * @return the current width of the content of this console.
	 */
	protected int getContentWidth() {
		
		final ConsoleStructure consoleStructure = getRefCurrentStructure();
		
		return (
			consoleStructure.getActiveWidth()
			- consoleStructure.getActiveLeftBorderSize()
			- getLeftPadding()
			- consoleStructure.getActiveRightBorderSize()
			- getRightPadding()
		);
	}

	//method
	/**
	 * Paints this console using the given rectangle structure and graphics.
	 * 
	 * @param rectangleStructure
	 * @param graphics
	 */
	protected void paintContent(final ConsoleStructure rectangleStructure, final Graphics graphics) {
		
		final int contentHeight = getContentHeight();
		final int textSize = rectangleStructure.getActiveTextSize();
		final GraphicText graphicText =
		new GraphicText()
		.setSize(rectangleStructure.getActiveTextSize())
		.setColor(rectangleStructure.getActiveTextColor());
		
		//Iterates the text lines of this console.
		int totalTextLinesHight = 0;
		for (final String tl: getTextLines()) {
			
			totalTextLinesHight += textSize;
			if (totalTextLinesHight > contentHeight) {
				break;
			}
			
			graphicText.setText(">" + tl);
			graphicText.paint(graphics);
			graphics.translate(0, textSize);
		}
	}
}
