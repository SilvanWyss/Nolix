/*
 * file:	ColorGradient.java
 * author:	Silvan Wyss
 * month:	2016-07
 * lines:	110
 */

//package declaration
package ch.nolix.element.basic;

//own imports
import ch.nolix.core.container.List;
import ch.nolix.core.invalidArgumentException.Argument;
import ch.nolix.core.invalidArgumentException.ArgumentName;
import ch.nolix.core.invalidArgumentException.InvalidArgumentException;
import ch.nolix.core.specification.StandardSpecification;

//class
/**
 * A color gradient specifies two colors and a pattern how the colors are visualized.
 */
public final class ColorGradient extends Element {
	
	//default values
	public static final ColorGradientPattern DEFAULT_PATTERN = ColorGradientPattern.TopToBottom;
	public static final Color DEFAULT_COLOR_1 = Color.BLACK;
	public static final Color DEFAULT_COLOR_2 = new Color(Color.WHITE);
	
	//attribute headers
	private static final String PATTERN = "Pattern";
	private static final String COLOR_1 = "Color1";
	private static final String COLOR_2 = "Color2";

	//attributes
	private ColorGradientPattern pattern = DEFAULT_PATTERN;
	private Color color1 = new Color();
	private Color color2 = new Color();
	
	//method
	/**
	 * @return the attributes of this color gradient
	 */
	public final List<StandardSpecification> getAttributes() {
		return new List<StandardSpecification>()
			.addAtEnd(pattern.getSpecification())
			.addAtEnd(color1.getSpecificationAs(COLOR_1))
			.addAtEnd(color2.getSpecificationAs(COLOR_2));
	}
	
	//method
	/**
	 * @return the pattern of this color gradient
	 */
	public final ColorGradientPattern getPattern() {
		return pattern;
	}
	
	//method
	/**
	 * Resets this color gradient to black and white color.
	 */
	public final void reset() {
		setColor1(DEFAULT_COLOR_1);
		setColor2(DEFAULT_COLOR_2);
	}
	
	//method
	/**
	 * Sets the given attribute to this color gradient.
	 * 
	 * @throws Exception if the given attribute is not valid
	 */
	public final void addOrChangeAttribute(StandardSpecification attribute) {
		switch (attribute.getHeader()) {
			case PATTERN:
				setPattern(ColorGradientPattern.valueOf(attribute.getOneAttributeToString()));
				break;
			case COLOR_1:
				setColor1(new Color(attribute.getOneAttributeToString()));
				break;
			case COLOR_2:
				setColor2(new Color(attribute.getOneAttributeToString()));
				break;
			default:
				throw new InvalidArgumentException(
					new ArgumentName("attribute"),
					new Argument(attribute)
				);
		}
	}
	
	//method
	/**
	 * Sets the color 1 of this color gradient.
	 * 
	 * @param color
	 * @throws Exception if the given color is no true color value (negative or bigger than 16777215)
	 */
	public final void setColor1(Color color) {
		this.color1 = color;
	}
	
	//method
	/**
	 * Sets the color 2 of this color gradient.
	 * 
	 * @param color
	 * @throws Exception if the given color is no true color value (negative or bigger than 16777215)
	 */
	public final void setColor2(Color color) {
		this.color2 = color;
	}
	
	//method
	/**
	 * Sets the pattern of this color gradient.
	 * 
	 * @param pattern
	 */
	public final void setPattern(ColorGradientPattern pattern) {
		this.pattern = pattern;
	}
}
