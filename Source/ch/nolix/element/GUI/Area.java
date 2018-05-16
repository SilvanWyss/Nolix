//package declaration
package ch.nolix.element.GUI;

//own imports
import ch.nolix.core.container.ReadContainer;
import ch.nolix.core.constants.PascalCaseNameCatalogue;
import ch.nolix.core.constants.VariableNameCatalogue;
import ch.nolix.core.container.List;
import ch.nolix.core.specification.Specification;
import ch.nolix.core.specification.StandardSpecification;
import ch.nolix.element.color.Color;
import ch.nolix.element.intData.Height;
import ch.nolix.element.intData.Width;
import ch.nolix.element.painter.IPainter;
import ch.nolix.primitive.invalidStateException.UnexistingAttributeException;
import ch.nolix.primitive.validator2.Validator;

//class
/**
 * An {@link Area} is a {@link Widget} that:
 * -Has a specific width and height.
 * -Can have a background color.
 * 
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 310
 */
public final class Area extends Widget<Area, AreaLook> {

	//constant
	public static final String TYPE_NAME = "Area";
	
	//attributes
	private Width width = new Width();
	private Height height = new Height();
	
	//optional attribute
	private Color backgroundColor;
	
	//constructor
	/**
	 * Creates a new {@link Area}.
	 */
	public Area() {
		reset();
		approveProperties();
	}
	
	//constructor
	/**
	 * Creates a new {@link Area} with the given with, height and background color.
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
	 * Adds or changes the given attribute to the current {@link Area}.
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
			case PascalCaseNameCatalogue.BACKGROUND_COLOR:
				setBackgroundColor(Color.createFromSpecification(attribute));
				break;
			default:
				
				//Calls method of the base class.
				super.addOrChangeAttribute(attribute);
		}
	}
	
	//method
	/**
	 * @return the active cursor icon of the current {@link Area}.
	 */
	public CursorIcon getActiveCursorIcon() {
		return getCursorIcon();
	}
	
	//method
	/**
	 * @return the attributes of the current {@link Area}.
	 */
	public List<StandardSpecification> getAttributes() {
		
		//Calls method of the base class.
		final List<StandardSpecification> attributes = super.getAttributes();
		
		attributes
		.addAtEnd(width.getSpecification())
		.addAtEnd(height.getSpecification());
		
		//Handles the case that the current area has a background color.
		if (hasBackgroundColor()) {
			attributes.addAtEnd(
				getBackgroundColor().getSpecificationAs(
					PascalCaseNameCatalogue.BACKGROUND_COLOR
				)
			);
		}
		
		return attributes;
	}
	
	//method
	/**
	 * @return the background color of the current {@link Area}.
	 * @throws UnexistingAttributeException
	 * if the current {@link Area} has no background color.
	 */
	public Color getBackgroundColor() {
		
		//Checks if the current area has a background color.
		supposeHasBackgroundColor();
		
		return backgroundColor;
	}
	
	//method
	/**
	 * @return the height of the current {@link Area} when it is not collapsed.
	 */
	public int getHeightWhenNotCollapsed() {
		return height.getValue();
	}
	
	//method
	/**
	 * @return the widgetes of the current {@link Area}.
	 */
	public ReadContainer<Widget<?, ?>> getRefWidgets() {
		return new ReadContainer<Widget<?, ?>>();
	}
	
	//method
	/**
	 * @return the width of the current {@link Area} when it is not collapsed.
	 */
	public int getWidthWhenNotCollapsed() {
		return width.getValue();
	}
	
	//method
	/**
	 * @return true if the current {@link Area} has a background color.
	 */
	public boolean hasBackgroundColor() {
		return (backgroundColor != null);
	}
	
	//method
	/**
	 * @param role
	 * @return true if the current {@link Area} has the given role.
	 */
	public boolean hasRole(final String role) {
		return false;
	}
	
	//method
	/**
	 * Removes the background color of the current {@link Area}.
	 * 
	 * @return the current {@link Area}.
	 */
	public Area removeBackgroundColor() {
		
		backgroundColor = null;
		
		return this;
	}
		
	//method
	/**
	 * Resets the configuration of the current {@link Area}.
	 * 
	 * @return the current {@link Area}.
	 */
	public Area resetConfiguration() {
		
		//Calls method of the base class.
		super.resetConfiguration();
						
		setWidth(500);
		setHeight(200);
		setBackgroundColor(Color.LIGHT_GREY);
		
		return this;
	}
	
	//method
	/**
	 * Sets the background color of the current {@link Area}.
	 * 
	 * @param backgroundColor
	 * @return the current {@link Area}.
	 * @throws NullArgumentException
	 * if the given background color is null.
	 */
	public Area setBackgroundColor(final Color backgroundColor) {
		
		//Checks if the given background color is not null.
		Validator
		.suppose(backgroundColor)
		.thatIsNamed(VariableNameCatalogue.BACKGROUND_COLOR)
		.isNotNull();
		
		//Sets the background color of the current {@link Area}.
		this.backgroundColor = backgroundColor;
		
		return this;
	}
	
	//method
	/**
	 * Sets the height of the current {@link Area}.
	 * 
	 * @param height
	 * @return the current {@link Area}.
	 * @throws NonPositiveArgumentException
	 * if the given height is not positive.
	 */
	public Area setHeight(final int height) {
		
		this.height = new Height(height);
		
		return this;
	}
	
	//method
	/**
	 * Sets the with of the current {@link Area}.
	 * 
	 * @param width
	 * @return the current {@link Area}.
	 * @throws NonPositiveArgumentException
	 * if the given width is not positive.
	 */
	public Area setWidth(final int width) {
		
		this.width = new Width(width);
		
		return this;
	}
	
	//method
	/**
	 * @return a new widget look for the current {@link Area}.
	 */
	protected AreaLook createWidgetLook() {
		return new AreaLook();
	}
	
	//method
	/**
	 * Paints the current {@link Area} using the given area look and painter.
	 * 
	 * @param areaLook
	 * @param painter
	 */
	protected final void paint(
		final AreaLook areaLook,
		final IPainter painter
	) {
		//Handles the case that the current area has a background color.
		if (hasBackgroundColor()) {
			painter.setColor(getBackgroundColor());
			painter.paintFilledRectangle(getWidth(), getHeight());
		}
	}
	
	//method
	/**
	 * @throws UnexistingAttributeException
	 * if the current {@link Area} has no background color.
	 */
	private void supposeHasBackgroundColor() {
		
		//Checks if the current area has a background color.
		if (!hasBackgroundColor()) {
			throw new UnexistingAttributeException(
				this,
				VariableNameCatalogue.BACKGROUND_COLOR
			);
		}
	}
}
