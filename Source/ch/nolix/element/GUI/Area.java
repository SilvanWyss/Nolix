//package declaration
package ch.nolix.element.GUI;

//Java import
import java.awt.Graphics;

//own imports


import ch.nolix.core.container.List;
import ch.nolix.core.specification.Specification;
import ch.nolix.core.validator2.Validator;
import ch.nolix.element.basic.Color;
import ch.nolix.element.data.BackgroundColor;
import ch.nolix.element.data.Height;
import ch.nolix.element.data.Width;

//class
/**
 * An area is a widget that:
 * -Has a specific width and height.
 * -Can have a background color.
 * 
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 210
 */
public final class Area extends Widget<AreaStructure, Area> {

	//constant
	public static final String SIMPLE_CLASS_NAME = "Area";
	
	//default values
	public static final int DEFAULT_WIDTH = 200;
	public static final int DEFAULT_HEIGHT = 100;
	public static final int DEFAULT_BACKGROUND_COLOR = Color.LIGHT_GREY;
	
	//attributes
	private Width width = new Width();
	private Height height = new Height();
	
	//optional attribute
	private BackgroundColor backgroundColor;
	
	//constructor
	/**
	 * Creates new area with default values.
	 */
	public Area() {
		
		//Calls constructor of the base class.
		super(
			new AreaStructure(),
			new AreaStructure(),
			new AreaStructure()
		);
		
		resetConfiguration();
	}
	
	//method
	/**
	 * Adds or changes the given attribute to this area.
	 * 
	 * @param attribute
	 * @throws InvalidArgumentException if the given attribute is not valid.
	 */
	public void addOrChangeAttribute(final Specification attribute) {
		
		//Enumerates the header of the given attribute.
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
	 * @return the attributes of this area.
	 */
	public List<Specification> getAttributes() {
		
		//Calls method of the base class.
		final List<Specification> attributes = super.getAttributes();
		
		attributes
		.addAtEnd(width.getSpecification())
		.addAtEnd(height.getSpecification());
		
		if (hasBackgroundColor()) {
			attributes.addAtEnd(backgroundColor.getSpecification());
		}
		
		return attributes;
	}
	
	//method
	/**
	 * @return true if this area has a background color.
	 */
	public final boolean hasBackgroundColor() {
		return (backgroundColor != null);
	}
	
	//method
	/**
	 * @param role
	 * @return true if this area has the given role.
	 */
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
	 * Sets the background color of this area.
	 * 
	 * @param backgroundColor
	 * @throws NullArgumentException if the given background color is null.
	 */
	public final Area setBackgroundColor(final BackgroundColor backgroundColor) {
		
		//Checks if the given background color is not null.
		Validator.supposeThat(backgroundColor).thatIsInstanceOf(BackgroundColor.class).isNotNull();
		
		//Sets the background color of this area.
		this.backgroundColor = backgroundColor;
		
		return this;
	}
	
	//method
	/**
	 * Sets the height of this area.
	 * 
	 * @param height
	 * @return this area.
	 * @throws NonPositiveArgumentException if the given height is not positive.
	 */
	public final Area setHeight(final int height) {
		
		this.height = new Height(height);
		
		return this;
	}
	
	//method
	/**
	 * Sets the with of this area.
	 * 
	 * @param width
	 * @return this area.
	 * @throws NonPositiveArgumentException if the given width is not positive.
	 */
	public final Area setWidth(final int width) {
		
		this.width = new Width(width);
		
		return this;
	}
	
	//method
	/**
	 * @return the height of this widget when it is not collapsed.
	 */
	protected final int getHeightWhenNotCollapsed() {
		return height.getValue();
	}
	
	//method
	/**
	 * @return the width of this widget when it is not collapsed.
	 */
	protected final int getWidthWhenNotCollapsed() {
		return width.getValue();
	}
	
	//method
	/**
	 * Paints this area using the given widget structure and graphics.
	 */
	protected final void paint(final AreaStructure widgetStructure, final Graphics graphics) {
		if (hasBackgroundColor()) {
			backgroundColor.paintRectangle(graphics, 0, 0, getWidth(), getHeight());
		}
	}
}
