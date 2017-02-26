/*
 * file:	GraphicText.java
 * author:	Silvan Wyss
 * month:	2016-03
 * lines:	150
 */

//package declaration
package ch.nolix.element.data;

//Java imports
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JFrame;

















//own imports
import ch.nolix.common.container.List;
import ch.nolix.common.exception.Argument;
import ch.nolix.common.exception.InvalidArgumentException;
import ch.nolix.common.exception.ArgumentName;
import ch.nolix.common.specification.Specification;
import ch.nolix.element.basic.Color;
import ch.nolix.element.basic.Element;
import ch.nolix.element.basic.PositiveInteger;
import ch.nolix.element.basic.Text;

//class
/**
 * A graphic text:
 *  -has a text
 *  -has a size
 *  -has a color
 *  -can be painted
 */
public final class GraphicText extends Element {
	
	public static final int DEFAULT_SIZE = 10;
	public static final String DEFAULT_COLOR = Color.BLACK_STRING;
	
	//attributes
	private final Text text = new Text();
	private final PositiveInteger size = new PositiveInteger();
	private Color color = new Color();
	
	//constructor
	/**
	 * Creates new graphic text with default values.
	 */
	public GraphicText() {
		reset();
	}
	
	//method
	/**
	 * @return the attributes of this graphic text
	 */
	public final List<Specification> getAttributes() {	
		return new List<Specification>()
			.addAtEnd(text.getSpecification())
			.addAtEnd(size.getSpecification())
			.addAtEnd(color.getSpecification());
	}
	
	//method
	/**
	 * @return the height of this graphic text
	 */
	public final int getHeight() {
		return new JFrame().getFontMetrics(new Font("Sans-Serif", Font.PLAIN, getSize())).getHeight();
	}
	
	//method
	/**
	 * @return the size of this graphic text
	 */
	public final int getSize() {
		return size.getValue();
	}
	
	//method
	/**
	 * @return the text of this graphic text
	 */
	public final String getText() {
		return text.getValue();
	}
	
	//method
	/**
	 * @return the width of this graphic text
	 */
	public final int getWidth() {
		return new JFrame().getFontMetrics(new Font("Sans-Serif", Font.PLAIN, getSize())).stringWidth(getText());
	}
	
	public final void paint(Graphics graphics) {
		paint(graphics, 0, 0);
	}
	
	//method
	/**
	 * Paints this graphic text using the given graphics.
	 * 
	 * @param graphics
	 * @param xPosition
	 * @param yPosition
	 */
	public void paint(Graphics graphics, int xPosition, int yPosition) {
		graphics.setFont(new Font("Sans-Serif", Font.PLAIN, getSize()));
		graphics.setColor(color.getJavaColor());
		graphics.drawString(getText(), xPosition, yPosition + getSize());		
	}
	
	//method
	/**
	 * Resets this graphic text.
	 */
	public final void reset() {
		text.reset();
		setSize(DEFAULT_SIZE);
		setColor(DEFAULT_COLOR);
	}
	
	//method
	/**
	 * Sets the given attribute to this graphic text.
	 * 
	 * @param attribute
	 * @throws Exception if the given attribute is not valid
	 */
	public final void addOrChangeAttribute(Specification attribute) {
		switch (attribute.getHeader()) {
			case Text.SIMPLE_CLASS_NAME:
				setText(attribute.getOneAttributeToString());
				break;
			case Size.SIMPLE_CLASS_NAME:
				setSize(attribute.getOneAttributeToInteger());
				break;
			case Color.SIMPLE_CLASS_NAME:
				setColor(attribute.getOneAttributeToString());
				break;
			default:
				throw new InvalidArgumentException(
					new ArgumentName("attribute"),
					new Argument(attribute)
				);
		}
	}
	
	//method
	/**
	 * Sets the color of this graphic text.
	 * 
	 * @param color
	 * @return this graphic text
	 * @throws Exception if the given color is no true color value
	 */
	public GraphicText setColor(int color) {
		
		this.color = new Color(color);
		
		return this;
	}
	
	//method
	/**
	 * Sets the color of this graphic text.
	 * 
	 * @param color
	 * @return this graphic text
	 * @throws Exception if the given color is no color name or no true color value.
	 */
	public final GraphicText setColor(String color) {
		
		this.color = new Color(color);
		
		return this;
	}
	
	//method
	/**
	 * Sets the size of this graphic text.
	 * 
	 * @param size
	 * @return this graphic text
	 * @throws Exception if the given size is not positive
	 */
	public final GraphicText setSize(int size) {
		
		this.size.setValue(size);
		
		return this;
	}
	
	//method
	/**
	 * Sets the text of this graphic text.
	 * 
	 * @param text
	 * @return this graphic text
	 * @throws Exception if the given text is null or an empy string
	 */
	public final GraphicText setText(String text) {
		
		this.text.setValue(text);
		
		return this;
	}
}
