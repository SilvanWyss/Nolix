/*
 * file:	TextBox.java
 * author:	Silvan Wyss
 * month:	2016-03
 * lines:	330
 */

//package declaration
package ch.nolix.element.dialog;

//Java imports
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;










//own imports
import ch.nolix.common.container.List;
import ch.nolix.common.specification.Specification;
import ch.nolix.common.util.Validator;
import ch.nolix.element.basic.Color;
import ch.nolix.element.data.Width;

//class
public final class TextBox extends TextLineRectangle<TextBox> {

	//constant
	public final static String SIMPLE_CLASS_NAME = "TextBox";
	
	//default values
	public final static int DEFAULT_WIDTH = 200;
	public final static String DEFAULT_BACKGROUND_COLOR = Color.WHITE_STRING;
	public final static String DEFAULT_CURSOR_COLOR = Color.BLACK_STRING;
	
	//limit value
	public final static int MIN_WIDTH = 10;
	
	//attribute header
	private final static String CURSOR_COLOR = "CursorColor";
	
	//attributes
	private final Width width = new Width();
	private int textCursorPosition = 0;
	private final TextCursor textCursor = new TextCursor();
	
	//constructor
	/**
	 * Creates new text box that has default attributes.
	 */
	public TextBox() {	
		resetConfiguration();
	}
	
	//method
	/**
	 * @return the attributes of this specifiable object
	 */
	public final List<Specification> getAttributes() {
		
		//Calls method of the base class.
		List<Specification> attributes = super.getAttributes();
		
		try {
			attributes.addAtEnd(getRefWidth().getSpecification());
			attributes.addAtEnd(new Specification(CURSOR_COLOR, getRefTextCursor().getRefColor().getAttributes()));
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return attributes;
	}
	
	public final boolean hasRole(final String role) {
		return false;
	}
	
	//method
	/**
	 * Notes a click.
	 * @throws Exception if an error occurs
	 */
	public final void noteLeftMouseButtonPress() {
		
		//Calls method of the base class.
		super.noteLeftMouseButtonPress();
		
		if (contentIsPointed())	{
			int textCursorDistanceFromTextBegin = getMouseXPosition() - getContentXPosition();
			JFrame frame = new JFrame();
			boolean found = false;
			for (int i = 0; i < getText().length(); i++) {
				int subTextWidth = frame.getFontMetrics(new Font("Sans-Serif", Font.PLAIN, getRefCurrentStructure().getCurrentTextSize())).stringWidth(getText().substring(0, i));
				int nextSubTextWidth = frame.getFontMetrics(new Font("Sans-Serif", Font.PLAIN, getRefCurrentStructure().getCurrentTextSize())).stringWidth(getText().substring(0, i + 1));
				int halfDistance = (nextSubTextWidth - subTextWidth) / 2;
				if (
					textCursorDistanceFromTextBegin > subTextWidth - halfDistance &&
					textCursorDistanceFromTextBegin < subTextWidth + halfDistance
				) {
					found = true;
					setTextCursorPosition(i);					
					break;
				}
			}
			
			if (!found) {
				setTextCursorPosition(getText().length());
			}
		}
	}
	
	//method
	/**
	 * Notes the given key event.
	 * @param keyEvent
	 * @throws Exception if an error occurs
	 */
	public final void noteTypedKeyWhenFocused(KeyEvent keyEvent) {
		
		if (isFocused()) {
			if (Character.isLetter(keyEvent.getKeyChar()) || Character.isDigit(keyEvent.getKeyChar())) {
				insertCharacterAfterCursor(keyEvent.getKeyChar());
			}
		}
	}
	
	//method
	/**
	 * Notes a press of a key.
	 */
	public final void noteKeyPress(KeyEvent keyEvent) {
			
		if (isFocused()) {
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
			}
		}
	}
	
	//method
	/**
	 * Sets the given attribute.
	 * @param attribute
	 * @throws Exception if the given attribute is not valid
	 */
	public final void addOrChangeAttribute(Specification attribute) {
		switch (attribute.getHeader()) {
			case Width.SIMPLE_CLASS_NAME:
				setWidth(attribute.getOneAttributeToInteger());
				break;
			case CURSOR_COLOR:
				break;
			default:
				
				//Calls method of the base class.
				super.addOrChangeAttribute(attribute);
		}
	}
	
