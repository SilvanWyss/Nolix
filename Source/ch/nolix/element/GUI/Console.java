//package declaration
package ch.nolix.element.GUI;

//Java import
import java.awt.Graphics;
import java.awt.event.KeyEvent;

//own imports
import ch.nolix.core.constants.StringManager;
import ch.nolix.core.container.AccessorContainer;
import ch.nolix.core.container.IContainer;
import ch.nolix.core.container.List;
import ch.nolix.core.interfaces.Clearable;
import ch.nolix.core.specification.StandardSpecification;
import ch.nolix.element.basic.Color;
import ch.nolix.element.data.GraphicText;

//class
/**
 * @author Silvan Wyss
 * @month 2017-03
 * @lines 250
 */
public final class Console
extends BorderWidget<Console, ConsoleStructure>
implements Clearable {
	
	//type name
	public final String TYPE_NAME = "Console";
	
	//default value
	public static final Color DEFAULT_BACKGROUND_COLOR = new Color(Color.BLACK);
	
	//attribute header
	private final static String TEXT_LINES_HEADER = "TextLines";
	
	//attribute
	private String nextTextLine = StringManager.EMPTY_STRING;
	
	//multiple attribute
	private final List<String> textLines = new List<String>();
	
	//method
	/**
	 * Adds or change the given attribute to this console.
	 * 
	 * @param attribute
	 * @throws InvalidArgumentException if the given attribute is not valid.
	 */
	public void addOrChangeAttribute(final StandardSpecification attribute) {
		
		//Enumerates the header of the given attribute.
		switch (attribute.getHeader()) {
			case TEXT_LINES_HEADER:
				addTextLines(attribute.getRefAttributes().to(a -> a.toString()));
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
		
		//Iterates the given text lines.
		for (final String tl : textLines) {
			addTextLine(tl);
		}
		
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

		//Iterates the given text lines.
		for (final String tl : textLines) {
			addTextLine(tl);
		}
		
		return this;
	}
	
	//method
	/**
	 * Removes all text lines of this console.
	 */
	public void clear() {
		textLines.clear();
		nextTextLine = StringManager.EMPTY_STRING;
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
	public List<StandardSpecification> getAttributes() {
		
		//Calls method of the base class.
		final List<StandardSpecification> attributes = super.getAttributes();
		
		//Handles the option that this console contains text lines.
		if (containsTextLines()) {
			attributes.addAtEnd(new StandardSpecification(TEXT_LINES_HEADER, textLines));
		}
		
		return attributes;
	}
	
	//method
	/**
	 * @return the widgets of this console.
	 */
	public AccessorContainer<Widget<?, ?>> getRefWidgets() {
		return new AccessorContainer<>();
	}
	
	//method
	/**
	 * @return the text lines of this console.
	 */
	public AccessorContainer<String> getTextLines() {
		return new AccessorContainer<String>(textLines);
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
	
	public void noteKeyTyping(KeyEvent keyEvent) {
		
		if (Character.isLetter(keyEvent.getKeyChar()) || Character.isDigit(keyEvent.getKeyChar())) {
			nextTextLine += keyEvent.getKeyChar();
		}
		
		switch (keyEvent.getKeyCode()) {
		
			case KeyEvent.VK_ENTER:
				addTextLine(nextTextLine);
				nextTextLine = StringManager.EMPTY_STRING;
			case KeyEvent.VK_LEFT:

				break;
			case KeyEvent.VK_RIGHT:

				break;
			case KeyEvent.VK_BACK_SPACE:

				break;
			case KeyEvent.VK_DELETE:
				break;
		}
	}
	
	//method
	/**
	 * Resets the configuration of this console.
	 */
	public void resetConfiguration() {
		
		//Calls method of the base class.
		super.resetConfiguration();
		
		getRefNormalStructure().setBackgroundColor(DEFAULT_BACKGROUND_COLOR);
	}
	
	//method
	/**
	 * Creates new widget structure for this console.
	 */
	protected ConsoleStructure createWidgetStructure() {
		return new ConsoleStructure();
	}

	//method
	/**
	 * @return the height of the content of this console.
	 */
	protected int getContentHeight() {
		
		final ConsoleStructure currentStructure = getRefCurrentStructure();
		
		return (
			currentStructure.getActiveHeight()
			- currentStructure.getActiveTopBorderSize()
			- currentStructure.getActiveTopPadding()
			- currentStructure.getActiveBottomBorderSize()
			- currentStructure.getActiveBottomPadding()
		);
	}

	//method
	/**
	 * @return the width of the content of this console.
	 */
	protected int getContentWidth() {
		
		final ConsoleStructure currentStructure = getRefCurrentStructure();
		
		return (
			currentStructure.getActiveWidth()
			- currentStructure.getActiveLeftBorderSize()
			- currentStructure.getActiveLeftPadding()
			- currentStructure.getActiveRightBorderSize()
			- currentStructure.getActiveRightPadding()
		);
	}

	//method
	/**
	 * Paints the content of this console using the given widget structure and graphics.
	 * 
	 * @param widgetStructure
	 * @param graphics
	 */
	protected void paintContent(final ConsoleStructure widgetStructure, final Graphics graphics) {
		
		final int contentHeight = getContentHeight();
		final int textSize = widgetStructure.getActiveTextSize();
		final GraphicText graphicText =
		new GraphicText()
		.setSize(widgetStructure.getActiveTextSize())
		.setColor(widgetStructure.getActiveTextColor());
		
		//Iterates the text lines of this console.
		int totalTextLinesHight = 0;
		for (final String tl : getTextLines()) {
			
			totalTextLinesHight += textSize;
			if (totalTextLinesHight > contentHeight) {
				break;
			}
			
			graphicText.setText(">" + tl);
			graphicText.paint(graphics);
			graphics.translate(0, textSize);
		}
		
		graphicText.setText(">" + nextTextLine);
		
		graphics.setColor(java.awt.Color.GRAY);
		graphics.fillRect(graphicText.getWidth() - 1, 0, 2, graphicText.getHeight());

		graphicText.paint(graphics);
		
	}
}
