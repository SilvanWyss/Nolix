//package declaration
package ch.nolix.element.GUI;

//Java import
import java.awt.Graphics;
import java.awt.event.KeyEvent;

//own imports
import ch.nolix.core.constants.CharacterCatalogue;
import ch.nolix.core.constants.StringCatalogue;
import ch.nolix.core.container.AccessorContainer;
import ch.nolix.core.container.IContainer;
import ch.nolix.core.container.List;
import ch.nolix.core.interfaces.Clearable;
import ch.nolix.core.sequencer.Sequencer;
import ch.nolix.core.specification.StandardSpecification;
import ch.nolix.core.validator2.Validator;
import ch.nolix.element.color.Color;
import ch.nolix.element.font.Font;
import ch.nolix.element.font.TextFont;

//class
/**
 * @author Silvan Wyss
 * @month 2017-03
 * @lines 580
 */
public final class Console
extends BorderWidget<Console, ConsoleStructure>
implements Clearable<Console> {
	
	//type name
	public static final String TYPE_NAME = "Console";
	
	//default value
	public static final Color DEFAULT_BACKGROUND_COLOR = Color.BLACK;
	
	//attribute headers
	private final static String LINES_HEADER = "Lines";
	
	//attribute
	//The edit line is the line of this console that can be edited.
	private String editLine = StringCatalogue.EMPTY_STRING;
	
	//attribute
	//The text cursor position is the position of the text cursor in the edit line of this console.
	//The text cursor position is measured by the number of characters.
	private int textCursorPosition = 0;
	
	//multiple attribute
	private final List<String> lines = new List<String>();
	
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
			case LINES_HEADER:
				attribute.getRefAttributes().forEach(a -> writeLine(a.toString()));
				break;
			default:
				
				//Calls method of the base class.
				super.addOrChangeAttribute(attribute);
		}
	}
	
	//method
	/**
	 * Removes all lines of this console and clears the edit line of this console.
	 * 
	 * @return this console.
	 */
	public Console clear() {
		
		lines.clear();
		clearEditLine();
		
		return this;
	}
	
	//method
	/**
	 * Clears the edit line of this console.
	 */
	public void clearEditLine() {		
		editLine = StringCatalogue.EMPTY_STRING;
		textCursorPosition = 0;
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
		
		//Handles the option that this console contains one or several lines.
		if (containsAny()) {
			attributes.addAtEnd(new StandardSpecification(LINES_HEADER, lines));
		}
		
		return attributes;
	}
	
	//method
	/**
	 * The edit line of a console is its next line that can be edited.
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
	 * @return the lines of this console.
	 */
	public AccessorContainer<String> getLines() {
		return new AccessorContainer<String>(lines);
	}
	
	//method
	/**
	 * @return true if this console contains no line.
	 */
	public final boolean isEmpty() {
		return getLines().isEmpty();
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
				insertCharacterAfterCursor(CharacterCatalogue.SPACE);
				break;
			case KeyEvent.VK_ENTER:
				writeEditLine();
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
	 * Reads the next character from this console. 
	 * Attention: Clears the edit line of this console.
	 * Attention: Lasts until this console receives a character.
	 * 
	 * @return the next character that is written to this console.
	 */
	public char readCharacter() {
		
		clearEditLine();
		
		while (getEditLine().isEmpty()) {
			Sequencer.waitForMilliseconds(100);
		}
		
		final char nextCharacter = getEditLine().charAt(0);	
		writeLine(nextCharacter);
		
		return nextCharacter;
	}
	
	//method
	/**
	 * Reads the next enter from this console.
	 * Attention: Clears the edit line of this console.
	 * Attention: Lasts until this console receives an enter.
	 */
	public void readEnter() {
		readLine();
	}
	
	//method
	/**
	 * Reads the next line from this console.
	 * Attention: Clears the edit line of this console.
	 * Attention: Lasts until this console receives a line.
	 * 
	 * @return the next line of this console.
	 */
	public String readLine() {
		
		clearEditLine();
		
		final int lineCount = lines.getElementCount();	
		while (getLines().getElementCount() == lineCount) {
			Sequencer.waitForMilliseconds(100);
		}
		
		return lines.getRefLast();
	}
	
	//method
	/**
	 * Reads the next line, that is not empty, from this console.
	 * Attention: Clears the edit line of this console.
	 * Attention: Lasts until this console receives a non-empty line.
	 * 
	 * @return the next line, that is not empty, that is written to this console.
	 * 
	 */
	public String readNonEmptyLine() {		
		while (true) {
			
			final String nextLine = readLine();
			
			if (!nextLine.isEmpty()) {
				return nextLine;
			}
		}
	}
	
	//method
	/**
	 * Resets the configuration of this console.
	 */
	public void resetConfiguration() {
		
		//Calls method of the base class.
		super.resetConfiguration();
		
		setCursorIcon(CursorIcon.Edit);
		getRefNormalStructure()
		.setBackgroundColor(DEFAULT_BACKGROUND_COLOR)
		.setTextFont(TextFont.Console);
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
	 * Writes the edit line of this console to this console.
	 * Attention: Clears the edit line of this console.
	 * 
	 * @return this console.
	 */
	public Console writeEditLine() {
		return writeLine(editLine);
	}
	
	//method
	/**
	 * Writes an empty line to this console.
	 * Attention: Clears the edit line of this console.
	 * 
	 * @return this console.
	 */
	public Console writeEmptyLine() {
		return writeLine(StringCatalogue.EMPTY_STRING);
	}
	
	//method
	/**
	 * Writes a line to this console that consists of the given character.
	 * Attention: Clears the edit line of this console.
	 * 
	 * @param character
	 * @return this console.
	 */
	public Console writeLine(final char character) {
		return writeLine(Character.toString(character));
	}
	
	//method
	/**
	 * Writes the given line to this console.
	 * Attention: Clears the edit line of this console.
	 * 
	 * @param line
	 * @return this console.
	 * @throws NullArgumentException if the given line is null.
	 */
	public Console writeLine(final String line) {
		
		lines.addAtEnd(line);
		clearEditLine();
		
		return this;
	}
	
	//method
	/**
	 * Writes the given lines to this console.
	 * Attention: Clears the edit line of this console.
	 * 
	 * @param lines
	 * @return this console.
	 * @throws NullArgumentException if one of the given lines is null.
	 */
	public Console writeLine(final String... lines) {
		
		//Iterates the given lines.
		for (final String l : lines) {
			writeLine(l);
		}
		
		return this;
	}
	
	//method
	/**
	 * Writes the given lines to this console.
	 * Attention: Clears the edit line of this console.
	 * 
	 * @param lines
	 * @return this console.
	 * @throws NullArgumentException if one of the given lines is null.
	 */
	public Console writeLines(final IContainer<String> lines) {

		//Iterates the given lines.
		for (final String l : lines) {
			writeLine(l);
		}
		
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
		final Font font = new Font(widgetStructure.getActiveTextFont(), textSize, widgetStructure.getActiveTextColor());
		final int lineCount = getLines().getElementCount();
		final int shownLineCount = contentHeight / textSize;
		
		//Iterates the lines of this console.
		int lineNumber = 1;
		for (final String l : getLines()) {
			
			int offsetLineCount = isEnabled() ? 2 : 1;
			
			if (lineNumber > lineCount - shownLineCount + offsetLineCount) {
				font.paintText(getLinePrefix() + l, getContentWidth(), graphics);
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
