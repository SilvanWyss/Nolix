//package declaration
package ch.nolix.element.color;

//Java import
import java.awt.GradientPaint;

//own imports
import ch.nolix.core.container.List;
import ch.nolix.core.documentNode.DocumentNode;
import ch.nolix.core.documentNode.DocumentNodeoid;
import ch.nolix.core.enums.UniDirection;
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

	//static method
	/**
	 * Creates new color gradient from the given specification.
	 * 
	 * @param specification
	 * @return a new color gradient from the given specification.
	 * @throws InvalidArgumentException if the given specification is not valid.
	 */
	public static ColorGradient createFromSpecification(
		final DocumentNodeoid specification
	) {
		
		final var attributes = specification.getRefAttributes();
		
		final var directionSpecification = new DocumentNode();
		directionSpecification.addAttribute(attributes.getRefAt(1));
		
		final var color1Specification = new DocumentNode();
		color1Specification.addAttribute(attributes.getRefAt(2));
		
		final var color2Specification = new DocumentNode();
		color2Specification.addAttribute(attributes.getRefAt(3));
		
		return new ColorGradient(
			UniDirection.createFromSpecification(directionSpecification),
			Color.createFromSpecification(color1Specification),
			Color.createFromSpecification(color2Specification)
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
	 * @throws NullArgumentException if the given direction is not an instance.
	 * @throws NullArgumentException if the given color 1 is not an instance.
	 * @throws NullArgumentException if the given color 2 is not an instance.
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
	 * @throws NullArgumentException if the given direction is not an instance.
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
	 * @throws NullArgumentException if the given direction is not an instance.
	 * @throws NullArgumentException if the given color 1 is not an instance.
	 * @throws NullArgumentException if the given color 2 is not an instance.
	 */
	public ColorGradient(
		final UniDirection direction,
		final Color color1,
		final Color color2
	) {
		//Checks if the given direction is an instance.
		Validator.suppose(direction).thatIsNamed("direction").isInstance();
		
		//Checks if the given color 1 is an instance.
		Validator.suppose(color1).thatIsNamed("color 1").isInstance();
		
		//Checks if the given color 2 is an instance.
		Validator.suppose(color2).thatIsNamed("color 2").isInstance();
		
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
	public List<DocumentNode> getAttributes() {
		return 
		new List<DocumentNode>(
			DocumentNode.createSpecificationWithHeader(getDirection().toString()),
			DocumentNode.createSpecificationWithHeader(getColor1().getStringValue()),
			DocumentNode.createSpecificationWithHeader(getColor2().getStringValue())
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

	//method
	/**
	 * @param appendAlphaValueAlways
	 * @return a hexadecimal specification of this color gradient.
	 */
	public DocumentNode getHexadecimalSpecification(
		final boolean appendAlphaValueAlways
	) {
		return new DocumentNode(
			getType(),
			color1.getHexadecimalSpecification(appendAlphaValueAlways),
			color2.getHexadecimalSpecification(appendAlphaValueAlways)
		);
	}
}
