//package declaration
package ch.nolix.element.GUI;

//own imports
import ch.nolix.core.constants.PascalCaseNameCatalogue;
import ch.nolix.core.constants.VariableNameCatalogue;
import ch.nolix.core.container.List;
import ch.nolix.core.documentNode.DocumentNode;
import ch.nolix.core.documentNode.DocumentNodeoid;
import ch.nolix.core.invalidArgumentException.InvalidArgumentException;
import ch.nolix.core.validator2.Validator;
import ch.nolix.element.color.Color;
import ch.nolix.element.core.PositiveInteger;
import ch.nolix.element.painter.IPainter;

//abstract class
/**
 * The width of a line is bigger than its thickness.
 * 
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 290
 * @param <L> The type of a line.
 */
public abstract class Line<L extends Line<L>>
extends Widget<L, LineLook> {
	
	//type name
	public static final String TYPE_NAME = "Line";
	
	//min length to thickness ratio
	public static final int MIN_LENGTH_TO_THICKNESS_RATIO = 4;
	
	//default values
	public static final int DEFAULT_LENGTH = 100;
	public static final int DEFAULT_THICKNESS = 1;
	public static final Color DEFAULT_COLOR = Color.BLACK;
	
	//attributes
	private PositiveInteger length;
	private PositiveInteger thickness;
	private Color color;
	
	//method
	/**
	 * Adds or change the given attribute to this line.
	 * 
	 * @param attribute
	 * @throws InvalidArgumentException if the given attribute is not valid.
	 */
	@Override
	public final void addOrChangeAttribute(final DocumentNodeoid attribute) {
		
		//Enumerates the header of the given attribute.
		switch (attribute.getHeader()) {
			case PascalCaseNameCatalogue.LENGTH:
				setLength(attribute.getOneAttributeAsInt());
				break;
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
	 * @return the active cursor icon of the current {@link Line}.
	 */
	@Override
	public CursorIcon getCursorIcon() {
		return getCustomCursorIcon();
	}
	
	//method
	/**
	 * @return the attributes of this line.
	 */
	@Override
	public final List<DocumentNode> getAttributes() {
		
		//Calls method of the base class.
		final List<DocumentNode> attributes = super.getAttributes();
		
		if (getLength() != DEFAULT_LENGTH) {
			attributes.addAtEnd(length.getSpecificationAs(PascalCaseNameCatalogue.LENGTH));
		}
		
		if (getThickness() != DEFAULT_THICKNESS) {
			attributes.addAtEnd(thickness.getSpecificationAs(PascalCaseNameCatalogue.THICKNESS));
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
	
	//method
	/**
	 * @return the length of this line.
	 */
	public final int getLength() {
		return length.getValue();
	}
	
	//method
	/**
	 * @return the thickness of this line.
	 */
	public final int getThickness() {
		return thickness.getValue();
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
	 * {@inheritDoc}
	 */
	@Override
	public boolean keepsFocus() {
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
				
		setLength(DEFAULT_LENGTH);
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
	 * @throws NullArgumentException if the given color is null.
	 */
	public final L setColor(final Color color) {
		
		//Checks if the given color is not null.
		Validator.suppose(color).isOfType(Color.class);
		
		//Sets the color of this line.
		this.color = color;
		
		return asConcreteType();
	}
	
	//method
	/**
	 * Sets the length of this line.
	 * 
	 * @param length
	 * @return this line.
	 * @throws InvalidArgumentException if the given length
	 * is smaller than 4 times the thickness of this line.
	 */
	public final L setLength(final int length) {
		
		//Checks if the given length is not smaller than 4 times the thickness of this line.
		if (length < MIN_LENGTH_TO_THICKNESS_RATIO * getThickness()) {
			throw new InvalidArgumentException(
				VariableNameCatalogue.LENGTH,
				length,
				"is smaller than "
				+ MIN_LENGTH_TO_THICKNESS_RATIO
				+ "*thickness "
			);
		}
		
		this.length = new PositiveInteger(length);
		
		return asConcreteType();
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
		
		this.thickness = new PositiveInteger(thickness);
		
		return asConcreteType();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void applyUsableConfigurationWhenConfigurationIsReset() {
		setThickness(1);
	}
	
	//method
	/**
	 * @return a new widget look for this line.
	 */
	@Override
	protected final LineLook createWidgetLook() {
		return new LineLook();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void fillUpOwnWidgets(final List<Widget<?, ?>> list) {}
	
	//method
	/**
	 * Paints this line using the given widget structure and graphics.
	 * 
	 * @param widgetStructure
	 * @param painter
	 */
	@Override
	protected final void paint(
		final LineLook widgetStructure,
		final IPainter painter
	) {
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
	protected boolean viewAreaIsUnderCursor() {
		return isUnderCursor();
	}
}
