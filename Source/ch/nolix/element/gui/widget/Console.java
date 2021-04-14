//package declaration
package ch.nolix.element.gui.widget;

//own imports
import ch.nolix.common.constant.CharacterCatalogue;
import ch.nolix.common.constant.StringCatalogue;
import ch.nolix.common.container.IContainer;
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.container.ReadContainer;
import ch.nolix.common.document.node.BaseNode;
import ch.nolix.common.document.node.Node;
import ch.nolix.common.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.common.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.common.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.common.errorcontrol.validator.Validator;
import ch.nolix.common.skillapi.Clearable;
import ch.nolix.element.elementenum.RotationDirection;
import ch.nolix.element.gui.base.CursorIcon;
import ch.nolix.element.gui.base.Widget;
import ch.nolix.element.gui.color.Color;
import ch.nolix.element.gui.input.Key;
import ch.nolix.element.gui.painterapi.IPainter;
import ch.nolix.element.gui.textformat.Font;
import ch.nolix.element.gui.textformat.TextFormat;

//class
/**
 * @author Silvan Wyss
 * @date 2017-03-06
 * @lines 870
 */
public final class Console extends BorderWidget<Console, OldConsoleLook> implements Clearable {
	
	//constants
	public static final String TYPE_NAME = "Console";
	public static final Color DEFAULT_BACKGROUND_COLOR = Color.LIGHT_GREY;
	
	//constant
	/**
	 * The line prefix is displayed at the begin of each line.
	 */
	public static final String LINE_PREFIX = "> ";
	
	//constant
	/**
	 * The mask character is the character that is displayed in a secret line.
	 */
	public static final char MASK_CHARACTER = CharacterCatalogue.BULLET;
		
	//constants
	private static final String LINES_HEADER = "Lines";
	private static final String EDIT_LINE_HEADER = "EditLine";
	
	//attribute
	private boolean editable = true;
	
	//attribute
	/**
	 * The edit line is the line of the current {@link Console} that can be edited.
	 */
	private String editLine = StringCatalogue.EMPTY_STRING;
	
	//attribute
	/**
	 * The text cursor position is the position of the text cursor in the edit line of the current {@link Console}.
	 * The text cursor position is measured by the number of characters.
	 */
	private int textCursorPosition;
	
	//optional attribute
	/**
	 * The secret line exists if this conole is reading a secret line.
	 */
	private String secretEditLine;
	
	//multi-attribute
	private final LinkedList<String> lines = new LinkedList<>();
	
	//constructor
	/**
	 * Creates a new console.
	 */
	public Console() {
		setProposalSize(200, 100);
		getRefBaseLook().setPaddings(10);
	}
	
	//method
	/**
	 * Adds or change the given attribute to the current {@link Console}.
	 * 
	 * @param attribute
	 * @throws InvalidArgumentException if the given attribute is not valid.
	 */
	@Override
	public void addOrChangeAttribute(final BaseNode attribute) {
		
		//Enumerates the header of the given attribute.
		switch (attribute.getHeader()) {
			case LINES_HEADER:
				attribute.getRefAttributes().forEach(a -> writeLine(a.getHeaderOrEmptyString()));
				break;
			case EDIT_LINE_HEADER:
				setEditLine(attribute.getRefOneAttribute().getHeaderOrEmptyString());
				break;
			default:
				
				//Calls method of the base class.
				super.addOrChangeAttribute(attribute);
		}
	}
	
	//method
	/**
	 * Removes all lines of the current {@link Console} and clears the edit line of the current {@link Console}.
	 */
	@Override
	public void clear() {
		lines.clear();
		clearEditLine();
	}
	
	//method
	/**
	 * Clears the edit line of the current {@link Console}.
	 */
	public void clearEditLine() {
		editLine = StringCatalogue.EMPTY_STRING;
		textCursorPosition = 0;
	}
	
