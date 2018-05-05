//package declaration
package ch.nolix.element.color;

//Java import
import java.awt.GradientPaint;

//own imports
import ch.nolix.core.container.IContainer;
import ch.nolix.core.container.List;
import ch.nolix.core.enums.UniDirection;
import ch.nolix.core.specification.Specification;
import ch.nolix.core.specification.StandardSpecification;
import ch.nolix.element.core.Element;
import ch.nolix.primitive.invalidStateException.InvalidStateException;
import ch.nolix.primitive.validator2.Validator;

//class
/**
 * A color gradient specifies two colors and an uni direction.
 * A color gradient is not mutable.
 * 
 * @author Silvan Wyss
 * @month 2016-07
 * @lines 240
 */
public class ColorGradient extends Element {
	
	//constant
	public static final ColorGradient VERTICAL_BLACK_WHITE_COLOR_GRADIENT =
	new ColorGradient(UniDirection.Vertical, Color.BLACK, Color.WHITE);
	
	//constant
	public static final ColorGradient VERTICAL_RED_WHITE_COLOR_GRADIENT =
	new ColorGradient(UniDirection.Vertical, Color.RED, Color.WHITE);
	
	//default values
	public static final UniDirection DEFAULT_DIRECTION = UniDirection.Vertical;
	public static final Color DEFAULT_COLOR1 = Color.BLACK;
	public static final Color DEFAULT_COLOR2 = Color.WHITE;
	
	//constants
	private static final String DIRECTION_HEADER = "Direction";
	private static final String COLOR_1_HEADER = "Color1";
	private static final String COLOR_2_HEADER = "Color2";

	//static method
	/**
	 * Creates new color gradient from the given specification.
	 * 
	 * @param specification
	 * @return a new color gradient from the given specification.
	 * @throws InvalidArgumentException if the given specification is not valid.
	 */
	public static ColorGradient createFromSpecification(
		final Specification specification
	) {
		
		final IContainer<Specification> attributes =
		specification.getRefAttributes();
		
		return new ColorGradient(
			UniDirection.valueOf(attributes.getRefAt(1).toString()),
			Color.createFromSpecification(attributes.getRefAt(2)),
			Color.createFromSpecification(attributes.getRefAt(3))
		);
	}
	
	//attributes
	private final UniDirection direction;
	private final Color color1;
	private final Color color2;
	
	//constructor
	/**
	 * Creates a new color gradient.
	 */
	public ColorGradient() {
			
		//Calls other constructor.
		this(DEFAULT_DIRECTION, DEFAULT_COLOR1, DEFAULT_COLOR2);
	}
	
	//constructor
	/**
	 * Creates a new color gradient with the given color 1 and color 2.
	 * 
	 * @param direction
	 * @param color1
	 * @param color2
	 * @throws NullArgumentException if the given direction is null.
	 * @throws NullArgumentException if the given color 1 is null.
	 * @throws NullArgumentException if the given color 2 is null.
	 */
	public ColorGradient(
		final Color color1,
		final Color color2
	) {
		//Calls other constructor.
		this(DEFAULT_DIRECTION, color1, color2);
	}
	
	//constructor
	/**
	 * Creates a new color gradient with the given direction.
	 * 
	 * @param direction
	 * @throws NullArgumentException if the given direction is null.
	 */
	public ColorGradient(final UniDirection direction) {
		
		//Calls other constructor.
		this(direction, DEFAULT_COLOR1, DEFAULT_COLOR2);
	}
	
	//constructor
	/**
	 * Creates a new color gradient with the given direction, color 1 and color 2.
	 * 
	 * @param direction
	 * @param color1
	 * @param color2
	 * @throws NullArgumentException if the given direction is null.
	 * @throws NullArgumentException if the given color 1 is null.
	 * @throws NullArgumentException if the given color 2 is null.
	 */
	public ColorGradient(
		final UniDirection direction,
		final Color color1,
		final Color color2
	) {
		//Checks if the given direction is not null.
		Validator.suppose(direction).thatIsNamed("direction").isNotNull();
		
		//Checks if the given color 1 is not null.
		Validator.suppose(color1).thatIsNamed("color 1").isNotNull();
		
		//Checks if the given color 2 is not null.
		Validator.suppose(color2).thatIsNamed("color 2").isNotNull();
		
		this.direction = direction;
		this.color1 = color1;
		this.color2 = color2;
	}
	
	
	//method
	/**
	 * @param xPosition
	 * @param yPosition
	 * @param width
	 * @param height
	 * @return a new Swing gradient paint of this color gradient
	 * for the given position and size.
	 */
	public GradientPaint createSwingGradientPaint(
		final int xPosition,
		final int yPosition,
		final int width,
		final int height
	) {
		//Enumerates the direction of this color gradient.
		switch (getDirection()) {
			case Horizontal:
				return
				new GradientPaint(
					xPosition,
					yPosition,
					getColor1().createSwingColor(),
					xPosition + width,
					yPosition,
					getColor2().createSwingColor()
				);
			case Vertical:
				return
				new GradientPaint(
					xPosition,
					yPosition,
					getColor1().createSwingColor(),
					xPosition,
					yPosition + height,
					getColor2().createSwingColor()
				);
			case DiagonalUp:
				return
				new GradientPaint(
					xPosition,
					yPosition,
					getColor1().createSwingColor(),
					xPosition + width,
					yPosition + height,
					getColor2().createSwingColor()
				);
			case DiagonalDown:
				return
				new GradientPaint(
					xPosition,
					yPosition,
					getColor2().createSwingColor(),
					xPosition + width,
					yPosition + height,
					getColor1().createSwingColor()
				);
			default:
				
				throw new InvalidStateException(this);
		}
	}
			
	//method
	/**
	 * @return the attributes of this color gradient.
	 */
	public List<StandardSpecification> getAttributes() {
		return 
		new List<StandardSpecification>(
			new StandardSpecification(
				DIRECTION_HEADER,
				getDirection().toString()
			),		
			getColor1().getSpecificationAs(COLOR_1_HEADER),
			getColor2().getSpecificationAs(COLOR_2_HEADER)
		);
	}
	
	//method
	/**
	 * @return the color 1 this color gradient.
	 */
	public Color getColor1() {
		return color1;
	}
	
	//method
	/**
	 * @return the color 2 of this color gradient.
	 */
	public Color getColor2() {
		return color2;
	}
	
	//method
	/**
	 * @return the direction of this color gradient.
	 */
	public UniDirection getDirection() {
		return direction;
	}
}
