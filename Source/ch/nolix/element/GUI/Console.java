//package declaration
package ch.nolix.element.GUI;

//Java import
import java.awt.Graphics;
import java.awt.event.KeyEvent;

//own imports
import ch.nolix.core.constants.CharacterManager;
import ch.nolix.core.constants.StringManager;
import ch.nolix.core.container.AccessorContainer;
import ch.nolix.core.container.IContainer;
import ch.nolix.core.container.List;
import ch.nolix.core.interfaces.Clearable;
import ch.nolix.core.sequencer.Sequencer;
import ch.nolix.core.specification.StandardSpecification;
import ch.nolix.core.validator2.Validator;
import ch.nolix.element.basic.Color;

//class
/**
 * @author Silvan Wyss
 * @month 2017-03
 * @lines 520
 */
public final class Console
extends BorderWidget<Console, ConsoleStructure>
implements Clearable {
	
	//type name
	public static final String TYPE_NAME = "Console";
	
	//default value
	public static final Color DEFAULT_BACKGROUND_COLOR = new Color(Color.BLACK);
	
	//attribute headers
	private final static String TEXT_LINES_HEADER = "TextLines";
	
	//attribute
	//The edit line is the line of this console that can be edited.
	private String editLine = StringManager.EMPTY_STRING;
	
	//attribute
	//The text cursor position is the position of the text cursor in the edit line of this console.
	//The text cursor position is measured by the number of characters.
	private int textCursorPosition = 0;
	
	//multiple attribute
	private final List<String> textLines = new List<String>();
	
	//constructor
	/**
	 * Creates new console.
	 */
	public Console() {
		resetConfiguration();
	}
	
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
				attribute.getRefAttributes().forEach(a -> addTextLine(a.toString()));
				break;
			default:
				
				//Calls method of the base class.
				super.addOrChangeAttribute(attribute);
		}
	}
	
	//method
	/**
	 * Adds the next line to this console that consists of the given character.
	 * 
	 * @param character
	 * @return this console.
	 */
	public Console addTextLine(final char character) {
		return addTextLine(Character.toString(character));
	}
	
	//method
	/**
	 * Adds the given text line to this console and clears the edit line of this console.
	 * 
	 * @param textLine
	 * @return this console.
	 * @throws NullArgumentException if the given text line is null.
	 */
	public Console addTextLine(final String textLine) {
		
		textLines.addAtEnd(textLine);
		clearEditLine();
		
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
	 * Removes all text lines of this console and clears the edit line of this console.
	 */
	public void clear() {
		textLines.clear();
		clearEditLine();
	}
	
	//method
	/**
	 * Clears the edit line of this console.
	 */
	public void clearEditLine() {		
		editLine = StringManager.EMPTY_STRING;
		textCursorPosition = 0;
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
	 * Deletes the character after the text cursor
	 * in the edit line of this console if there is one.
	 */
	public final void deleteCharacterAfterTextCursor() {
		if (textCursorPosition < editLine.length()) {
			editLine
			= getEditLineBeforeTextCursor() + getEditLineAfterTextCursor().substring(1);
		}
	}
	
	//method
	/**
	 * Deletes the character before the text cursor
	 * in the edit line of this console if there is one.
	 */
	public final void deleteCharacterBeforeTextCursor() {
		if (!editLine.isEmpty() && textCursorPosition > 0) {
			
			editLine
			= editLine.substring(0, textCursorPosition  - 1) + getEditLineAfterTextCursor();
			
			textCursorPosition--;
		}
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
	 * The edit line of a console is its next text line that can be edited.
	 * 
	 * @return the edit line of this console.
	 */
	public String getEditLine() {
		return editLine;
	}
	
	//method
	/**
	 * @return the text of the edit line of this console after the text cursor.
	 */
	public String getEditLineAfterTextCursor() {
		return editLine.substring(textCursorPosition);
	}
	
	//method
	/**
	 * @return the text of the edit line of this console before the text cursor.
	 */
	public String getEditLineBeforeTextCursor() {
		return editLine.substring(0, textCursorPosition);
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
	 * Inserts the given character into the edit line of this console after the text cursor.
	 * 
	 * @param character
	 */
	public final void insertCharacterAfterCursor(final char character) {
		
		final int originalTextCursorPosition = textCursorPosition;
		
		setEditLine(
			getEditLineBeforeTextCursor()
			+ character
			+ getEditLineAfterTextCursor()
		);
		
		textCursorPosition = originalTextCursorPosition + 1;
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
	 * Moves the text cursor position in the edit line of this console one character to the left if possible.
	 */
	public void moveTextCursorPositionToLeft() {
		if (textCursorPosition > 0) {
			textCursorPosition--;
		}
	}
	
	//method
	/**
	 * Moves the text cursor position in the edit line of this console one character to the right if possible.
	 */
	public void moveTextCursorPositionToRight() {
		if (textCursorPosition < getEditLine().length()) {
			textCursorPosition++;
		}
	}
	
	//method
	/**
	 * Lets this console note a key typing.
	 */
	public void noteKeyTyping(final KeyEvent keyEvent) {
		
		//Enumerates the key code of the given key event.
		switch (keyEvent.getKeyCode()) {
			case KeyEvent.VK_SPACE:
				insertCharacterAfterCursor(CharacterManager.SPACE);
				break;
			case KeyEvent.VK_ENTER:
				addTextLine(editLine);
				break;
			case KeyEvent.VK_LEFT:
				moveTextCursorPositionToLeft();
				break;
			case KeyEvent.VK_RIGHT:
				moveTextCursorPositionToRight();
				break;
			case KeyEvent.VK_BACK_SPACE:
				deleteCharacterBeforeTextCursor();
				break;
			case KeyEvent.VK_DELETE:
				deleteCharacterAfterTextCursor();
				break;
			default:
				if (Character.isDefined(keyEvent.getKeyChar())) {
					insertCharacterAfterCursor(keyEvent.getKeyChar());
				}
		}
	}
	
	//method
	/**
	 * Attention: Clears the edit line of this console.
	 * Attention: This method lasts until this console receives a next character.
	 * 
	 * @return
	 */
	public char readNextCharacter() {
		
		clearEditLine();
		
		while (getEditLine().isEmpty()) {
			Sequencer.waitForMilliseconds(100);
		}
		
		final char nextCharacter = getEditLine().charAt(0);	
		addTextLine(nextCharacter);
		
		return nextCharacter;
	}
	
	//method
	/**
	 * Attention: Clears the edit line of this console.
	 * Attention: This method lasts until this console receives a next line.
	 * 
	 * @return the next text line of this conosle, that is not empty.
	 * 
	 */
	public String readNextNonEmptyTextLine() {
		while (true) {
			
			final String nextTextLine = readNextTextLine();
			
			if (!nextTextLine.isEmpty()) {
				return nextTextLine;
			}
		}
	}
	
	//method
	/**
	 * Attention: Clears the edit line of this console.
	 * Attention: This method lasts until this console receives a next line.
	 * 
	 * @return the next text line of this console.
	 */
	public String readNextTextLine() {
		
		clearEditLine();
		
		final int textLineCount = textLines.getElementCount();
		
		while (getTextLines().getElementCount() == textLineCount) {
			Sequencer.waitForMilliseconds(100);
		}
		
		return textLines.getRefLast();
	}
	
	//method
	/**
	 * Resets the configuration of this console.
	 */
	public void resetConfiguration() {
		
		//Calls method of the base class.
		super.resetConfiguration();
		
		setCursorIcon(CursorIcon.Edit);
		getRefNormalStructure().setBackgroundColor(DEFAULT_BACKGROUND_COLOR);
	}
	
	//method
	/**
	 * Sets the edit line of this console.
	 * 
	 * @param editLine
	 * @return this console
	 * @throws NullArgumentException if the given edit line is null.
	 */
	public Console setEditLine(final String editLine) {
		
		//Checks if the given edit line is not null.
		Validator.supposeThat(editLine).thatIsNamed("edit line").isNotNull();
		
		//Sets the edit line of this console.
		this.editLine = editLine;
		
		//Sets the text cursor position at the end of the edit line.
		textCursorPosition = getEditLine().length();
		
		return this;
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
		
		final int contentWidth = getContentWidth();
		final int contentHeight = getContentHeight();
		final int textSize = widgetStructure.getActiveTextSize();	
		final Font font = new Font(FontFamily.Console, textSize, widgetStructure.getActiveTextColor());
		final int textlineCount = getTextLines().getElementCount();
		final int shownTextLineCount = contentHeight / textSize;
		
		//Iterates the text lines of this console.
		int lineNumber = 1;
		for (final String tl : getTextLines()) {
			
			int offsetTextLineCount = isEnabled() ? 2 : 1;
			
			if (lineNumber > textlineCount - shownTextLineCount + offsetTextLineCount) {
				font.paintText(getLinePrefix() + tl, getContentWidth(), graphics);
				graphics.translate(0, textSize);
			}
			
			lineNumber++;
		}
		
		if (isEnabled()) {
		
			//Paints the edit line of this console.
			font.paintText(getLinePrefix() + getEditLine(), contentWidth, graphics);
				
			//Paints the text cursor of this console.
			final int textCursorXPosition
			= font.getTextWidth(getLinePrefix()
			+ getEditLineBeforeTextCursor())
			- 1;
			
			if (textCursorXPosition < contentWidth) {
				graphics.setColor(java.awt.Color.GRAY);
				graphics.fillRect(
					textCursorXPosition,
					0,
					2,
					font.getTextSize()
				);
			}
		}
	}

	//method
	/**
	 * The line prefix is a prefix that is written at the beginning of each line of this console.
	 * 
	 * @return the line prefix of this console.
	 */
	private String getLinePrefix() {
		return "> ";
	}
}
