/*
 * file:	Area.java
 * author:	Silvan Wyss
 * month:	2015-12
 * lines:	230
 */

//package declaration
package ch.nolix.element.dialog;

//Java import
import java.awt.Graphics;

//own imports
import ch.nolix.common.container.List;
import ch.nolix.common.specification.Specification;
import ch.nolix.common.util.Validator;
import ch.nolix.element.basic.Color;
import ch.nolix.element.data.BackgroundColor;
import ch.nolix.element.data.Height;
import ch.nolix.element.data.Width;

//class
/**
 * An area is a rectangle that:
 * -Has a specific width and height.
 * -Can have a background color.
 */
public final class Area extends Rectangle<Area, SimpleRectangleStructure> {

	//constant
	public final static String SIMPLE_CLASS_NAME = "Area";
	
	//default values
	public final static int DEFAULT_WIDTH = 200;
	public final static int DEFAULT_HEIGHT = 100;
	public final static int DEFAULT_BACKGROUND_COLOR = Color.LIGHT_GREY;
	
	//attributes
	private final Width width = new Width();
	private final Height height = new Height();
	
	//optional attribute
	private BackgroundColor backgroundColor;
	
	//constructor
	/**
	 * Creates new area with default values.
	 */
	public Area() {
		
		//Calls constructor of the base class.
		super(
			new SimpleRectangleStructure(),
			new SimpleRectangleStructure(),
			new SimpleRectangleStructure()
		);
		
		resetConfiguration();
	}
	
	//method
	/**
	 * @return the attributes of this area
	 */
	public List<Specification> getAttributes() {
		
		//Calls method of the base class.
		List<Specification> attributes = super.getAttributes();
		
		attributes.addAtEnd(width.getSpecification())
			.addAtEnd(height.getSpecification());
		
		if (hasBackgroundColor()) {
			attributes.addAtEnd(backgroundColor.getSpecification());
		}
		
		return attributes;
	}
	
	//method
	/**
	 * @return true if this area has a background color
	 */
	public final boolean hasBackgroundColor() {
		return (backgroundColor != null);
	}
	
	public final boolean hasRole(final String role) {
		return false;
	}
	
	//method
	/**
	 * Removes the background color of this area.
	 */
	public final void removeBackgroundColor() {
		backgroundColor = null;
	}
	
	//method
	/**
	 * Sets the background color of this area.
	 * @param backgroundColor
	 * @throws Exception if the given background color is null
	 */
	public final Area setBackgroundColor(BackgroundColor backgroundColor) {
		
		//Checks the given background color.
		Validator.throwExceptionIfValueIsNull("background color", backgroundColor);
		
		this.backgroundColor = backgroundColor;
		
		return this;
	}
	
	//method
	/**
	 * Sets the given attribute to this area.
	 * 
	 * @param attribute
	 * @throws Exception if the given attribute is not valid
	 */
	public void addOrChangeAttribute(Specification attribute) {
		switch (attribute.getHeader()) {
			case Width.SIMPLE_CLASS_NAME:
				setWidth(attribute.getOneAttributeToInteger());
				break;
			case Height.SIMPLE_CLASS_NAME:
				setHeight(attribute.getOneAttributeToInteger());
				break;
			case BackgroundColor.SIMPLE_CLASS_NAME:
				setBackgroundColor(new BackgroundColor(attribute.getOneAttributeToString()));
				break;
			default:
				
				//Calls method of the base class.
				super.addOrChangeAttribute(attribute);
		}
	}
	
	//method
	/**
	 * Resets this area.
	 */
	public final void reset() {
		
		//Calls method of the base class.
		super.reset();
		
		removeBackgroundColor();
	}
	
	//method
	/**
	 * Resets the configuration of this area.
	 */
	public final void resetConfiguration() {
		
		//Calls method of the base class.
		super.resetConfiguration();
		
		setWidth(DEFAULT_WIDTH);
		setHeight(DEFAULT_HEIGHT);
		setBackgroundColor(new BackgroundColor(DEFAULT_BACKGROUND_COLOR));			
	}
	
	//method
	/**
	 * Sets the height of this area.
	 * 
	 * @param height
	 * @throws Exception if the given height is not positive
	 */
	public final void setHeight(int height) {
		this.height.setValue(height);
	}
	
	//method
	/**
	 * Sets the with of this area.
	 * 
	 * @param width
	 * @throws Exception if the given width is not positive
	 */
	public final void setWidth(int width) {
		this.width.setValue(width);
	}
	
	//method
	/**
	 * @return the height of this rectangle when it is not collapsed
	 */
	protected final int getHeightWhenNotCollapsed() {
		return height.getValue();
	}
	
	//method
	/**
	 * @return the width of this rectangle when it is not collapsed
	 */
	protected final int getWidthWhenNotCollapsed() {
		return width.getValue();
	}
	
	//method
	/**
	 * Paints this area using the given rectangle structure and graphics.
	 */
	protected final void paint(SimpleRectangleStructure rectangleStructure, Graphics graphics) {
		if (hasBackgroundColor()) {
			backgroundColor.paintRectangle(graphics, 0, 0, getWidth(), getHeight());
		}
	}
}
