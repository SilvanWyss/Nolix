//package declaration
package ch.nolix.element.widget;

//Java imports
import java.awt.event.KeyEvent;

//own imports
import ch.nolix.core.container.List;
import ch.nolix.core.documentNode.DocumentNode;
import ch.nolix.core.math.Calculator;
import ch.nolix.core.validator.Validator;
import ch.nolix.element.painter.IPainter;

//class
/**
 * @author Silvan Wyss
 * @month 2016-03
 * @lines 320
 */
public final class TextBox extends TextLineWidget<TextBox> {

	//constant
	public static final String TYPE_NAME = "TextBox";
	
	//limit value
	public static final int MIN_WIDTH = 10;
	
	//constant
	private static final int MIN_CONTENT_AREA_WIDTH = 200;
	
	//attribute
	private int textCursorPosition = 0;
	
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
	 * @throws NullArgumentException if the given text is null.
	 */
	public TextBox(final String text) {
		
		resetAndApplyDefaultConfiguration();
		
		setText(text);
	}
	
	//method
	/**
	 * @return the interaction attributes of the current {@link TextBox}.
	 */
	@Override
	public List<DocumentNode> getInteractionAttributes() {
		
		//Calls method of the base class.
		final var interactionAttributes = super.getInteractionAttributes();
		
		interactionAttributes.addAtEnd(getTextSpecification());
		
		return interactionAttributes;
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
	 * Lets the current {@link TextBox} note a key typing.
	 * 
	 * @param keyEvent
	 */
	@Override
	public void noteKeyTyping(final KeyEvent keyEvent) {
		
		//Enumerates the key code of the given key event.
		switch (keyEvent.getKeyCode()) {
			case KeyEvent.VK_LEFT:
				
				if (getTextCursorPosition() > 0) {
					textCursorPosition--;
				}
				
				break;
			case KeyEvent.VK_RIGHT:
				
				if (getTextCursorPosition() < getText().length()) {
					textCursorPosition++;
				}
				
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
	 * Lets the current {@link TextBox} note a left mouse button press.
	 */
	@Override
	public void noteLeftMouseButtonPress() {
		
		//Calls method of the base class.
		super.noteLeftMouseButtonPress();
		
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
	 * Paints the content area of the current {@link TextBox} using the given text line widget look and painter.
	 * 
	 * @param textLineWidgetLook
	 * @param painter
	 */
	@Override
	protected final void paintContentArea(
		final TextLineWidgetLook textLineWidgetLook,
		final IPainter painter
	) {
		
		//Calls method of the base class.
		super.paintContentArea(textLineWidgetLook, painter);
		
		//Paints the text cursor if the current text box is focused or hover focused.
		if (isFocused() || isHoverFocused()) {
			painter.setColor(textLineWidgetLook.getRecursiveOrDefaultTextColor());
			
			painter.paintFilledRectangle(
				getTextCursorXPositionOnContentArea(),
				0,
				1,
				(int)(1.2 * textLineWidgetLook.getRecursiveOrDefaultTextSize())
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
			setText(getText().substring(0, textCursorPosition - 1) + getText().substring(textCursorPosition));
			textCursorPosition--;
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
		return textCursorPosition;
	}
	
	//method
	/**
	 * @return the x-position of the text cursor of the current {@link TextBox} on the content area.
	 */
	private final int getTextCursorXPositionOnContentArea() {
		return getTextFormat().getSwingTextWidth(getTextBeforeTextCursor());
	}
	
	//method
	/**
	 * Inserts the given character after the text cursor to the text of the current {@link TextBox}.
	 * 
	 * @param character
	 */
	private final void insertCharacterAfterCursor(final char character) {
		
		setText(getTextBeforeTextCursor() + character + getTextAfterTextCursor());
		
		textCursorPosition++;
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
		.suppose(textCursorPosition)
		.thatIsNamed("text cursor position")
		.isNotNegative();
		
		this.textCursorPosition = textCursorPosition;
	}
}
