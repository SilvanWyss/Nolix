//package declaration
package ch.nolix.system.gui.textbox;

import ch.nolix.core.commontype.constant.CharacterCatalogue;
import ch.nolix.core.commontype.constant.StringCatalogue;
import ch.nolix.core.document.node.Node;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.NegativeArgumentException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.math.GlobalCalculator;
import ch.nolix.coreapi.documentapi.nodeapi.INode;
import ch.nolix.system.element.MutableValue;
import ch.nolix.system.gui.widget.BorderWidget;
import ch.nolix.system.gui.widget.TextBoxLook;
import ch.nolix.system.gui.widget.TextLineWidget;
import ch.nolix.system.gui.widget.TextMode;
import ch.nolix.systemapi.guiapi.inputapi.Key;
import ch.nolix.systemapi.guiapi.mainapi.CursorIcon;
import ch.nolix.systemapi.guiapi.painterapi.IPainter;

//class
/**
 * @author Silvan Wyss
 * @date 2017-01-01
 */
final class TextBoxInputField extends TextLineWidget<TextBoxInputField, TextBoxLook> {
	
	//constants
	public static final String DEFAULT_TEXT = StringCatalogue.EMPTY_STRING;
	public static final TextMode DEFAULT_TEXT_MODE = TextMode.NORMAL;
	public static final int DEFAULT_CURSOR_POSITION = 0;
	public static final int TEXT_CURSOR_WIDTH = 2;
	
	//constant
	private static final String TEXT_MODE_HEADER = "TextMode";
	private static final String TEXT_CURSOR_POSITION_HEADER = "TextCursorPosition";
	
	//attribute
	private MutableValue<TextMode> textMode =
	new MutableValue<>(
		TEXT_MODE_HEADER,
		DEFAULT_TEXT_MODE,
		this::setTextMode,
		TextMode::fromSpecification,
		Node::fromEnum
	);
	
	//attribute
	private MutableValue<Integer> textCursorPosition =
	new MutableValue<>(
		TEXT_CURSOR_POSITION_HEADER,
		DEFAULT_CURSOR_POSITION,
		this::setTextCursorPosition,
		INode::getSingleChildNodeAsInt,
		Node::withChildNode
	);
	
