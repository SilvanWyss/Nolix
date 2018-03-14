//package declaration
package ch.nolix.element.GUI;

//Java import
import java.awt.Graphics;

//own imports
import ch.nolix.core.container.ReadContainer;
import ch.nolix.core.container.List;
import ch.nolix.core.specification.StandardSpecification;
import ch.nolix.element.color.Color;
import ch.nolix.element.intData.Length;
import ch.nolix.element.intData.Thickness;
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
 * @lines 260
 * @param <L> The type of a line.
 */
public abstract class Line<L extends Line<L>>
extends Widget<L, LineStructure> {
	
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
	public final void addOrChangeAttribute(final StandardSpecification attribute) {
		
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
	 * @return the widgets of this line.
	 */
	public final ReadContainer<Widget<?, ?>> getRefWidgets() {
		return new ReadContainer<>();
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
	 * Resets the configuration of this line.
	 */
	public final void resetConfiguration() {
		
		//Calls method of the base class.
		super.resetConfiguration();
		
		setLength(DEFAULT_LENGTH);
		setThickness(DEFAULT_THICKNESS);
		setColor(DEFAULT_COLOR);
	}
	
	//method
	/**
	 * Sets the color of this line.
	 * 
	 * @param color
	 * @return this line.
	 * @throws NullArgumentException if the given color is null.
	 */
	@SuppressWarnings("unchecked")
	public final L setColor(final Color color) {
		
		//Checks if the given color is not null.
		Validator.suppose(color).thatIsInstanceOf(Color.class).isNotNull();
		
		//Sets the color of this line.
		this.color = color;
		
		return (L)this;
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
	@SuppressWarnings("unchecked")
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
		
		return (L)this;
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
	@SuppressWarnings("unchecked")
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
		
		return (L)this;
	}
	
	//method
	/**
	 * @return a new widget structure for this line.
	 */
	protected final LineStructure createWidgetStructure() {
		return new LineStructure();
	}
	
	//method
	/**
	 * Paints this line using the given widget structure and graphics.
	 * 
	 * @param widgetStructure
	 * @param graphics
	 */
	protected final void paint(
		final LineStructure widgetStructure,
		final Graphics graphics
	) {
		graphics.setColor(color.getJavaColor());
		graphics.fillRect(
			getXPositionOnContainer(),
			getYPositionOnContainer(),
			getWidth(),
			getHeight()
		);
	}
}
