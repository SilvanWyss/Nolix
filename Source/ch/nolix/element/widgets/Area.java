//package declaration
package ch.nolix.element.widgets;

import ch.nolix.common.constant.PascalCaseNameCatalogue;
import ch.nolix.common.constant.VariableNameCatalogue;
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.invalidArgumentException.ArgumentDoesNotHaveAttributeException;
import ch.nolix.common.node.BaseNode;
import ch.nolix.common.node.Node;
import ch.nolix.common.validator.Validator;
import ch.nolix.element.GUI.Widget;
import ch.nolix.element.color.Color;
import ch.nolix.element.painter.IPainter;

//class
/**
 * A {@link Area} is a {@link Widget} that:
 * -Has a specific width and height.
 * -Can have a background color.
 * 
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 370
 */
public final class Area extends Widget<Area, AreaLook> {
	
	//constant
	public static final String TYPE_NAME = "Area";
	
	//attributes
	private int width;
	private int height;
	
	//optional attribute
	private Color backgroundColor;
	
	//constructor
	/**
	 * Creates a new {@link Area}.
	 */
	public Area() {
		resetAndApplyDefaultConfiguration();
	}
	
	//constructor
	/**
	 * Creates a new {@link Area} with the given backgroundColor.
	 * 
	 * @param backgroundColor
	 * @throws ArgumentIsNullException if the given backgroundColor is null.
	 */
	public Area(final Color backgroundColor) {
		
		resetAndApplyDefaultConfiguration();
		
		setBackgroundColor(backgroundColor);
	}
	
	//constructor
	/**
	 * Creates a new {@link Area} with the given width and height.
	 * 
	 * @param width
	 * @param height
	 * @throws NonPositiveArgumentException if the given width is not positive.
	 * @throws NonPositiveArgumentException if the given height is not positive.
	 */
	public Area(final int width, final int height) {
		
		resetAndApplyDefaultConfiguration();
		
		setSize(width, height);
	}
	
	//constructor
	/**
	 * Creates a new {@link Area} with the given width, height and background color.
	 * 
	 * @param width
	 * @param height
	 * @param backgroundColor
	 * @throws NonPositiveArgumentException if the given width is not positive.
	 * @throws NonPositiveArgumentException if the given height is not positive.
	 * @throws ArgumentIsNullException if the given background color is null.
	 */
	public Area(
		final int width,
		final int height,
		final Color backgroundColor
	) {
		
		resetAndApplyDefaultConfiguration();
		
		setSize(width, height);
		setBackgroundColor(backgroundColor);
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void addOrChangeAttribute(final BaseNode attribute) {
		
		//Enumerates the header of the given attribute.
		switch (attribute.getHeader()) {
			case PascalCaseNameCatalogue.WIDTH:
				setWidth(attribute.getOneAttributeAsInt());
				break;
			case PascalCaseNameCatalogue.HEIGHT:
				setHeight(attribute.getOneAttributeAsInt());
				break;
			case PascalCaseNameCatalogue.BACKGROUND_COLOR:
				setBackgroundColor(Color.fromSpecification(attribute));
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
	@Override
	public LinkedList<Node> getAttributes() {
		
		//Calls method of the base class.
		final LinkedList<Node> attributes = super.getAttributes();
		
		attributes
		.addAtEnd(new Node(PascalCaseNameCatalogue.HEIGHT, height))
		.addAtEnd(new Node(PascalCaseNameCatalogue.WIDTH, width));
		
		//Handles the case that the current Area has a background color.
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
	 * @throws ArgumentDoesNotHaveAttributeException if the current {@link Area} does not have a background color.
	 */
	public Color getBackgroundColor() {
		
		//Asserts that the current Area has a background color.
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
	@Override
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
	@Override
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
	 * @throws ArgumentIsNullException
	 * if the given background color is null.
	 */
	public Area setBackgroundColor(final Color backgroundColor) {
		
		//Asserts that the given background color is not null.
		Validator
		.assertThat(backgroundColor)
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
	 * @throws NonPositiveArgumentException if the given height is not positive.
	 */
	public Area setHeight(final int height) {
		
		Validator.assertThat(height).thatIsNamed(VariableNameCatalogue.HEIGHT).isPositive();
		
		this.height = height;
		
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
		
		Validator.assertThat(width).thatIsNamed(VariableNameCatalogue.WIDTH).isPositive();
		
		this.width = width;
		
		return this;
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean viewAreaIsUnderCursor() {
		return isUnderCursor();
	}

	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void applyDefaultConfigurationWhenHasBeenReset() {
		setBackgroundColor(Color.LIGHT_GREY);
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected AreaLook createLook() {
		return new AreaLook();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void fillUpChildWidgets(final LinkedList<Widget<?, ?>> list) {}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void fillUpPaintableWidgets(final LinkedList<Widget<?, ?>> list) {}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected int getHeightWhenNotCollapsed() {
		return height;
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected int getWidthWhenNotCollapsed() {
		return width;
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void paint(final IPainter painter, final AreaLook areaLook) {
		
		//Handles the case that the current Area has a background color.
		if (hasBackgroundColor()) {
			painter.setColor(getBackgroundColor());
			painter.paintFilledRectangle(getWidth(), getHeight());
		}
	}
	
	//method
	/**
	 * @throws ArgumentDoesNotHaveAttributeException
	 * if the current {@link Area} does not have a background color.
	 */
	private void supposeHasBackgroundColor() {
		
		//Asserts that the current area has a background color.
		if (!hasBackgroundColor()) {
			throw new ArgumentDoesNotHaveAttributeException(
				this,
				VariableNameCatalogue.BACKGROUND_COLOR
			);
		}
	}
}
