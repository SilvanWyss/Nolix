//package declaration
package ch.nolix.element.gui.widget;

//own imports
import ch.nolix.common.constant.PascalCaseCatalogue;
import ch.nolix.common.constant.LowerCaseCatalogue;
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.document.node.BaseNode;
import ch.nolix.common.document.node.Node;
import ch.nolix.common.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.common.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.common.errorcontrol.invalidargumentexception.NonPositiveArgumentException;
import ch.nolix.common.errorcontrol.validator.Validator;
import ch.nolix.element.elementenum.RotationDirection;
import ch.nolix.element.gui.base.Widget;
import ch.nolix.element.gui.color.Color;
import ch.nolix.element.gui.input.Key;
import ch.nolix.element.gui.painterapi.IPainter;

//class
/**
 * A {@link Area} is a {@link Widget} that:
 * -Has a specific width and height.
 * -Can have a background color.
 * 
 * @author Silvan Wyss
 * @date 2016-01-01
 * @lines 410
 */
public final class Area extends Widget<Area, OldAreaLook> {
	
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
			case PascalCaseCatalogue.WIDTH:
				setWidth(attribute.getOneAttributeAsInt());
				break;
			case PascalCaseCatalogue.HEIGHT:
				setHeight(attribute.getOneAttributeAsInt());
				break;
			case PascalCaseCatalogue.BACKGROUND_COLOR:
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
	public void fillUpAttributesInto(final LinkedList<Node> list) {
		
		//Calls method of base class.
		super.fillUpAttributesInto(list);
		
		list.addAtEnd(
			Node.withHeaderAndAttribute(PascalCaseCatalogue.HEIGHT, height),
			Node.withHeaderAndAttribute(PascalCaseCatalogue.WIDTH, width)
		);
		
		//Handles the case that the current Area has a background color.
		if (hasBackgroundColor()) {
			list.addAtEnd(getBackgroundColor().getSpecificationAs(PascalCaseCatalogue.BACKGROUND_COLOR));
		}
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
	 * @throws ArgumentIsNullException if the given background color is null.
	 */
	public Area setBackgroundColor(final Color backgroundColor) {
		
		//Asserts that the given background color is not null.
		Validator
		.assertThat(backgroundColor)
		.thatIsNamed(LowerCaseCatalogue.BACKGROUND_COLOR)
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
		
		Validator.assertThat(height).thatIsNamed(LowerCaseCatalogue.HEIGHT).isPositive();
		
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
		
		Validator.assertThat(width).thatIsNamed(LowerCaseCatalogue.WIDTH).isPositive();
		
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
	protected OldAreaLook createLook() {
		return new OldAreaLook();
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
	protected int getHeightWhenExpanded() {
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
	protected void noteKeyPressOnSelfWhenFocused(final Key key) {}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void noteKeyReleaseOnSelfWhenFocused(final Key key) {}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void noteKeyTypingOnSelfWhenFocused(final Key key) {}
	
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
	protected void noteMouseWheelRotationStepOnSelfWhenFocused(final RotationDirection rotationDirection) {}
	
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
	protected void paint(final IPainter painter, final OldAreaLook areaLook) {
		
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
	protected void recalculateWidgetSelf() {}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void resetWidgetConfigurationOnSelf() {
		setWidth(500);
		setHeight(200);
		removeBackgroundColor();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void resetWidget() {}
	
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
				LowerCaseCatalogue.BACKGROUND_COLOR
			);
		}
	}
}
