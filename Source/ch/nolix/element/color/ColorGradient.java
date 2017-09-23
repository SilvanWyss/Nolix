//package declaration
package ch.nolix.element.color;

//Java imports
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;

//own imports
import ch.nolix.core.container.List;
import ch.nolix.core.enums.UniDirection;
import ch.nolix.core.invalidStateException.InvalidStateException;
import ch.nolix.core.specification.StandardSpecification;
import ch.nolix.core.validator2.Validator;
import ch.nolix.element.core.Element;

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
	
	//default values
	public static final UniDirection DEFAULT_DIRECTION = UniDirection.Vertical;
	public static final Color DEFAULT_COLOR1 = Color.BLACK;
	public static final Color DEFAULT_COLOR2 = Color.WHITE;
	
	//attribute headers
	private static final String DIRECTION_HEADER = "Direction";
	private static final String COLOR_1_HEADER = "Color1";
	private static final String COLOR_2_HEADER = "Color2";

	//attributes
	private final UniDirection direction;
	private final Color color1;
	private final Color color2;
	
	//constructor
	/**
	 * Creates new color gradient.
	 */
	public ColorGradient() {
			
		//Calls other constructor.
		this(DEFAULT_DIRECTION, DEFAULT_COLOR1, DEFAULT_COLOR2);
	}
	
	//constructor
	/**
	 * Creates new color gradient with the given color 1 and color 2.
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
	 * Creates new color gradient with the given direction.
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
	 * Creates new color gradient with the given direction, color 1 and color 2.
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
		Validator.supposeThat(direction).thatIsNamed("direction").isNotNull();
		
		//Checks if the given color 1 is not null.
		Validator.supposeThat(color1).thatIsNamed("color 1").isNotNull();
		
		//Checks if the given color 2 is not null.
		Validator.supposeThat(color2).thatIsNamed("color 2").isNotNull();
		
		this.direction = direction;
		this.color1 = color1;
		this.color2 = color2;
	}
	
	//method
	/**
	 * @return the attributes of this color gradient.
	 */
	public List<StandardSpecification> getAttributes() {
		return 
		new List<StandardSpecification>()
		.addAtEnd(
			new StandardSpecification(
				DIRECTION_HEADER,
				getDirection().toString()
			)
		)
		.addAtEnd(getColor1().getSpecificationAs(COLOR_1_HEADER))
		.addAtEnd(getColor2().getSpecificationAs(COLOR_2_HEADER));
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
	 * Lets this color gradient paint a rectangle with the given width and height
	 * at the given position using the given graphics.
	 * 
	 * @param xPosition
	 * @param yPosition
	 * @param width
	 * @param height
	 * @param graphics
	 */
	public void paintRectangle(
		final int xPosition,
		final int yPosition,
		final int width,
		final int height,
		final Graphics graphics
	) {
		((Graphics2D)graphics).setPaint(
			getJavaGradientPaint(xPosition, yPosition, width, height)
		);
		
		graphics.fillRect(xPosition, yPosition, width, height);
	}
	
	//method
	/**
	 * @param xPosition
	 * @param yPosition
	 * @param width
	 * @param height
	 * @return a new Java gradient paint of this color gradient
	 * for the given position, width and height.
	 */
	private GradientPaint getJavaGradientPaint(
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
					getColor1().getJavaColor(),
					xPosition + width,
					yPosition,
					getColor2().getJavaColor()
				);
			case Vertical:
				return
				new GradientPaint(
					xPosition,
					yPosition,
					getColor2().getJavaColor(),
					xPosition + width,
					yPosition,
					getColor1().getJavaColor()
				);
			case DiagonalUp:
				return
				new GradientPaint(
					xPosition,
					yPosition,
					getColor1().getJavaColor(),
					xPosition,
					yPosition + height,
					getColor2().getJavaColor()
				);
			case DiagonalDown:
				return
				new GradientPaint(
					xPosition,
					yPosition,
					getColor2().getJavaColor(),
					xPosition,
					yPosition + height,
					getColor1().getJavaColor()
				);
			default:
				
				throw new InvalidStateException(this);
		}
	}
}
