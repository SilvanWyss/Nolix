/*
 * file:	Line.java
 * author:	Silvan Wyss
 * month:	2015-12
 * lines:	250
 */

//package declaration
package ch.nolix.element.GUI;

//Java import
import java.awt.Graphics;

import ch.nolix.core.container.AccessorContainer;

//own imports

import ch.nolix.core.container.List;
import ch.nolix.core.invalidArgumentException.Argument;
import ch.nolix.core.invalidArgumentException.ArgumentName;
import ch.nolix.core.invalidArgumentException.ErrorPredicate;
import ch.nolix.core.invalidArgumentException.InvalidArgumentException;
import ch.nolix.core.specification.StandardSpecification;
import ch.nolix.element.basic.Color;
import ch.nolix.element.data.Length;
import ch.nolix.element.data.Thickness;

//abstract class
/**
 * A line is a rectangle that has a color and which length is clearly bigger than its thickness.
 * 
 * @author Silvan Wyss
 * @param <L> The type of a line.
 */
public abstract class Line<L extends Line<L>>
extends Widget<L, LineStructure> {
	
	//constants
	public static final String SIMPLE_CLASS_NAME = "Line";
	public static final int MIN_LENGTH_TO_THICKNESS_RATIO = 4;
	
	//default values
	public static final int DEFAULT_LENGTH = 100;
	public static final int DEFAULT_THICKNESS = 1;
	public static final Color DEFAULT_COLOR = Color.BLACK;
	
	//attributes
	private final Length length = new Length();
	private final Thickness thickness = new Thickness();
	private Color color = new Color();
	
	//method
	/**
	 * Adds or change the given attribute to this line.
	 * 
	 * @param attribute		The attribute to add or change to this line.
	 * @throws Exception if the given attribute is not valid
	 */
	public final void addOrChangeAttribute(final StandardSpecification attribute) {
		
		//Enumerates the given attribute.
		switch (attribute.getHeader()) {
			case Length.TYPE_NAME:
				setLength(attribute.getOneAttributeToInteger());
				break;
			case Thickness.TYPE_NAME:
				setThickness(attribute.getOneAttributeToInteger());
				break;	
			case Color.TYPE_NAME:
				setColor(attribute.getOneAttributeToString());
				break;
			default:
				
				//Calls method of the base class.
				super.addOrChangeAttribute(attribute);			
		}
	}
	
	//method
	/**
	 * @return the attributes of this line
	 */
	public final List<StandardSpecification> getAttributes() {	
		
		//Calls method of the base class.
		final List<StandardSpecification> attributes = super.getAttributes();
		
		if (getLength() != DEFAULT_LENGTH) {
			attributes.addAtEnd(length.getSpecification());
		}
		
		if (getThickness() != DEFAULT_THICKNESS) {
			attributes.addAtEnd(thickness.getSpecification());
		}
		
		if (getColor().equals(DEFAULT_COLOR)) {
			attributes.addAtEnd(color.getSpecification());
		}
		
		return attributes;
	}
	
	//method
	/**
	 * @return the color of this line
	 */
	public final Color getColor() {
		return color;
	}
	
	//method
	/**
	 * @return the length of this line
	 */
	public final int getLength() {
		return length.getValue();
	}
	
	//method
	/**
	 * @return the thickness of this line
	 */
	public final int getThickness() {
		return thickness.getValue();
	}
	
	//method
	/**
	 * @param role		The role this line is ask to have.
	 * @return true if this line has the given role
	 */
	public final boolean hasRole(final String role) {
		return false;
	}
	
	//method
	/**
	 * Resets the confoguration of this line.
	 */
	public final void resetConfiguration() {
		
		//Calls method of the base class.
		super.resetConfiguration();
		
		setLength(DEFAULT_LENGTH);
		setThickness(DEFAULT_THICKNESS);
		setColor(DEFAULT_COLOR);
	}
	
	//method
	/**
	 * Sets the color of this line.
	 * 
	 * @param color		The color to set tot this line.
	 * @return this line
	 * @throws Exception if the given color is no true color
	 */
	@SuppressWarnings("unchecked")
	public final L setColor(final Color color) {
		
		this.color = color;
		
		return (L)this;
	}
	
	//method
	/**
	 * Sets the color of this line.
	 * 
	 * @param color		The color to set to this line.
	 * @return this line
	 * @throws Exception if the given color is no color name or no true color value
	 */
	@SuppressWarnings("unchecked")
	public final L setColor(final String color) {
		
		this.color = new Color(color);
		
		return (L)this;
	}
	
	//method
	/**
	 * Sets the length of this line.
	 * 
	 * @param length		The length to set to this line.
	 * @return this line
	 * @throws Exception if the given length is smaller than 4 times the thickness of this line
	 */
	@SuppressWarnings("unchecked")
	public final L setLength(int length) {
		
		//Checks the given length.
		if (length < MIN_LENGTH_TO_THICKNESS_RATIO * getThickness()) {
			throw new InvalidArgumentException(
				new ArgumentName("length"),
				new Argument(length),
				new ErrorPredicate("is smaller than " + MIN_LENGTH_TO_THICKNESS_RATIO + "x the thickness " + getThickness() + ".")
			);
		}
		
		this.length.setValue(length);
		
		return (L)this;
	}
	
	//method
	/**
	 * Sets the thickness of this line
	 * 
	 * @param thickness		The thickness to set to this line.
	 * @return this line
	 * @throws Exception if 4 times the given thickness is bigger than the length of this line
	 */
	@SuppressWarnings("unchecked")
	public final L setThickness(final int thickness) {
		
		//Checks the given thickness.
		if (MIN_LENGTH_TO_THICKNESS_RATIO * thickness > getLength()) {
			throw new InvalidArgumentException(
				new ArgumentName("thickness"),
				new Argument(thickness),
				new ErrorPredicate(" multiplied with " + MIN_LENGTH_TO_THICKNESS_RATIO +  " is bigger than the length " + getLength() + ".")
			);
		}
		
		this.thickness.setValue(thickness);
		
		return (L)this;
	}
	
	//method
	/**
	 * @return new widget structure for this line.
	 */
	protected final LineStructure createWidgetStructure() {
		return new LineStructure();
	}
	
	//method
	/**
	 * Paints this rectangle using the given rectangle structure and graphics.
	 * 
	 * @param rectangleStructure
	 * @param graphics
	 */
	protected final void paint(
		final LineStructure rectangleStructure,
		final Graphics graphics
	) {
		graphics.setColor(color.getJavaColor());
		graphics.fillRect(
			getXPositionOnContainer(),
			getYPositionOnContainer(),
			getWidthWhenNotCollapsed(),
			getHeightWhenNotCollapsed()
		);
	}
	
	@Override
	public AccessorContainer<Widget<?, ?>> getRefWidgets() {
		return new AccessorContainer<>();
	}
}
