//package declaration
package ch.nolix.system.gui.widget;

//own imports
import ch.nolix.core.container.main.LinkedList;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.invalidargumentexception.NonPositiveArgumentException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;
import ch.nolix.core.programatom.name.PascalCaseCatalogue;
import ch.nolix.system.element.mutableelement.MutableOptionalValue;
import ch.nolix.system.element.mutableelement.MutableValue;
import ch.nolix.system.gui.color.Color;
import ch.nolix.systemapi.guiapi.inputapi.Key;
import ch.nolix.systemapi.guiapi.painterapi.IPainter;
import ch.nolix.systemapi.guiapi.processproperty.RotationDirection;
import ch.nolix.systemapi.guiapi.widgetguiapi.IWidget;

//class
/**
 * A {@link Area} is a {@link Widget} that:
 * -Has a specific width and height.
 * -Can have a background color.
 * 
 * @author Silvan Wyss
 * @date 2016-01-01
 */
public final class Area extends Widget<Area, AreaLook> {
	
	//constants
	public static final int DEFAULT_WIDTH = 500;
	public static final int DEFAULT_HEIGHT = 200;
	
	//constants
	private static final String WIDTH_HEADER = PascalCaseCatalogue.WIDTH;
	private static final String HEIGHT_HEADER = PascalCaseCatalogue.HEIGHT;
	private static final String BACKGROUND_COLOR_HEADER = PascalCaseCatalogue.BACKGROUND_COLOR;
	
	//attributes
	private final MutableValue<Integer> width = MutableValue.forInt(WIDTH_HEADER, DEFAULT_WIDTH, this::setWidth);
	private final MutableValue<Integer> height = MutableValue.forInt(HEIGHT_HEADER, DEFAULT_HEIGHT, this::setHeight);
	
	//attribute
	private final MutableOptionalValue<Color> backgroundColor =
	new MutableOptionalValue<>(
		BACKGROUND_COLOR_HEADER,
		this::setBackgroundColor,
		Color::fromSpecification,
		Color::getSpecification
	);
	
	//constructor
	/**
	 * Creates a new {@link Area}.
	 */
	public Area() {
		
		reset();
		
		setBackgroundColor(Color.LIGHT_GREY);
	}
	
	//method
	/**
	 * @return the background color of the current {@link Area}.
	 * @throws ArgumentDoesNotHaveAttributeException if the current {@link Area} does not have a background color.
	 */
	public Color getBackgroundColor() {
		return backgroundColor.getValue();
	}
	
	//method
	/**
	 * @return true if the current {@link Area} has a background color.
	 */
	public boolean hasBackgroundColor() {
		return backgroundColor.hasValue();
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
	 */
	public void removeBackgroundColor() {
		backgroundColor.clear();
	}
	
	//method
	/**
	 * Sets the background color of the current {@link Area}.
	 * 
	 * @param backgroundColor
	 * @return the current {@link Area}.
	 * @throws ArgumentIsNullException if the given backgroundColor is null.
	 */
	public Area setBackgroundColor(final Color backgroundColor) {
		
		this.backgroundColor.setValue(backgroundColor);
		
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
		
		GlobalValidator.assertThat(height).thatIsNamed(LowerCaseCatalogue.HEIGHT).isPositive();
		
		this.height.setValue(height);
		
		return this;
	}
	
	//method
	/**
	 * Sets the size of the current {@link Area}.
	 * 
	 * @param width
	 * @param height
	 * @return the current {@link Area}.
	 * @throws NonPositiveArgumentException if the given width is not positive.
	 * @throws NonPositiveArgumentException if the given height is not positive.
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
	 * @throws NonPositiveArgumentException if the given width is not positive.
	 */
	public Area setWidth(final int width) {
		
		GlobalValidator.assertThat(width).thatIsNamed(LowerCaseCatalogue.WIDTH).isPositive();
		
		this.width.setValue(width);
		
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
	protected void fillUpChildWidgets(final LinkedList<IWidget<?, ?>> list) {
		//Does nothing.
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void fillUpWidgetsForPainting(final LinkedList<IWidget<?, ?>> list) {
		//Does nothing.
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected int getHeightWhenExpanded() {
		return height.getValue();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected int getWidthWhenExpanded() {
		return width.getValue();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void noteKeyDownOnSelfWhenFocused(final Key key) {
		//Does nothing.
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void noteKeyReleaseOnSelfWhenFocused(final Key key) {
		//Does nothing.
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void noteKeyTypingOnSelfWhenFocused(final Key key) {
		//Does nothing.
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void noteLeftMouseButtonClickOnSelfWhenEnabled() {
		//Does nothing.
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void noteLeftMouseButtonPressOnSelfWhenEnabled() {
		//Does nothing.
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void noteLeftMouseButtonReleaseOnSelfWhenEnabled() {
		//Does nothing.
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void noteMouseMoveOnSelfWhenEnabled() {
		//Does nothing.
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void noteMouseWheelClickOnSelfWhenEnabled() {
		//Does nothing.
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void noteMouseWheelPressOnSelfWhenEnabled() {
		//Does nothing.
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void noteMouseWheelReleaseOnSelfWhenEnabled() {
		//Does nothing.
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void noteMouseWheelRotationStepOnSelfWhenFocused(final RotationDirection rotationDirection) {
		//Does nothing.
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void noteRightMouseButtonClickOnSelfWhenEnabled() {
		//Does nothing.
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void noteRightMouseButtonPressOnSelfWhenEnabled() {
		//Does nothing.
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void noteRightMouseButtonReleaseOnSelfWhenEnabled() {
		//Does nothing.
	}
	
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
	protected void recalculateWidgetSelf() {
		//Does nothing.
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void resetWidgetConfiguration() {
		
		setWidth(DEFAULT_WIDTH);
		setHeight(DEFAULT_HEIGHT);
		
		removeBackgroundColor();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void resetWidget() {
		//Does nothing.
	}
}