	//constructor
	/**
	 * Creates a new {@link TextBoxInputField}.
	 */
	public TextBoxInputField() {
		
		reset();
		
		setCustomCursorIcon(CursorIcon.EDIT);
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getShownText() {
		
		//Enumerates the text mode of the current TextBox.
		switch (getTextMode()) {
			case NORMAL:
				return getShownTextForNormalTextMode();
			case SECRET:
				return getShownTextForSecretTextMode();
			default:
				throw InvalidArgumentException.forArgument(getTextMode());
		}
	}
	
	//method
	/**
	 * @return the width of the text cursor of the current {@link TextBoxInputField}.
	 */
	public int getTextCursorWidth() {
		return GlobalCalculator.getMax(1, (int)(0.08 * getRefActiveLook().getTextSize()));
	}
	
	//method
	/**
	 * @return the text mode of the current {@link TextBoxInputField}.
	 */
	public TextMode getTextMode() {
		return textMode.getValue();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean hasRole(final String role) {
		return false;
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void noteLeftMouseButtonPressOnContentAreaWhenEnabled() {		
		
		final var text = getShownText();
		
		if (text.isEmpty()) {
			setTextCursorPosition(0);
		} else {
			
			final var cursorXPositionOnContentArea = getCursorXPositionOnContentArea();
			final var textFormat = getTextFormat();
			
			if (cursorXPositionOnContentArea <= textFormat.getSwingTextWidth(text.charAt(0)) / 2) {
				setTextCursorPosition(0);
			} else if (
				cursorXPositionOnContentArea >=
				textFormat.getSwingTextWidth(text) - textFormat.getSwingTextWidth(text.charAt(text.length() - 1))
			) {
				setTextCursorPosition(text.length());
			} else {
		
				for (var i = 1; i < text.length(); i++) {
					
					final var subTextWidth = textFormat.getSwingTextWidth(text.substring(0, i));
					final var previousCharacterWidth = textFormat.getSwingTextWidth(text.charAt(i - 1));
					final var nextCharacterWidth = textFormat.getSwingTextWidth(text.charAt(i));
					
					if (
						subTextWidth - previousCharacterWidth / 2 <= cursorXPositionOnContentArea
						&& subTextWidth + nextCharacterWidth / 2 >= cursorXPositionOnContentArea
					) {
						setTextCursorPosition(i);
					}
				}
			}
		}
	}
	
	//method
	/**
	 * Sets the text mode of the current {@link TextBoxInputField}.
	 * 
	 * @param textMode
	 * @return the current {@link TextBoxInputField}.
	 * @throws ArgumentIsNullException if the given textMode is null.
	 */
	public TextBoxInputField setTextMode(final TextMode textMode) {
		
		this.textMode.setValue(textMode);
		
		return this;
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected TextBoxLook createLook() {
		return new TextBoxLook();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected String getDefaultText() {
		return DEFAULT_TEXT;
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected int getTextWidthAddition() {
		return 2*TEXT_CURSOR_WIDTH;
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void noteKeyDownOnSelfWhenFocused(final Key key) {
		
		//Enumerates the given key.
		switch (key) {
			case ARROW_LEFT:
				
				if (getTextCursorPosition() > 0) {
					setTextCursorPosition(getTextCursorPosition() - 1);
				}
				
				break;
			case ARROW_RIGHT:
				
				if (getTextCursorPosition() < getShownText().length()) {
					setTextCursorPosition(getTextCursorPosition() + 1);
				}
				
				break;
			case BACKSPACE:				
				deleteCharacterBeforeTextCursor();
				break;
			case DELETE:				
				deleteCharacterAfterTextCursor();
				break;
			default:
				if (key.isCharacter()) {
					noteCharacterKeyDownOnSelfWhenFocused(key);
				}
		}
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void noteLeftMouseButtonReleaseOnContentAreaWhenEnabled() {
		//Does nothing.
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void paintTextLineWidgetContentArea(final IPainter painter, final TextBoxLook textBoxLook) {
		if (isFocused()) {
			paintTextCursor(painter, textBoxLook);
		}
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void resetBorderWidgetConfiguration() {
		setProposalWidth(200);
		setCustomCursorIcon(CursorIcon.EDIT);
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void resetTextLineWidget() {
		textCursorPosition.setValue(0);
	}
	
	//method
	/**
	 * Deletes the character after the text cursor from the text of the current text box
	 * if there is a character after the text cursor.
	 */
	private void deleteCharacterAfterTextCursor() {
		if (getTextCursorPosition() < getShownText().length()) {
			setText(getTextBeforeTextCursor() + getTextAfterTextCursor().substring(1));
		}
	}
	
	//method
	/**
	 * Deletes the character before the text cursor from the text of the current text box
	 * if there is a character before the text cursor.
	 */
	private void deleteCharacterBeforeTextCursor() {
		if (!getShownText().isEmpty() && getTextCursorPosition() > 0) {
			
			final var lTextCursorPosition = getTextCursorPosition();
			
			setText(getText().substring(0, lTextCursorPosition - 1) + getText().substring(lTextCursorPosition));
			setTextCursorPosition(lTextCursorPosition - 1);
		}
	}
	
	//method
	/**
	 * @return the shown text before the text cursor of the current {@link TextBoxInputField}.
	 */
	private String getShownTextBeforeTextCursor() {
		return getShownText().substring(0, getTextCursorPosition());
	}
	
	//method
	/**
	 * @return the shown text of the current {@link TextBoxInputField} for {@link TextMode#NORMAL}.
	 */
	private String getShownTextForNormalTextMode() {
		return getText();
	}
	
	//method
	/**
	 * @return the shown text of the current {@link TextBoxInputField} for {@link TextMode#SECRET}.
	 */
	private String getShownTextForSecretTextMode() {
		final var stringBuilder = new StringBuilder();
		
		final var text = getText();
		for (var i = 0; i < text.length(); i++) {
			stringBuilder.append(CharacterCatalogue.BULLET);
		}
		
		return stringBuilder.toString();
	}

	//method
	/**
	 * @return the text after the text cursor of the current {@link TextBoxInputField}.
	 */
	private String getTextAfterTextCursor() {
		return getText().substring(getTextCursorPosition());
	}
	
	//method
	/**
	 * @return the text before the text cursor of the current {@link TextBoxInputField}.
	 */
	private String getTextBeforeTextCursor() {
		return getText().substring(0, getTextCursorPosition());
	}
	
	//method
	/**
	 * @return the text cursor position of the current {@link TextBoxInputField}.
	 */
	private int getTextCursorPosition() {
		return textCursorPosition.getValue();
	}
	
	//method
	/**
	 * @return the x-position of the text cursor of the current {@link TextBoxInputField} on the content area.
	 */
	private int getTextCursorXPositionOnContentArea() {
		
		if (getShownTextBeforeTextCursor().isEmpty()) {
			return 2;
		}
		
		return getTextFormat().getSwingTextWidth(getShownTextBeforeTextCursor()) - getTextCursorWidth() / 2;
	}
	
	//method
	/**
	 * Inserts the given character after the text cursor to the text of the current {@link TextBoxInputField}.
	 * 
	 * @param character
	 */
	private void insertCharacterAfterCursor(final char character) {
		
		setText(getTextBeforeTextCursor() + character + getTextAfterTextCursor());
		
		setTextCursorPosition(getTextCursorPosition() + 1);
	}
	
	//method
	/**
	 * Lets the current {@link TextBoxInputField} note a key press for the case when
	 * the current {@link BorderWidget} is focused and the given key is a character.
	 * 
	 * @param characterKey
	 */
	private void noteCharacterKeyDownOnSelfWhenFocused(final Key characterKey) {
		if (getRefKeyBoard().keyIsPressed(Key.CONTROL) && characterKey == Key.V) {
			final var clipboardText = fromFrontEnd().getTextFromClipboard();
			for (var i = 0; i < clipboardText.length(); i++) {
				insertCharacterAfterCursor(clipboardText.charAt(i));
			}
		} else if (getRefKeyBoard().keyIsPressed(Key.SHIFT) ^ getRefKeyBoard().shiftIsLocked()) {
			insertCharacterAfterCursor(characterKey.toUpperCaseChar());	
		} else {
			insertCharacterAfterCursor(characterKey.toLowerCaseChar());
		}
	}
	
	//method
	/**
	 * Paints the text cursor of the current {@link TextBoxInputField} using the given painter and textBoxLook.
	 * @param painter
	 * @param textBoxLook
	 */
	private void paintTextCursor(IPainter painter, final TextBoxLook textBoxLook) {
		
		painter.setColor(textBoxLook.getTextColor());
		
		painter.paintFilledRectangle(
			getTextCursorXPositionOnContentArea(),
			0,
			getTextCursorWidth(),
			(int)(1.2 * textBoxLook.getTextSize())
		);
	}
	
	//method
	/**
	 * Sets the text cursor position of the current {@link TextBoxInputField}.
	 * 
	 * @param textCursorPosition
	 * @throws NegativeArgumentException if the given text cursor position is negative.
	 */
	private void setTextCursorPosition(final int textCursorPosition) {
		
		GlobalValidator.assertThat(textCursorPosition).thatIsNamed("text cursor position").isNotNegative();
		
		this.textCursorPosition.setValue(textCursorPosition);
	}
	
	//method
	@Override
	protected void setTextWhenHasOtherText(final String text) {
		
		super.setTextWhenHasOtherText(text);
		
		if (textCursorPosition.getValue() > text.length()) {
			textCursorPosition.setValue(text.length());
		}
	}
}
