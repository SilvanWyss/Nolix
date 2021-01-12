//package declaration
package ch.nolix.element.widget;

//own imports
import ch.nolix.common.constant.StringCatalogue;
import ch.nolix.common.invalidargumentexception.NegativeArgumentException;
import ch.nolix.common.math.Calculator;
import ch.nolix.common.node.BaseNode;
import ch.nolix.common.node.Node;
import ch.nolix.common.validator.Validator;
import ch.nolix.element.base.MutableValue;
import ch.nolix.element.gui.CursorIcon;
import ch.nolix.element.input.Key;
import ch.nolix.element.painterapi.IPainter;

//class
/**
 * @author Silvan Wyss
 * @date 2017-01-01
 * @lines 320
 */
public final class TextBox extends TextLineWidget<TextBox, TextBoxLook> {
	
	//constant
	public static final String TYPE_NAME = "TextBox";
	public static final String DEFAULT_TEXT = StringCatalogue.EMPTY_STRING;
	
	//constants
	private static final int DEFAULT_CURSOR_POSITION = 0;
	private static final String TEXT_CURSOR_POSITION_HEADER = "TextCursorPosition";
	
	//attribute
	private MutableValue<Integer> textCursorPosition =
	new MutableValue<>(
		TEXT_CURSOR_POSITION_HEADER,
		DEFAULT_CURSOR_POSITION,
		this::setTextCursorPosition,
		BaseNode::getOneAttributeAsInt,
		Node::withAttribute
	);
	
	//constructor
	/**
	 * Creates a new {@link TextBox}.
	 */
	public TextBox() {
		setCustomCursorIcon(CursorIcon.EDIT);
		getRefBaseLook().setBorderThicknesses(1);
	}
	
	//method
	/**
	 * @return the width of the text cursor of the current {@link TextBox}.
	 */
	public int getTextCursorWidth() {
		return Calculator.getMax(1, (int)(0.08 * getRefLook().getRecursiveOrDefaultTextSize()));
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
		
		final var text = getText();
		
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
	protected int getNaturalContentAreaWidth() {
		return getTextFormat().getSwingTextWidth(getText());
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void noteKeyPressOnContentAreaWhenFocused(final Key key) {
		
		//Enumerates the given key.
		switch (key) {
			case ARROW_LEFT:
				
				if (getTextCursorPosition() > 0) {
					setTextCursorPosition(getTextCursorPosition() - 1);
				}
				
				break;
			case ARROW_RIGHT:
				
				if (getTextCursorPosition() < getText().length()) {
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
					if (getRefKeyBoard().keyIsPressed(Key.SHIFT) ^ getRefKeyBoard().shiftIsLocked()) {
						insertCharacterAfterCursor(key.toUpperCaseChar());	
					} else {
						insertCharacterAfterCursor(key.toLowerCaseChar());
					}
				}
		}
	}
	
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
	protected void resetBorderWidgetConfigurationOnSelf() {
		setProposalWidth(200);
		setCustomCursorIcon(CursorIcon.EDIT);
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void resetBorderWidget() {
		setText(DEFAULT_TEXT);
		textCursorPosition.setValue(0);
	}
	
	//method
	/**
	 * Deletes the character after the text cursor from the text of the current text box
	 * if there is a character after the text cursor.
	 */
	private void deleteCharacterAfterTextCursor() {
		if (getTextCursorPosition() < getText().length()) {
			setText(getTextBeforeTextCursor() + getTextAfterTextCursor().substring(1));
		}
	}
	
	//method
	/**
	 * Deletes the character before the text cursor from the text of the current text box
	 * if there is a character before the text cursor.
	 */
	private void deleteCharacterBeforeTextCursor() {
		if (!getText().isEmpty() && getTextCursorPosition() > 0) {
			
			final var lTextCursorPosition = getTextCursorPosition();
			
			setText(getText().substring(0, lTextCursorPosition - 1) + getText().substring(lTextCursorPosition));
			setTextCursorPosition(lTextCursorPosition - 1);
		}
	}
	
	//method
	/**
	 * @return the text after the text cursor of the current {@link TextBox}.
	 */
	private String getTextAfterTextCursor() {
		return getText().substring(getTextCursorPosition());
	}
	
	//method
	/**
	 * @return the text before the text cursor of the current {@link TextBox}.
	 */
	private String getTextBeforeTextCursor() {
		return getText().substring(0, getTextCursorPosition());
	}
	
	//method
	/**
	 * @return the text cursor position of the current {@link TextBox}.
	 */
	private int getTextCursorPosition() {
		return textCursorPosition.getValue();
	}
	
	//method
	/**
	 * @return the x-position of the text cursor of the current {@link TextBox} on the content area.
	 */
	private int getTextCursorXPositionOnContentArea() {
		
		if (getTextBeforeTextCursor().isEmpty()) {
			return 2;
		}
		
		return getTextFormat().getSwingTextWidth(getTextBeforeTextCursor()) - getTextCursorWidth() / 2;
	}
	
	//method
	/**
	 * Inserts the given character after the text cursor to the text of the current {@link TextBox}.
	 * 
	 * @param character
	 */
	private void insertCharacterAfterCursor(final char character) {
		
		setText(getTextBeforeTextCursor() + character + getTextAfterTextCursor());
		
		setTextCursorPosition(getTextCursorPosition() + 1);
	}
	
	//method
	/**
	 * Paints the text cursor of the current {@link TextBox} using the given painter and textBoxLook.
	 * @param painter
	 * @param textBoxLook
	 */
	private void paintTextCursor(IPainter painter, final TextBoxLook textBoxLook) {
		painter.setColor(textBoxLook.getRecursiveOrDefaultTextColor());
		
		painter.paintFilledRectangle(
			getTextCursorXPositionOnContentArea(),
			0,
			getTextCursorWidth(),
			(int)(1.2 * textBoxLook.getRecursiveOrDefaultTextSize())
		);
	}
	
	//method
	/**
	 * Sets the text cursor position of the current {@link TextBox}.
	 * 
	 * @param textCursorPosition
	 * @throws NegativeArgumentException if the given text cursor position is negative.
	 */
	private void setTextCursorPosition(final int textCursorPosition) {
		
		Validator
		.assertThat(textCursorPosition)
		.thatIsNamed("text cursor position")
		.isNotNegative();
		
		this.textCursorPosition.setValue(textCursorPosition);
	}
}
