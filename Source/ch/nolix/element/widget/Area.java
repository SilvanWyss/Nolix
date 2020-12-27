//package declaration
package ch.nolix.element.widget;

//own imports
import ch.nolix.common.constant.PascalCaseNameCatalogue;
import ch.nolix.common.constant.VariableNameCatalogue;
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.common.node.BaseNode;
import ch.nolix.common.node.Node;
import ch.nolix.common.validator.Validator;
import ch.nolix.element.color.Color;
import ch.nolix.element.elementenum.RotationDirection;
import ch.nolix.element.gui.Widget;
import ch.nolix.element.input.Key;
import ch.nolix.element.painterapi.IPainter;

//class
/**
 * A {@link Area} is a {@link Widget} that:
 * -Has a specific width and height.
 * -Can have a background color.
 * 
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 420
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
		setBackgroundColor(Color.LIGHT_GREY);
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
		.addAtEnd(Node.withHeaderAndAttribute(PascalCaseNameCatalogue.HEIGHT, height))
		.addAtEnd(Node.withHeaderAndAttribute(PascalCaseNameCatalogue.WIDTH, width));
		
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
	public boolean showAreaIsUnderCursor() {
		return isUnderCursor();
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
	protected void fillUpShownWidgets(final LinkedList<Widget<?, ?>> list) {}
	
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
	protected int getWidthWhenExpanded() {
		return width;
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void noteKeyPressOnSelfWhenEnabled(final Key key) {}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void noteKeyReleaseOnSelfWhenEnabled(final Key key) {}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void noteKeyTypingOnSelfWhenEnabled(final Key key) {}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void noteLeftMouseButtonClickOnSelfWhenEnabled() {}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void noteLeftMouseButtonPressOnSelfWhenEnabled() {}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void noteLeftMouseButtonReleaseOnSelfWhenEnabled() {}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void noteMouseMoveOnSelfWhenEnabled() {}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void noteMouseWheelClickOnSelfWhenEnabled() {}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void noteMouseWheelPressOnSelfWhenEnabled() {}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void noteMouseWheelReleaseOnSelfWhenEnabled() {}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void noteMouseWheelRotationStepOnSelfWhenEnabled(final RotationDirection rotationDirection) {}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void noteRightMouseButtonClickOnSelfWhenEnabled() {}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void noteRightMouseButtonPressOnSelfWhenEnabled() {}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void noteRightMouseButtonReleaseOnSelfWhenEnabled() {}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void paint(final IPainter painter, final AreaLook areaLook) {
		
		//Handles the case that the current Area has a background color.
		if (hasBackgroundColor()) {
			painter.setColor(getBackgroundColor());
			painter.paintFilledRectangle(getWidth(), getHeight());
		}
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void recalculateSelfStage2() {}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected boolean redirectsInputsToShownWidgets() {
		return true;
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void resetConfigurationOnSelfStage2() {
		setWidth(500);
		setHeight(200);
		removeBackgroundColor();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void resetStage3() {}
	
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
