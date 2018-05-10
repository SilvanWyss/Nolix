//package declaration
package ch.nolix.element.GUI;

//Java import
import java.awt.event.KeyEvent;

//own imports
import ch.nolix.core.constants.CharacterCatalogue;
import ch.nolix.core.constants.StringCatalogue;
import ch.nolix.core.container.ReadContainer;
import ch.nolix.core.enums.TextStyle;
import ch.nolix.core.container.IContainer;
import ch.nolix.core.container.List;
import ch.nolix.core.interfaces.Clearable;
import ch.nolix.core.specification.Specification;
import ch.nolix.core.specification.StandardSpecification;
import ch.nolix.element.color.Color;
import ch.nolix.element.font.Font;
import ch.nolix.element.font.TextFont;
import ch.nolix.element.painter.IPainter;
import ch.nolix.primitive.invalidStateException.UnexistingAttributeException;
import ch.nolix.primitive.validator2.Validator;

//class
/**
 * @author Silvan Wyss
 * @month 2017-03
 * @lines 680
 */
public final class Console
extends BorderWidget<Console, ConsoleLook>
implements Clearable<Console> {
	
	//type name
	public static final String TYPE_NAME = "Console";
	
	//mask character
	//The mask character is the character that is displayed in a secret line.
	public static final char MASK_CHARACTER = CharacterCatalogue.BULLET;
	
	//default value
	public static final Color DEFAULT_BACKGROUND_COLOR = Color.LIGHT_GREY;
	
	//attribute headers
	private final static String LINES_HEADER = "Lines";
	
	//attribute
	//The edit line is the line of this console that can be edited.
	private String editLine = StringCatalogue.EMPTY_STRING;
	
	//attribute
	//The text cursor position is the position of the text cursor in the edit line of this console.
	//The text cursor position is measured by the number of characters.
	private int textCursorPosition = 0;
	
	//optional attribute
	//The secret line exists if this conole is reading a secret line.
	private String secretEditLine;
	
	//multiple attribute
	private final List<String> lines = new List<String>();
	
	//constructor
	/**
	 * Creates a new console.
	 */
	public Console() {
		reset();
		approveProperties();
	}
	
	//method
	/**
	 * Adds or change the given attribute to this console.
	 * 
	 * @param attribute
	 * @throws InvalidArgumentException if the given attribute is not valid.
	 */
	public void addOrChangeAttribute(final Specification attribute) {
		
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
	 * @return the active cursor icon of the current {@link Console}.
	 */
	public CursorIcon getActiveCursorIcon() {
		return getCursorIcon();
	}
	
	//method
	/**
	 * @return the attributes of this console.
	 */
	public List<StandardSpecification> getAttributes() {
		
		//Calls method of the base class.
		final List<StandardSpecification> attributes = super.getAttributes();
		
		//Handles the case that this console contains one or several lines.
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
	public ReadContainer<Widget<?, ?>> getRefWidgets() {
		return new ReadContainer<>();
	}
	
	//method
	/**
	 * @return the lines of this console.
	 */
	public ReadContainer<String> getLines() {
		return new ReadContainer<String>(lines);
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
		
		//Handles the case that this console is reading a secret line.		
		if (isReadingSecretLine()) {
			setSecretEditLine(
				getSecretEditLineBeforeTextCursor()
				+ character
				+ getSecretEditLineAfterTextCursor()
			);
		}
		
		final int originalTextCursorPosition = textCursorPosition;
		
		final char displayedCharacter = !isReadingSecretLine() ? character : MASK_CHARACTER;
		
		setEditLine(
			getEditLineBeforeTextCursor()
			+ displayedCharacter
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
	 * Lets this console note an enter.
	 */
	public void noteEnter() {
		writeEditLine();
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
				noteEnter();
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
		
		//This loop suffers from being optimized away by the compiler of the JVM.
		while (getEditLine().isEmpty()) {
	
			//The following statement, that is actually unnecessary,
			//makes that the current loop is not optimized away.
			System.out.flush();			
			
			supposeGUIIsAlive();
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
		
		//This loop suffers from being optimized away by the compiler or the JVM.
		while (getLines().getElementCount() == lineCount) {
			supposeGUIIsAlive();
			
			//The following statement, that is actually unnecessary,
			//makes that the current loop is not optimized away.
			System.out.flush();
		}
		
		return lines.getRefLast();
	}
	
	//method
	/**
	 * Reads the next line, that is not empty, from this console.
	 * Attention: Clears the edit line of this console.
	 * Attention: Lasts until this console receives a non-empty line.
	 * 
	 * @return the next line of this console that is not empty.
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
	 * Reads the next line as secret line from this console.
	 * Attention: Clears the edit line of this console.
	 * Attention: Lasts until this console receives line.
	 * 
	 * @return the next line that is written to this console as secret line.
	 */
	public String readSecretLine() {	
		this.secretEditLine = StringCatalogue.EMPTY_STRING;	
		readLine();
		final String secretLine = this.secretEditLine;
		this.secretEditLine = null;
		return secretLine;
	}
	
	//method
	/**
	 * Resets the configuration of this console.
	 * 
	 * @return this console.
	 */
	public Console resetConfiguration() {
		
		//Calls method of the base class.
		super.resetConfiguration();
		
		setCursorIcon(CursorIcon.Edit);
		getRefBaseLook()
		.setBackgroundColor(DEFAULT_BACKGROUND_COLOR)
		.setTextFont(TextFont.Console);
		
		return this;
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
		Validator.suppose(editLine).thatIsNamed("edit line").isNotNull();
		
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
	 * @return a new widget look for this console.
	 */
	protected ConsoleLook createWidgetLook() {
		return new ConsoleLook();
	}

	//method
	/**
	 * @return the height of the content of this console.
	 */
	protected int getContentHeight() {
		
		final ConsoleLook currentStructure = getRefCurrentLook();
		
		return (
			currentStructure.getRecursiveOrDefaultHeight()
			- currentStructure.getRecursiveOrDefaultTopBorderThickness()
			- currentStructure.getRecursiveOrDefaultTopPadding()
			- currentStructure.getRecursiveOrDefaultBottomBorderThickness()
			- currentStructure.getRecursiveOrDefaultBottomPadding()
		);
	}

	//method
	/**
	 * @return the width of the content of this console.
	 */
	protected int getContentWidth() {
		
		final ConsoleLook currentStructure = getRefCurrentLook();
		
		return (
			currentStructure.getRecursiveOrDefaultWidth()
			- currentStructure.getRecursiveOrDefaultLeftBorderThickness()
			- currentStructure.getRecursiveOrDefaultLeftPadding()
			- currentStructure.getRecursiveOrDefaultRightBorderThickness()
			- currentStructure.getRecursiveOrDefaultRightPadding()
		);
	}
	
	//method
	/**
	 * Paints the content of this console using the given widget structure and graphics.
	 * 
	 * @param widgetStructure
	 * @param graphics
	 */
	protected void paintContent(final ConsoleLook widgetStructure, final IPainter graphics) {
		
		final int contentWidth = getContentWidth();
		final int contentHeight = getContentHeight();
		final int textSize = widgetStructure.getRecursiveOrDefaultTextSize();	
		
		final Font font =
		new Font(
			widgetStructure.getRecursiveOrDefaultTextFont(),
			TextStyle.Default,
			textSize,
			widgetStructure.getRecursiveOrDefaultTextColor()
		);
		
		final int lineCount = getLines().getElementCount();
		final int shownLineCount = contentHeight / textSize;
		
		//Iterates the lines of this console.
		int lineNumber = 1;
		for (final String l : getLines()) {
			
			int offsetLineCount = isEnabled() ? 2 : 1;
			
			if (lineNumber > lineCount - shownLineCount + offsetLineCount) {
				graphics.paintText(getLinePrefix() + l, font, getContentWidth());
				graphics.translate(0, textSize);
			}
			
			lineNumber++;
		}
		
		if (isEnabled()) {
		
			//Paints the edit line of this console.;
			graphics.paintText(getLinePrefix() + getEditLine(), font, contentWidth);
				
			//Paints the text cursor of this console.
				final int textCursorXPosition
				= font.getSwingTextWidth(getLinePrefix()
				+ getEditLineBeforeTextCursor())
				- 1;
				
				if (textCursorXPosition < contentWidth) {
					graphics.setColor(widgetStructure.getRecursiveOrDefaultTextColor());
					graphics.paintFilledRectangle(
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
	
	//method
	/**
	 * A console has a secret edit line when it is reading a secret line.
	 * 
	 * @return the secret edit line of this console.
	 * @throws UnexistingAttribute exception if this console has no secret edit line.
	 */
	private String getSecretEditLine() {
		
		//Checks if this console has a secret edit line.
		if (!isReadingSecretLine()) {
			throw new UnexistingAttributeException(this, "secret edit line");
		}
		
		return secretEditLine;
	}
	
	//method
	/**
	 * @return the text of the secret edit line of this console after the text cursor.
	 * @throws UnexistingAttribute exception if this console has no secret edit line.
	 */
	private String getSecretEditLineAfterTextCursor() {
		return getSecretEditLine().substring(textCursorPosition);
	}
	
	//method
	/**
	 * @return the text of the secret edit line of this console before the text cursor.
	 * @throws UnexistingAttribute exception if this console has no secret edit line.
	 */
	private String getSecretEditLineBeforeTextCursor() {
		return getSecretEditLine().substring(0, textCursorPosition);
	}
	
	//method
	/**
	 * @return true if this consoel is reading a secret line.
	 */
	private boolean isReadingSecretLine() {
		return (secretEditLine != null);
	}
	
	//method
	/**
	 * Sets the secret edit line of this console.
	 * 
	 * @param secretEditLine
	 * @throws NullArgumentException if the given secret edit line is null.
	 */
	private void setSecretEditLine(final String secretEditLine) {
		
		//Checks if the given secret edit line is not null.
		Validator
		.suppose(secretEditLine)
		.thatIsNamed("secret edit line")
		.isNotNull();
		
		this.secretEditLine = secretEditLine;
	}
}
