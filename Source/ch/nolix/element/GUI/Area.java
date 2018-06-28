//package declaration
package ch.nolix.element.GUI;

//own imports
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
 * A {@link Area} is a {@link Widget} that:
 * -Has a specific width and height.
 * -Can have a background color.
 * 
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 360
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
		applyUsableConfiguration();
	}
	
	//constructor
	/**
	 * Creates a new {@link Area} with the given background color.
	 * 
	 * @param backgroundColor
	 * @throws NullArgumentException if the given background color is null.
	 */
	public Area(final Color backgroundColor) {
		
		//Calls other constructor
		this();
		
		setBackgroundColor(backgroundColor);
	}
	
	//constructor
	/**
	 * Creates a new {@link Area} with the given with and height.
	 * 
	 * @param width
	 * @param height
	 * @throws NonPositiveArgumentException if the given width is not positive.
	 * @throws NonPositiveArgumentException if the given height is not positive.
	 */
	public Area(final int width, final int height) {
		
		//Calls other constructor
		this();
		
		setSize(width, height);
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
		
		setSize(width, height);
		setBackgroundColor(backgroundColor);
	}
	
	//method
	/**
	 * {@inheritDoc}
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
	 * {@inheritDoc}
	 */
	public CursorIcon getActiveCursorIcon() {
		return getCursorIcon();
	}
	
	//method
	/**
	 * {@inheritDoc}
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
	 * {@inheritDoc}
	 */
	public Area resetConfiguration() {
		
		//Calls method of the base class.
		super.resetConfiguration();
				
		setWidth(500);
		setHeight(200);
		removeBackgroundColor();
		
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
	 * Sets the size of the current {@link Area}.
	 * 
	 * @param width
	 * @param height
	 * @return the current {@link Area}.
	 * @throws NonPositiveArgumentException
	 * if the given width is not positive.
	 * @throws NonPositiveArgumentException
	 * if the given height is not positive.
	 */
	public Area setSize(final int width, final int height) {
		
		setWidth(width);
		setHeight(height);
		
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
	 * {@inheritDoc}
	 */
	protected void applyUsableConfigurationWhenConfigurationIsReset() {
		setBackgroundColor(Color.LIGHT_GREY);
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	protected AreaLook createWidgetLook() {
		return new AreaLook();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	protected void fillUpWidgets(final List<Widget<?, ?>> list) {}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	protected int getHeightWhenNotCollapsed() {
		return height.getValue();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	protected int getWidthWhenNotCollapsed() {
		return width.getValue();
	}
	
	//method
	/**
	 * {@inheritDoc}
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
	 * {@inheritDoc}
	 */
	protected boolean viewAreaIsUnderCursor() {
		return isUnderCursor();
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
