//package declaration
package ch.nolix.element.GUI;

//own imports
import ch.nolix.core.container.List;
import ch.nolix.core.specification.Specification;
import ch.nolix.core.specification.StandardSpecification;
import ch.nolix.element.color.Color;
import ch.nolix.element.intData.Length;
import ch.nolix.element.intData.Thickness;
import ch.nolix.element.painter.IPainter;
import ch.nolix.primitive.invalidArgumentException.Argument;
import ch.nolix.primitive.invalidArgumentException.ArgumentName;
import ch.nolix.primitive.invalidArgumentException.ErrorPredicate;
import ch.nolix.primitive.invalidArgumentException.InvalidArgumentException;
import ch.nolix.primitive.validator2.Validator;

//abstract class
/**
 * The width of a line is bigger than its thickness.
 * 
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 280
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
	private Length length;
	private Thickness thickness;
	private Color color;
	
	//method
	/**
	 * Adds or change the given attribute to this line.
	 * 
	 * @param attribute
	 * @throws InvalidArgumentException if the given attribute is not valid.
	 */
	public final void addOrChangeAttribute(final Specification attribute) {
		
		//Enumerates the header of the given attribute.
		switch (attribute.getHeader()) {
			case Length.TYPE_NAME:
				setLength(attribute.getOneAttributeAsInt());
				break;
			case Thickness.TYPE_NAME:
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
	public CursorIcon getActiveCursorIcon() {
		return getCursorIcon();
	}
	
	//method
	/**
	 * @return the attributes of this line.
	 */
	public final List<StandardSpecification> getAttributes() {	
		
		//Calls method of the base class.
		final List<StandardSpecification> attributes = super.getAttributes();
		
		if (getLength() != DEFAULT_LENGTH) {
			attributes.addAtEnd(length.getSpecification());
		}
		
		if (getThickness() != DEFAULT_THICKNESS) {
			attributes.addAtEnd(thickness.getSpecification());
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
	public final boolean hasRole(final String role) {
		return false;
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	public boolean keepsFocus() {
		return false;
	}
	
	//method
	/**
	 * Resets the configuration of this line.
	 * 
	 * @return this line.
	 */
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
		Validator.suppose(color).thatIsOfType(Color.class).isNotNull();
		
		//Sets the color of this line.
		this.color = color;
		
		return getInstance();
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
				new ArgumentName("length"),
				new Argument(length),
				new ErrorPredicate(
					"is smaller than "
					+ MIN_LENGTH_TO_THICKNESS_RATIO
					+ "x the thickness "
					+ getThickness() + "."
				)
			);
		}
		
		this.length = new Length(length);
		
		return getInstance();
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
				new ArgumentName("thickness"),
				new Argument(thickness),
				new ErrorPredicate(
					"multiplied with "
					+ MIN_LENGTH_TO_THICKNESS_RATIO
					+ "x is bigger than the length "
					+ getLength()
					+ "."
				)
			);
		}
		
		this.thickness = new Thickness(thickness);
		
		return getInstance();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	protected final void applyUsableConfigurationWhenConfigurationIsReset() {
		setThickness(1);
	}
	
	//method
	/**
	 * @return a new widget look for this line.
	 */
	protected final LineLook createWidgetLook() {
		return new LineLook();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	protected void fillUpWidgets(final List<Widget<?, ?>> list) {}
	
	//method
	/**
	 * Paints this line using the given widget structure and graphics.
	 * 
	 * @param widgetStructure
	 * @param painter
	 */
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
	protected boolean viewAreaIsUnderCursor() {
		return isUnderCursor();
	}
}
