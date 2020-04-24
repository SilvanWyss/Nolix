//package declaration
package ch.nolix.element.widgets;

import ch.nolix.common.constant.PascalCaseNameCatalogue;
import ch.nolix.common.constant.VariableNameCatalogue;
import ch.nolix.common.containers.LinkedList;
import ch.nolix.common.invalidArgumentExceptions.InvalidArgumentException;
import ch.nolix.common.node.BaseNode;
import ch.nolix.common.node.Node;
import ch.nolix.common.validator.Validator;
import ch.nolix.element.GUI.Widget;
import ch.nolix.element.color.Color;
import ch.nolix.element.painter.IPainter;

//class
/**
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 230
 * @param <L> The type of a line.
 */
public abstract class Line<L extends Line<L>> extends Widget<L, LineLook> {
	
	//type name
	public static final String TYPE_NAME = "Line";
	
	//min length to thickness ratio
	public static final int MIN_LENGTH_TO_THICKNESS_RATIO = 4;
	
	//default values
	public static final int DEFAULT_LENGTH = 100;
	public static final int DEFAULT_THICKNESS = 1;
	public static final Color DEFAULT_COLOR = Color.BLACK;
	
	//attributes
	private int thickness;
	private Color color;
	
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
				setColor(new Color(attribute.getOneAttributeAsString()));
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
			attributes.addAtEnd(new Node(PascalCaseNameCatalogue.THICKNESS, thickness));
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
	 * Resets the configuration of this line.
	 * 
	 * @return this line.
	 */
	@Override
	public final L resetConfiguration() {
		
		setThickness(DEFAULT_THICKNESS);
		setColor(DEFAULT_COLOR);
		
		//Calls method of the base class.
		return super.resetConfiguration();
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
	public boolean viewAreaIsUnderCursor() {
		return isUnderCursor();
	}

	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void applyDefaultConfigurationWhenHasBeenReset() {
		setThickness(DEFAULT_THICKNESS);
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
	protected final void fillUpPaintableWidgets(final LinkedList<Widget<?, ?>> list) {}
	
	//method
	/**
	 * Paints this line using the given widget structure and graphics.
	 * 
	 * @param widgetStructure
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
}
