//package declaration
package ch.nolix.element.widget;

//own imports
import ch.nolix.common.math.Calculator;
import ch.nolix.common.node.BaseNode;
import ch.nolix.common.node.Node;
import ch.nolix.common.validator.Validator;
import ch.nolix.element.GUI.CursorIcon;
import ch.nolix.element.base.MutableProperty;
import ch.nolix.element.input.Key;
import ch.nolix.element.painter.IPainter;

//class
/**
 * @author Silvan Wyss
 * @month 2016-03
 * @lines 350
 */
public final class TextBox extends TextLineWidget<TextBox, TextBoxLook> {

	//constants
	public static final String TYPE_NAME = "TextBox";
	public static final int MIN_WIDTH = 10;
	
	//constants
	private static final int MIN_CONTENT_AREA_WIDTH = 200;
	private static final String TEXT_CURSOR_POSITION_HEADER = "TextCursorPosition";
	
	//attribute
	private MutableProperty<Integer> textCursorPosition =
	new MutableProperty<>(
		TEXT_CURSOR_POSITION_HEADER,
		this::setTextCursorPosition,
		BaseNode::getOneAttributeAsInt,
		Node::withAttribute
	);
	
	//constructor
	/**
	 * Creates a new {@link TextBox}.
	 */
	public TextBox() {
		resetAndApplyDefaultConfiguration();
	}
	
	//constructor
	/**
	 * Creates a new {@link TextBox} with the given text.
	 * 
	 * @throws ArgumentIsNullException if the given text is null.
	 */
	public TextBox(final String text) {
		
		resetAndApplyDefaultConfiguration();
		
		setText(text);
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
	 * @return if the current {@link TextBox} has the given role.
	 */
	@Override
	public boolean hasRole(final String role) {
		return false;
	}
	
	//method
	/**
	 * Lets the current {@link TextBox} note a left mouse button press.
	 */
	@Override
	public void noteLeftMouseButtonPressOnContentAreaWhenEnabled() {		
		//Updates the text cursor position.
			final var text = getText();
			
			if (text.isEmpty()) {
				setTextCursorPosition(0);
			}
			else {
				
				final var cursorXPositionOnContentArea = getCursorXPositionOnContentArea();
				final var textFormat = getTextFormat();
				
				if (cursorXPositionOnContentArea <= textFormat.getSwingTextWidth(text.charAt(0)) / 2
				) {
					setTextCursorPosition(0);
				}
			
				else if (cursorXPositionOnContentArea >= textFormat.getSwingTextWidth(text) - textFormat.getSwingTextWidth(text.charAt(text.length() - 1))) {
					setTextCursorPosition(text.length());
				}
				
				else {
			
					for (int i = 1; i < text.length(); i++) {
						
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
	 * Resets the configuration of the current {@link TextBox}.
	 * 
	 * @return the current {@link TextBox}.
	 */
	@Override
	public TextBox resetConfiguration() {
		
		//Calls method of the base class.
		super.resetConfiguration();
		
		setProposalWidth(200);
		setCustomCursorIcon(CursorIcon.Edit);
		
		return this;
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean shortensShownTextWhenHasLimitedWidth() {
		return false;
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void applyDefaultConfigurationWhenHasBeenReset() {
		setCustomCursorIcon(CursorIcon.Edit);
		getRefBaseLook().setBorderThicknesses(1);
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
	 * @return the width of the content area of current {@link TextBox}.
	 */
	@Override
	protected final int getContentAreaWidth() {
		return
		Calculator.getMin(
			MIN_CONTENT_AREA_WIDTH,
			getTextFormat().getSwingTextWidth(getText())
		);
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void noteKeyPressWhenEnabled(final Key key) {
		
		//Calls method of the base class.
		super.noteKeyTypingWhenEnabled(key);
		
		//Handles the case that the current TextBox is focused.
		if (isFocused()) {
			
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
						if (getParentGUI().isPressed(Key.SHIFT) ^ getParentGUI().shiftIsLocked()) {
							insertCharacterAfterCursor(key.toUpperCaseChar());	
						}
						else {
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
	protected void noteLeftMouseButtonReleaseOnContentAreaWhenEnabled() {}
	
	//method
	/**
	 * Paints the content area of the current {@link TextBox} using the given text line widget look and painter.
	 * 
	 * @param textBoxLook
	 * @param painter
	 */
	@Override
	protected final void paintContentArea(
		final TextBoxLook textBoxLook,
		final IPainter painter
	) {
		
		//Calls method of the base class.
		super.paintContentArea(textBoxLook, painter);
		
		//Paints the text cursor if the current text box is focused.
		if (isFocused()) {
			
			painter.setColor(textBoxLook.getRecursiveOrDefaultTextColor());
			
			painter.paintFilledRectangle(
				getTextCursorXPositionOnContentArea(),
				0,
				getTextCursorWidth(),
				(int)(1.2 * textBoxLook.getRecursiveOrDefaultTextSize())
			);
		}
	}
	
	//method
	/**
	 * Deletes the character after the text cursor from the text of the current text box
	 * if there is a character after the text cursor.
	 */
	private final void deleteCharacterAfterTextCursor() {
		if (getTextCursorPosition() < getText().length()) {
			setText(getTextBeforeTextCursor() + getTextAfterTextCursor().substring(1));
		}
	}
	
	//method
	/**
	 * Deletes the character before the text cursor from the text of the current text box
	 * if there is a character before the text cursor.
	 */
	private final void deleteCharacterBeforeTextCursor() {
		if (!getText().isEmpty() && getTextCursorPosition() > 0) {
			
			final var textCursorPosition = getTextCursorPosition();
			
			setText(getText().substring(0, textCursorPosition - 1) + getText().substring(textCursorPosition));
			setTextCursorPosition(textCursorPosition - 1);
		}
	}
	
	//method
	/**
	 * @return the text after the text cursor of the current {@link TextBox}.
	 */
	private final String getTextAfterTextCursor() {
		return getText().substring(getTextCursorPosition());
	}
	
	//method
	/**
	 * @return the text before the text cursor of the current {@link TextBox}.
	 */
	private final String getTextBeforeTextCursor() {
		return getText().substring(0, getTextCursorPosition());
	}
	
	//method
	/**
	 * @return the text cursor position of the current {@link TextBox}.
	 */
	private final int getTextCursorPosition() {
		return textCursorPosition.getValue();
	}
	
	//method
	/**
	 * @return the x-position of the text cursor of the current {@link TextBox} on the content area.
	 */
	private final int getTextCursorXPositionOnContentArea() {
		
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
	private final void insertCharacterAfterCursor(final char character) {
		
		setText(getTextBeforeTextCursor() + character + getTextAfterTextCursor());
		
		setTextCursorPosition(getTextCursorPosition() + 1);;
	}
	
	//method
	/**
	 * Sets the text cursor position of the current {@link TextBox}.
	 * 
	 * @param textCursorPosition
	 * @throws NegativeArgumentException if the given text cursor position is negative.
	 */
	private final void setTextCursorPosition(final int textCursorPosition) {
		
		Validator
		.assertThat(textCursorPosition)
		.thatIsNamed("text cursor position")
		.isNotNegative();
		
		this.textCursorPosition.setValue(textCursorPosition);
	}
}
