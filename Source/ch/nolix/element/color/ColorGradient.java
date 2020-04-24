//package declaration
package ch.nolix.element.color;

//Java import
import java.awt.GradientPaint;

import ch.nolix.common.container.LinkedList;
import ch.nolix.common.invalidArgumentException.InvalidArgumentException;
import ch.nolix.common.node.BaseNode;
import ch.nolix.common.node.Node;
import ch.nolix.common.validator.Validator;
import ch.nolix.element.elementAPI.IElement;
import ch.nolix.element.elementEnums.UniDirection;

//class
/**
 * A color gradient specifies two colors and an uni direction.
 * A color gradient is not mutable.
 * 
 * @author Silvan Wyss
 * @month 2016-07
 * @lines 240
 */
public class ColorGradient implements IElement {
	
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
	public static ColorGradient fromSpecification(
		final BaseNode specification
	) {
		
		final var attributes = specification.getRefAttributes();
		
		final var directionSpecification = new Node();
		directionSpecification.addAttribute(attributes.getRefAt(1));
		
		final var color1Specification = new Node();
		color1Specification.addAttribute(attributes.getRefAt(2));
		
		final var color2Specification = new Node();
		color2Specification.addAttribute(attributes.getRefAt(3));
		
		return new ColorGradient(
			UniDirection.fromSpecification(directionSpecification),
			Color.fromSpecification(color1Specification),
			Color.fromSpecification(color2Specification)
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
	 * @throws ArgumentIsNullException if the given direction is null.
	 * @throws ArgumentIsNullException if the given color 1 is null.
	 * @throws ArgumentIsNullException if the given color 2 is null.
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
	 * @throws ArgumentIsNullException if the given direction is null.
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
	 * @throws ArgumentIsNullException if the given direction is null.
	 * @throws ArgumentIsNullException if the given color 1 is null.
	 * @throws ArgumentIsNullException if the given color 2 is null.
	 */
	public ColorGradient(
		final UniDirection direction,
		final Color color1,
		final Color color2
	) {
		//Asserts that the given direction is not null.
		Validator.assertThat(direction).thatIsNamed("direction").isNotNull();
		
		//Asserts that the given color 1 is not null.
		Validator.assertThat(color1).thatIsNamed("color 1").isNotNull();
		
		//Asserts that the given color 2 is not null.
		Validator.assertThat(color2).thatIsNamed("color 2").isNotNull();
		
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
					(float)xPosition + width,
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
					(float)yPosition + height,
					getColor2().createSwingColor()
				);
			case DiagonalUp:
				return
				new GradientPaint(
					xPosition,
					yPosition,
					getColor1().createSwingColor(),
					(float)xPosition + width,
					(float)yPosition + height,
					getColor2().createSwingColor()
				);
			case DiagonalDown:
				return
				new GradientPaint(
					xPosition,
					yPosition,
					getColor2().createSwingColor(),
					(float)xPosition + width,
					(float)yPosition + height,
					getColor1().createSwingColor()
				);
			default:
				
				throw new InvalidArgumentException(this);
		}
	}
			
	//method
	/**
	 * @return the attributes of this color gradient.
	 */
	@Override
	public LinkedList<Node> getAttributes() {
		return 
		new LinkedList<>(
			new Node(getDirection().toString()),
			new Node(getColor1().getHexadecimalValue()),
			new Node(getColor2().getHexadecimalValue())
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
