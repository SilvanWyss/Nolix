//package declaration
package ch.nolix.element.GUI;

//own imports
import ch.nolix.core.container.ReadContainer;
import ch.nolix.core.constants.VariableNameCatalogue;
import ch.nolix.core.container.List;
import ch.nolix.core.specification.Specification;
import ch.nolix.core.specification.StandardSpecification;
import ch.nolix.element.color.Color;
import ch.nolix.element.data.BackgroundColor;
import ch.nolix.element.intData.Height;
import ch.nolix.element.intData.Width;
import ch.nolix.element.painter.IPainter;
import ch.nolix.primitive.invalidStateException.UnexistingAttributeException;
import ch.nolix.primitive.validator2.Validator;

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
public final class Area extends Widget<Area, AreaStructure> {

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
	 * Creates a new area with default values.
	 */
	public Area() {
		reset();
		approveProperties();
	}
	
	//constructor
	/**
	 * Creates new area with the given with, height and background color.
	 * 
	 * @param width
	 * @param height
	 * @param backgroundColor
	 * @throws NonPositiveArgumentException if the given width is not positive.
	 * @throws NonPositiveArgumentException if the given height is not positive.
	 * @throws NullArgumentException if the given background color is null.
	 */
	public Area(
		final int width,
		final int height,
		final Color backgroundColor
	) {
		
		//Calls other constructor
		this();
		
		setWidth(width);
		setHeight(height);
		setBackgroundColor(backgroundColor);
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
			case Width.TYPE_NAME:
				setWidth(attribute.getOneAttributeAsInt());
				break;
			case Height.TYPE_NAME:
				setHeight(attribute.getOneAttributeAsInt());
				break;
			case BackgroundColor.TYPE_NAME:
				setBackgroundColor(
					new BackgroundColor(attribute.getOneAttributeAsString())
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
		
		//Handles the case that this area has a background color.
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
		supposeHasBackgroundColor();
		
		return backgroundColor;
	}
	
	//method
	/**
	 * @return the height of this area when it is not collapsed.
	 */
	public int getHeightWhenNotCollapsed() {
		return height.getValue();
	}
	
	//method
	/**
	 * @return the widgetes of this area.
	 */
	public ReadContainer<Widget<?, ?>> getRefWidgets() {
		return new ReadContainer<>();
	}
	
	//method
	/**
	 * @return the width of this area when it is not collapsed.
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
	 * 
	 * @return this area.
	 */
	public Area resetConfiguration() {
						
		setWidth(DEFAULT_WIDTH);
		setHeight(DEFAULT_HEIGHT);
		setBackgroundColor(DEFAULT_BACKGROUND_COLOR);
		
		//Calls method of the base class.
		return super.resetConfiguration();
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
		.thatIsNamed(VariableNameCatalogue.BACKGROUND_COLOR)
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
	 * Creates a new widget structure for this area.
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
		final IPainter graphics
	) {
		//Handles the case that this area has a background color.
		if (hasBackgroundColor()) {
			graphics.setColor(backgroundColor);
			graphics.paintFilledRectangle(getWidth(), getHeight());
		}
	}
	
	//method
	/**
	 * @throws UnexistingAttributeException if this area has no background color.
	 */
	private void supposeHasBackgroundColor() {
		
		//Checks if this area has a background color.
		if (!hasBackgroundColor()) {
			throw new UnexistingAttributeException(this, "background color");
		}
	}
}
