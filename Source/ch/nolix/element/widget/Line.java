//package declaration
package ch.nolix.element.widget;

//own imports
import ch.nolix.common.constant.PascalCaseNameCatalogue;
import ch.nolix.common.constant.VariableNameCatalogue;
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.common.invalidargumentexception.InvalidArgumentException;
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
 * @author Silvan Wyss
 * @date 2016-01-01
 * @lines 350
 * @param <L> is the type of a line.
 */
public abstract class Line<L extends Line<L>> extends Widget<L, LineLook> {
	
	//constant
	public static final String TYPE_NAME = "Line";
	
	//min length to thickness ratio
	public static final int MIN_LENGTH_TO_THICKNESS_RATIO = 4;
	
	//constants
	public static final int DEFAULT_LENGTH = 100;
	public static final int DEFAULT_THICKNESS = 1;
	public static final Color DEFAULT_COLOR = Color.BLACK;
	
	//attributes
	private int thickness;
	private Color color;
	
	//constructor
	/**
	 * Creates a new {@link Line}.
	 */
	public Line() {
		setThickness(DEFAULT_THICKNESS);
	}
	
	//method
	/**
	 * Adds or change the given attribute to this line.
	 * 
	 * @param attribute
	 * @throws InvalidArgumentException if the given attribute is not valid.
	 */
	@Override
	public final void addOrChangeAttribute(final BaseNode attribute) {
		
		//Enumerates the header of the given attribute.
		switch (attribute.getHeader()) {
			case PascalCaseNameCatalogue.THICKNESS:
				setThickness(attribute.getOneAttributeAsInt());
				break;
			case Color.TYPE_NAME:
				setColor(new Color(attribute.getOneAttributeHeader()));
				break;
			default:
				
				//Calls method of the base class.
				super.addOrChangeAttribute(attribute);
		}
	}
	
	//method
	/**
	 * @return the attributes of this line.
	 */
	@Override
	public final LinkedList<Node> getAttributes() {
		
		//Calls method of the base class.
		final LinkedList<Node> attributes = super.getAttributes();
		
		if (getThickness() != DEFAULT_THICKNESS) {
			attributes.addAtEnd(Node.withHeaderAndAttribute(PascalCaseNameCatalogue.THICKNESS, thickness));
		}
		
		if (!getColor().equals(DEFAULT_COLOR)) {
			attributes.addAtEnd(color.getSpecification());
		}
		
		return attributes;
	}
	
	//method
	/**
	 * @return the color of this line.
	 */
	public final Color getColor() {
		return color;
	}
	
	//method declaration
	/**
	 * @return the length of this line.
	 */
	public abstract int getLength();
	
	//method
	/**
	 * @return the thickness of this line.
	 */
	public final int getThickness() {
		return thickness;
	}
	
	//method
	/**
	 * @param role
	 * @return true if this line has the given role.
	 */
	@Override
	public final boolean hasRole(final String role) {
		return false;
	}
	
	//method
	/**
	 * Sets the color of this line.
	 * 
	 * @param color
	 * @return this line.
	 * @throws ArgumentIsNullException if the given color is null.
	 */
	public final L setColor(final Color color) {
		
		//Asserts that the given color is not null.
		Validator.assertThat(color).isOfType(Color.class);
		
		//Sets the color of this line.
		this.color = color;
		
		return asConcrete();
	}
	
	//method
	/**
	 * Sets the thickness of this line.
	 * 
	 * @param thickness
	 * @return this line.
	 * @throws InvalidArgumentException if 4 times the given thickness
	 * is bigger than the length of this line.
	 */
	public final L setThickness(final int thickness) {
		
		//Checks the given thickness.
		if (MIN_LENGTH_TO_THICKNESS_RATIO * thickness > getLength()) {
			throw new InvalidArgumentException(
				VariableNameCatalogue.THICKNESS,
				thickness,
				"is bigger than length/"
				+ MIN_LENGTH_TO_THICKNESS_RATIO
			);
		}
		
		this.thickness = thickness;
		
		return asConcrete();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final boolean showAreaIsUnderCursor() {
		return isUnderCursor();
	}
	
	//method
	/**
	 * @return a new widget look for this line.
	 */
	@Override
	protected final LineLook createLook() {
		return new LineLook();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void fillUpChildWidgets(final LinkedList<Widget<?, ?>> list) {}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void fillUpShownWidgets(final LinkedList<Widget<?, ?>> list) {}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void noteKeyPressOnSelfWhenEnabled(final Key key) {}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void noteKeyReleaseOnSelfWhenEnabled(final Key key) {}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void noteKeyTypingOnSelfWhenEnabled(final Key key) {}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void noteLeftMouseButtonClickOnSelfWhenEnabled() {}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void noteLeftMouseButtonPressOnSelfWhenEnabled() {}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void noteLeftMouseButtonReleaseOnSelfWhenEnabled() {}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void noteMouseMoveOnSelfWhenEnabled() {}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void noteMouseWheelClickOnSelfWhenEnabled() {}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void noteMouseWheelPressOnSelfWhenEnabled() {}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void noteMouseWheelReleaseOnSelfWhenEnabled() {}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void noteMouseWheelRotationStepOnSelfWhenEnabled(final RotationDirection rotationDirection) {}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void noteRightMouseButtonClickOnSelfWhenEnabled() {}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void noteRightMouseButtonPressOnSelfWhenEnabled() {}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void noteRightMouseButtonReleaseOnSelfWhenEnabled() {}
	
	//method
	/**
	 * Paints this line using the given widget structure and graphics.
	 * 
	 * @param lineLook
	 * @param painter
	 */
	@Override
	protected final void paint(final IPainter painter, final LineLook lineLook) {
		painter.setColor(color);
		painter.paintFilledRectangle(
			getWidth(),
			getHeight()
		);
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void recalculateWidgetSelf() {}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void resetWidgetConfigurationOnSelf() {
		setThickness(DEFAULT_THICKNESS);
		setColor(DEFAULT_COLOR);
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void resetWidget() {}
}
