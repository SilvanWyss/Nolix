//package declaration
package ch.nolix.element.GUI;

//Java import
import java.awt.Graphics;

//own imports
import ch.nolix.core.container.AccessorContainer;
import ch.nolix.core.container.List;
import ch.nolix.core.invalidStateException.UnexistingAttributeException;
import ch.nolix.core.specification.StandardSpecification;
import ch.nolix.core.validator2.Validator;
import ch.nolix.element.color.Color;
import ch.nolix.element.data.BackgroundColor;
import ch.nolix.element.intData.Height;
import ch.nolix.element.intData.Width;

//class
/**
 * An area is a widget that:
 * -Has a specific width and height.
 * -Can have a background color.
 * 
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 260
 */
public class Area extends Widget<Area, AreaStructure> {

	//type name
	public static final String TYPE_NAME = "Area";
	
	//default values
	public static final int DEFAULT_WIDTH = 200;
	public static final int DEFAULT_HEIGHT = 100;
	public static final Color DEFAULT_BACKGROUND_COLOR = Color.GREY;

	//attribute header
	private static final String BACKGROUND_COLOR_HEADER = "BackgroundColor";
	
	//attributes
	private Width width = new Width();
	private Height height = new Height();
	
	//optional attribute
	private Color backgroundColor;
	
	//constructor
	/**
	 * Creates new area with default values.
	 */
	public Area() {
		resetConfiguration();
	}
	
	//method
	/**
	 * Adds or changes the given attribute to this area.
	 * 
	 * @param attribute
	 * @throws InvalidArgumentException if the given attribute is not valid.
	 */
	public void addOrChangeAttribute(final StandardSpecification attribute) {
		
		//Enumerates the header of the given attribute.
		switch (attribute.getHeader()) {
			case Width.TYPE_NAME:
				setWidth(attribute.getOneAttributeToInteger());
				break;
			case Height.TYPE_NAME:
				setHeight(attribute.getOneAttributeToInteger());
				break;
			case BackgroundColor.TYPE_NAME:
				setBackgroundColor(
					new BackgroundColor(attribute.getOneAttributeToString())
				);
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
	public List<StandardSpecification> getAttributes() {
		
		//Calls method of the base class.
		final List<StandardSpecification> attributes = super.getAttributes();
		
		attributes
		.addAtEnd(width.getSpecification())
		.addAtEnd(height.getSpecification());
		
		//Handles the option that this area has a background color.
		if (hasBackgroundColor()) {
			attributes.addAtEnd(
				getBackgroundColor().getSpecificationAs(BACKGROUND_COLOR_HEADER)
			);
		}
		
		return attributes;
	}
	
	//method
	/**
	 * @return the background color of this area.
	 * @throws UnexistingAttributeException if this area has no background color.
	 */
	public Color getBackgroundColor() {
		
		//Checks if this area has a background color.
		if (!hasBackgroundColor()) {
			throw new UnexistingAttributeException(this, "background color");
		}
		
		return backgroundColor;
	}
	
	//method
	/**
	 * @return the height of this widget when it is not collapsed.
	 */
	public int getHeightWhenNotCollapsed() {
		return height.getValue();
	}
	
	//method
	/**
	 * @return the element of this area.
	 */
	public AccessorContainer<Widget<?, ?>> getRefWidgets() {
		return new AccessorContainer<>();
	}
	
	//method
	/**
	 * @return the width of this widget when it is not collapsed.
	 */
	public int getWidthWhenNotCollapsed() {
		return width.getValue();
	}
	
	//method
	/**
	 * @return true if this area has a background color.
	 */
	public boolean hasBackgroundColor() {
		return (backgroundColor != null);
	}
	
	//method
	/**
	 * @param role
	 * @return true if this area has the given role.
	 */
	public boolean hasRole(final String role) {
		return false;
	}
	
	//method
	/**
	 * Removes the background color of this area.
	 */
	public void removeBackgroundColor() {
		backgroundColor = null;
	}
		
	//method
	/**
	 * Resets the configuration of this area.
	 */
	public void resetConfiguration() {
		
		//Calls method of the base class.
		super.resetConfiguration();
		
		setWidth(DEFAULT_WIDTH);
		setHeight(DEFAULT_HEIGHT);
		setBackgroundColor(DEFAULT_BACKGROUND_COLOR);			
	}
	
	//method
	/**
	 * Sets the background color of this area.
	 * 
	 * @param backgroundColor
	 * @return this area.
	 * @throws NullArgumentException if the given background color is null.
	 */
	public Area setBackgroundColor(final Color backgroundColor) {
		
		//Checks if the given background color is not null.
		Validator
		.suppose(backgroundColor)
		.thatIsInstanceOf(BackgroundColor.class)
		.isNotNull();
		
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
	public Area setHeight(final int height) {
		
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
	public Area setWidth(final int width) {
		
		this.width = new Width(width);
		
		return this;
	}
	
	//method
	/**
	 * Creates new widget structure for this area.
	 */
	protected AreaStructure createWidgetStructure() {
		return new AreaStructure();
	}
	
	//method
	/**
	 * Paints this area using the given widget structure and graphics.
	 * 
	 * @param widgetStructure
	 * @param graphics
	 */
	protected final void paint(
		final AreaStructure widgetStructure,
		final Graphics graphics
	) {
		//Handles the option that this area has a background color.
		if (hasBackgroundColor()) {
			graphics.setColor(backgroundColor.getJavaColor());
			graphics.fillRect(0, 0, getWidth(), getHeight());
		}
	}
}