	//method
	/**
	 * Deletes the character after the text cursor
	 * in the edit line of the current {@link Console} if there is one.
	 */
	public void deleteCharacterAfterTextCursor() {
		if (textCursorPosition < editLine.length()) {
			editLine
			= getEditLineBeforeTextCursor() + getEditLineAfterTextCursor().substring(1);
		}
	}
	
	//method
	/**
	 * Deletes the character before the text cursor
	 * in the edit line of the current {@link Console} if there is one.
	 */
	public void deleteCharacterBeforeTextCursor() {
		if (!editLine.isEmpty() && textCursorPosition > 0) {
			
			editLine
			= editLine.substring(0, textCursorPosition - 1) + getEditLineAfterTextCursor();
			
			textCursorPosition--;
		}
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void fillUpAttributesInto(final LinkedList<Node> list) {
		
		//Calls method of base class.
		super.fillUpAttributesInto(list);
		
		//Handles the case that the current Console contains one or several lines.
		if (containsAny()) {
			
			final var linesSpecification = new Node();
			linesSpecification.setHeader(LINES_HEADER);
			
			for (final var l : lines) {
				
				final var lineAttribute = new Node();
				if (!l.isEmpty()) {
					lineAttribute.setHeader(l);
				}
				
				linesSpecification.addAttribute(lineAttribute);
			}
			
			list.addAtEnd(linesSpecification);
		}
		
		final var editLineSpecification = new Node().setHeader(EDIT_LINE_HEADER);
		if (!editLine.isEmpty()) {
			editLineSpecification.addAttribute(Node.withHeader(editLine));
		}
		
		list.addAtEnd(editLineSpecification);
	}
	
	//method
	/**
	 * The edit line of a console is its next line that can be edited.
	 * 
	 * @return the edit line of the current {@link Console}.
	 */
	public String getEditLine() {
		return editLine;
	}
	
	//method
	/**
	 * @return the text of the edit line of the current {@link Console} after the text cursor.
	 */
	public String getEditLineAfterTextCursor() {
		return editLine.substring(textCursorPosition);
	}
	
	//method
	/**
	 * @return the text of the edit line of the current {@link Console} before the text cursor.
	 */
	public String getEditLineBeforeTextCursor() {
		return editLine.substring(0, textCursorPosition);
	}
	
	//method
	/**
	 * @return the lines of the current {@link Console}.
	 */
	public ReadContainer<String> getLines() {
		return ReadContainer.forIterable(lines);
	}
	
	//method
	/**
	 * @return true if the current {@link Console} is editable.
	 */
	public boolean isEditable() {
		return editable;
	}
	
	//method
	/**
	 * @return true if the current {@link Console} does not contain a line.
	 */
	@Override
	public boolean isEmpty() {
		return getLines().isEmpty();
	}
	
	//method
	/**
	 * @return true if the current {@link Console} has the given role.
	 */
	@Override
	public boolean hasRole(final String role) {
		return false;
	}
		
	//method
	/**
	 * Inserts the given character into the edit line of the current {@link Console} after the text cursor.
	 * 
	 * @param character
	 */
	public void insertCharacterAfterCursor(final char character) {
		
		//Handles the case that the current {@link Console} is reading a secret line.		
		if (isReadingSecretLine()) {
			setSecretEditLine(
				getSecretEditLineBeforeTextCursor()
				+ character
				+ getSecretEditLineAfterTextCursor()
			);
		}
		
		final int originalTextCursorPosition = textCursorPosition;
		
		final char displayedCharacter;
		if (!isReadingSecretLine()) {
			displayedCharacter = character;
		} else {
			displayedCharacter = MASK_CHARACTER;
		}
		
		setEditLine(
			getEditLineBeforeTextCursor()
			+ displayedCharacter
			+ getEditLineAfterTextCursor()
		);
			
		textCursorPosition = originalTextCursorPosition + 1;
	}
	
	
	
	//method
	/**
	 * Moves the text cursor position in the edit line of the current {@link Console} one character to the left if possible.
	 */
	public void moveTextCursorPositionToLeft() {
		if (textCursorPosition > 0) {
			textCursorPosition--;
		}
	}
	
	//method
	/**
	 * Moves the text cursor position in the edit line of the current {@link Console} one character to the right if possible.
	 */
	public void moveTextCursorPositionToRight() {
		if (textCursorPosition < getEditLine().length()) {
			textCursorPosition++;
		}
	}
	
	//method
	/**
	 * Lets the current {@link Console} note an enter.
	 */
	public void noteEnter() {
		writeEditLine();
	}
	
	//method
	/**
	 * Reads the next character from the current {@link Console}. 
	 * Attention: Clears the edit line of the current {@link Console}.
	 * Attention: Lasts until the current {@link Console} receives a character.
	 * 
	 * @return the next character that is written to the current {@link Console}.
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
	 * Reads the next enter from the current {@link Console}.
	 * Attention: Clears the edit line of the current {@link Console}.
	 * Attention: Lasts until the current {@link Console} receives an enter.
	 */
	public void readEnter() {
		readLine();
	}
	
	//method
	/**
	 * Reads the next line from the current {@link Console}.
	 * Attention: Clears the edit line of the current {@link Console}.
	 * Attention: Lasts until the current {@link Console} receives a line.
	 * 
	 * @return the next line of the current {@link Console}.
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
	 * Reads the next line, that is not empty, from the current {@link Console}.
	 * Attention: Clears the edit line of the current {@link Console}.
	 * Attention: Lasts until the current {@link Console} receives a non-empty line.
	 * 
	 * @return the next line of the current {@link Console} that is not empty.
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
	 * Reads the next line as secret line from the current {@link Console}.
	 * Attention: Clears the edit line of the current {@link Console}.
	 * Attention: Lasts until the current {@link Console} receives line.
	 * 
	 * @return the next line that is written to the current {@link Console} as secret line.
	 */
	public String readSecretLine() {
		this.secretEditLine = StringCatalogue.EMPTY_STRING;
		readLine();
		final String secretLine = this.secretEditLine;
		this.secretEditLine = null;
		return secretLine;
	}
	
	//methods
	/**
	 * Sets the current {@link Console} editable.
	 * 
	 * @return the current {@link Console}.
	 */
	public Console setEditable() {
		
		editable = true;
		
		return this;
	}
	
	//method
	/**
	 * Sets the edit line of the current {@link Console}.
	 * 
	 * @param editLine
	 * @return the current {@link Console}
	 * @throws ArgumentIsNullException if the given edit line is null.
	 */
	public Console setEditLine(final String editLine) {
		
		//Asserts that the given edit line is not null.
		Validator.assertThat(editLine).thatIsNamed("edit line").isNotNull();
		
		//Sets the edit line of the current Console.
		this.editLine = editLine;
		
		//Sets the text cursor position at the end of the edit line.
		textCursorPosition = getEditLine().length();
		
		return this;
	}
	
	//methods
	/**
	 * Sets the current {@link Console} uneditable.
	 * 
	 * @return the current {@link Console}.
	 */
	public Console setUneditable() {
		
		editable = false;
		
		return this;
	}
	
	//method
	/**
	 * Writes the edit line of the current {@link Console} to the current {@link Console}.
	 * Attention: Clears the edit line of the current {@link Console}.
	 * 
	 * @return the current {@link Console}.
	 */
	public Console writeEditLine() {
		return writeLine(editLine);
	}
	
	//method
	/**
	 * Writes an empty line to the current {@link Console}.
	 * Attention: Clears the edit line of the current {@link Console}.
	 * 
	 * @return the current {@link Console}.
	 */
	public Console writeEmptyLine() {
		return writeLine(StringCatalogue.EMPTY_STRING);
	}
	
	//method
	/**
	 * Writes a line to the current {@link Console} that consists of the given character.
	 * Attention: Clears the edit line of the current {@link Console}.
	 * 
	 * @param character
	 * @return the current {@link Console}.
	 */
	public Console writeLine(final char character) {
		return writeLine(Character.toString(character));
	}
	
	//method
	/**
	 * Writes the given line to the current {@link Console}.
	 * Attention: Clears the edit line of the current {@link Console}.
	 * 
	 * @param line
	 * @return the current {@link Console}.
	 * @throws ArgumentIsNullException if the given line is null.
	 */
	public Console writeLine(final String line) {
		
		lines.addAtEnd(line);
		clearEditLine();
		scrollToBottom();
		
		return this;
	}
	
	//method
	/**
	 * Writes the given lines to the current {@link Console}.
	 * Attention: Clears the edit line of the current {@link Console}.
	 * 
	 * @param lines
	 * @return the current {@link Console}.
	 * @throws ArgumentIsNullException if one of the given lines is null.
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
	 * Writes the given lines to the current {@link Console}.
	 * Attention: Clears the edit line of the current {@link Console}.
	 * 
	 * @param lines
	 * @return the current {@link Console}.
	 * @throws ArgumentIsNullException if one of the given lines is null.
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
	 * {@inheritDoc}
	 */
	@Override
	protected boolean contentAreaMustBeExpandedToTargetSize() {
		return false;
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected ConsoleLook createLook() {
		return new ConsoleLook();
	}
	
	//method
	/**
	 * @return a new widget look for the current {@link Console}.
	 */
	@Override
	protected OldConsoleLook createOldLook() {
		return new OldConsoleLook();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void fillUpChildWidgets(final LinkedList<Widget<?, ?>> list) {}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void fillUpShownWidgets(final LinkedList<Widget<?, ?>> list) {}
	
	//method
	/**
	 * @return the height of the content of the current {@link Console}.
	 */
	@Override
	protected int getNaturalContentAreaHeight() {
		
		final var look = getRefOldLook();
		
		var contentAreaHeight =
		getLines().getElementCount() * look.getRecursiveOrDefaultTextSize();
		
		if (isEditable()) {
			contentAreaHeight += look.getRecursiveOrDefaultTextSize();
		}
		
		contentAreaHeight += 0.5 * look.getRecursiveOrDefaultTextSize();
		
		return contentAreaHeight;
	}

	//method
	/**
	 * @return the width of the content of the current {@link Console}.
	 */
	@Override
	protected int getNaturalContentAreaWidth() {
		
		final var font = getFont();
		
		if (isEmpty()) {
			return font.getSwingTextWidth(LINE_PREFIX);
		}
		
		return font.getSwingTextWidth(LINE_PREFIX) + getLines().getMax(font::getSwingTextWidth);
	}
	
	//method
	/**
	 * Paints the content of the current {@link Console} using the given widget structure and graphics.
	 * 
	 * @param widgetStructure
	 * @param painter
	 */
	@Override
	protected void paintContentArea(
		final OldConsoleLook widgetStructure,
		final IPainter painter
	) {
		
		final var textSize = widgetStructure.getRecursiveOrDefaultTextSize();
		final var font = getFont();
		
		//Iterates the lines of the current Console.
		for (final var l : getLines()) {
			painter.paintText(LINE_PREFIX + l, font);
			painter.translate(0, textSize);
		}
		
		if (isEditable()) {
			
			//Paints the edit line of the current Console.
			painter.paintText(LINE_PREFIX + getEditLine(), font);
			
			//Paints the text cursor of the current Console.
				final var textCursorXPosition =
				font.getSwingTextWidth(LINE_PREFIX + getEditLineBeforeTextCursor()) - 1;
				
				painter.setColor(widgetStructure.getRecursiveOrDefaultTextColor());
				painter.paintFilledRectangle(
					textCursorXPosition,
					0,
					2,
					font.getTextSize()
				);
		}
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void noteKeyPressOnSelfWhenFocused(final Key key) {
				
		//Handles the case that the current Console is focues.
		if (isFocused()) {
		
			//Enumerates the given key.
			switch (key) {
				case SPACE:
					insertCharacterAfterCursor(CharacterCatalogue.SPACE);
					break;
				case ENTER:
					noteEnter();
					break;
				case ARROW_LEFT:
					moveTextCursorPositionToLeft();
					break;
				case ARROW_RIGHT:
					moveTextCursorPositionToRight();
					break;
				case BACKSPACE:
					deleteCharacterBeforeTextCursor();
					break;
				case DELETE:
					deleteCharacterAfterTextCursor();
					break;
				default:
					if (key.isCharacter()) {
						if (getRefKeyBoard().keyIsPressed(Key.SHIFT) ^ getRefKeyBoard().shiftIsLocked()) {
							insertCharacterAfterCursor(key.toUpperCaseChar());	
						} else {
							insertCharacterAfterCursor(key.toLowerCaseChar());
						}
					}
			}
		}
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void noteKeyReleaseOnSelfWhenFocused(final Key key) {}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void noteKeyTypingOnSelfWhenFocused(final Key key) {}
		
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void noteLeftMouseButtonClickOnContentAreaWhenEnabled() {}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void noteLeftMouseButtonPressOnContentAreaWhenEnabled() {}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void noteLeftMouseButtonReleaseOnContentAreaWhenEnabled() {}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void noteMouseMoveOnContentAreaWhenEnabled() {}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void noteMouseWheelClickOnContentAreaWhenEnabled() {}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void noteMouseWheelPressOnContentAreaWhenEnabled() {}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void noteMouseWheelReleaseOnContentAreaWhenEnabled() {}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void noteMouseWheelRotationStepOnBorderWidgetWhenFocused(final RotationDirection rotationDirection) {}
		
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void noteRightMouseButtonClickOnContentAreaWhenEnabled() {}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void noteRightMouseButtonPressOnContentAreaWhenEnabled() {}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void noteRightMouseButtonReleaseOnContentAreaWhenEnabled() {}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void recalculateBorderWidget() {}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void resetBorderWidgetConfigurationOnSelf() {
		setCustomCursorIcon(CursorIcon.EDIT);
		getRefBaseLook().setTextFont(Font.LUCIDA_CONSOLE);
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void resetBorderWidget() {}
	
	//method
	private TextFormat getFont() {
		
		final var look = getRefOldLook();
		
		return
		new TextFormat(
			look.getRecursiveOrDefaultTextFont(),
			look.getRecursiveOrDefaultTextSize(),
			look.getRecursiveOrDefaultTextColor()
		);
	}
	
	//method
	/**
	 * A console has a secret edit line when it is reading a secret line.
	 * 
	 * @return the secret edit line of the current {@link Console}.
	 * @throws ArgumentDoesNotHaveAttributeException if the current {@link Console} does not have a secret edit line.
	 */
	private String getSecretEditLine() {
		
		//Asserts that the current Console has a secret edit line.
		if (!isReadingSecretLine()) {
			throw new ArgumentDoesNotHaveAttributeException(this, "secret edit line");
		}
		
		return secretEditLine;
	}
	
	//method
	/**
	 * @return the text of the secret edit line of the current {@link Console} after the text cursor.
	 * @throws ArgumentDoesNotHaveAttributeException if the current {@link Console} does not have a secret edit line.
	 */
	private String getSecretEditLineAfterTextCursor() {
		return getSecretEditLine().substring(textCursorPosition);
	}
	
	//method
	/**
	 * @return the text of the secret edit line of the current {@link Console} before the text cursor.
	 * @throws ArgumentDoesNotHaveAttributeException if the current {@link Console} does not have a secret edit line.
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
	 * Sets the secret edit line of the current {@link Console}.
	 * 
	 * @param secretEditLine
	 * @throws ArgumentIsNullException if the given secret edit line is null.
	 */
	private void setSecretEditLine(final String secretEditLine) {
		
		//Asserts that the given secret edit line is not null.
		Validator
		.assertThat(secretEditLine)
		.thatIsNamed("secret edit line")
		.isNotNull();
		
		this.secretEditLine = secretEditLine;
	}
}