	//method
	/**
	 * Sets the cursor color of this text box.
	 * @param cursorColor
	 * @throws Exception if the given cursor color is no color name and no true color value
	 */
	public final void setCursorColor(String cursorColor) {
		getRefTextCursor().setColor(cursorColor);
	}
	
	//method
	/**
	 * Sets the default configuration of this sub configurable object.
	 */
	public final void resetConfiguration() {
		
		//Calls method of the base class.
		super.resetConfiguration();
		
		setWidth(DEFAULT_WIDTH);
		getRefNormalStructure().setDefaultBorder();
		getRefNormalStructure().setBackgroundColor(DEFAULT_BACKGROUND_COLOR);
		setCursorColor(DEFAULT_CURSOR_COLOR);
		setCursorIcon(CursorIcon.Edit);
	}
	
	//method
	/**
	 * Sets the width of this text box.
	 * @param width
	 * @throws Exception if the given width is smaller than the min width of a text box
	 */
	public final void setWidth(int width) {
		
		Validator.throwExceptionIfValueIsSmaller("width", MIN_WIDTH, width);
				
		getRefWidth().setValue(width);
	}
	
	//method
	/**
	 * Paints this rectangle using the given rectangle structure and graphics.
	 * @param rectangleStructure
	 * @param graphics
	 */
	protected final void paintContent(TextLineRectangleStructure rectangleStructure, Graphics graphics) {
		
		int textCursorDistanceFromTextBegin = new JFrame().getFontMetrics(new Font("Sans-Serif", Font.PLAIN, getRefCurrentStructure().getCurrentTextSize())).stringWidth(getTextBeforeTextCursor());
		graphics.setColor(textCursor.getRefColor().getJavaColor());
		graphics.fillRect(
			getContentXPosition() + textCursorDistanceFromTextBegin,
			getContentYPosition(),
			1,
			getRefCurrentStructure().getCurrentTextSize()
		);
	}
	
	private final void deleteCharacterAfterTextCursor() {
		if (getTextCursorPosition() < getText().length()) {
			setText(getTextBeforeTextCursor() + getTextAfterTextCursor().substring(1));
		}
	}
	
	//method
	/**
	 * Deletes the character before the cursor from the text of this text line rectangle if there is a character before the cursor.
	 */
	private final void deleteCharacterBeforeTextCursor() {
		if (!getText().isEmpty() && getTextCursorPosition() > 0) {
			setText(getText().substring(0, textCursorPosition  - 1) + getText().substring(textCursorPosition));
			textCursorPosition--;
		}
	}
	
	//method
	/**
	 * @return the width of this text box
	 */
	protected final int getContentWidth() {		
		return (
			getRefWidth().getValue() -
			getLeftPadding() -
			getRefCurrentStructure().getRecLeftBorderSize() -
			getRefCurrentStructure().getRecRightBorderSize() -
			getRightPadding()
		);
	}
	
	//method
	/**
	 * @return the cursor of this tex box
	 */
	private final TextCursor getRefTextCursor() {
		return textCursor;
	}
	
	//method
	/**
	 * @return the width of this text box
	 */
	private final Width getRefWidth() {
		return width;
	}
	
	//method
	/**
	 * @return the text after the text cursor of this text box
	 */
	private final String getTextAfterTextCursor() {
		return getText().substring(getTextCursorPosition());
	}
	
	//method
	/**
	 * @return the text before the text cursor of this text box
	 */
	private final String getTextBeforeTextCursor() {
		return getText().substring(0, getTextCursorPosition());
	}
	
	//method
	/**
	 * @return the position of the text cursor of this text box
	 */
	private final int getTextCursorPosition() {
		return textCursorPosition;
	}
	
	//method
	/**
	 * Inserts the given character after the cursor to the text of this text line rectangle.
	 * @param character
	 */
	private final void insertCharacterAfterCursor(char character) {
		setText(getTextBeforeTextCursor() + character + getTextAfterTextCursor());
		textCursorPosition++;
	}
	
	//method
	/**
	 * Sets the position of the text cursor of this text box.
	 * @param textCursorPosition
	 */
	private final void setTextCursorPosition(int textCursorPosition) {
		this.textCursorPosition = textCursorPosition;
	}
}
