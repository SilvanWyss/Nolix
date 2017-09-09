//package declaration
package ch.nolix.element.basic;

//own imports
import ch.nolix.core.constants.StringManager;
import ch.nolix.core.container.List;
import ch.nolix.core.invalidArgumentException.Argument;
import ch.nolix.core.invalidArgumentException.ErrorPredicate;
import ch.nolix.core.invalidArgumentException.InvalidArgumentException;
import ch.nolix.core.specification.StandardSpecification;
import ch.nolix.core.validator2.Validator;

//class
/**
 * This class represents a true color.
 * A true color is a color that consists of a blue, green and red value that all are integers in [0, 255].
 * A color is not mutable.
 * 
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 680
 */
public class Color extends Element {
	
	//constant
	public static final String TYPE_NAME = "Color";

	//very light colors
	public static final String VERY_LIGHT_BLUE_STRING = "VeryLightBlue";
	public static final int VERY_LIGHT_BLUE = 0xBFBFFF;
	public static final String VERY_LIGHT_CYAN_STRING = "VeryLightCyan";
	public static final int VERY_LIGHT_CYAN = 0xBFFFFF;
	public static final String VERY_LIGHT_GREEN_STRING = "VeryLightGreen";
	public static final int VERY_LIGHT_GREEN = 0x3FFF3F;
	public static final String VERY_LIGHT_GREY_STRING = "VeryLightGrey";
	public static final int VERY_LIGHT_GREY = 0xEFEFEF;
	public static final String VERY_LIGHT_ORANGE_STRING = "VeryLightOrange";
	public static final int VERY_LIGHT_ORANGE = 0x000003;
	public static final String VERY_LIGHT_PURPLE_STRING = "VeryLightPurple";
	public static final int VERY_LIGHT_PURPLE = 0x000002;	
	public static final String VERY_LIGHT_RED_STRING = "VeryLightRed";
	public static final int VERY_LIGHT_RED = 0xF8E0E0;
	
	//light colors
	public static final String LIGHT_BLUE_STRING = "LightBlue";
	public static final int LIGHT_BLUE = 0x7F7FFF;
	public static final String LIGHT_CYAN_STRING = "LightCyan";
	public static final int LIGHT_CYAN = 0x7FFFFF;
	public static final String LIGHT_GREEN_STRING = "LightGreen";
	public static final int LIGHT_GREEN = 0x7FFF7F;
	public static final String LIGHT_GREY_STRING = "LightGrey";
	public static final int LIGHT_GREY = 0xDFDFDF;
	public static final String LIGHT_ORANGE_STRING = "LightOrange";
	public static final int LIGHT_ORANGE = 0xFFBF3F;
	public static final String LIGHT_PURPLE_STRING = "LightPurple";
	public static final int LIGHT_PURPLE = 0xE2A9F3;
	public static final String LIGHT_RED_STRING = "LightRed";
	public static final int LIGHT_RED = 0xFF7F7F;
	
	//normal colors
	public static final String ANTHRAZIT_STRING  = "Anthrazit";
	public static final int ANTHRAZIT = 0x101010;
	public static final String BLACK_STRING = "Black";
	public static final int BLACK = 0x000000;
	public static final String BLUE_STRING = "Blue";
	public static final int BLUE = 0x0000FF;
	public static final String BROWN_STRING = "Brown";
	public static final int BROWN = 0x7F3F00;
	public static final String CYAN_STRING = "Cyan";
	public static final int CYAN = 0x00FFFF;
	public static final String GREEN_STRING = "Green";
	public static final int GREEN = 0x00FF00;
	public static final String GREY_STRING = "Grey";
	public static final int GREY = 0x7F7F7F;
	public static final String ORANGE_STRING = "Orange";
	public static final int ORANGE = 0xFF7F00;
	public static final String PURPLE_STRING = "Purple";
	public static final int PURPLE = 0xBF00FF;
	public static final String RED_STRING = "Red";
	public static final int RED = 0xFF0000;
	public static final String WHITE_STRING = "White";
	public static final int WHITE = 0xFFFFFF;
	public static final String YELLOW_STRING = "Yellow";
	public static final int YELLOW = 0xFFFF00;
	
	//dark colors
	public static final String DARK_BLUE_STRING = "DarkBlue";
	public static final int DARK_BLUE = 0x00007F;
	public static final String DARK_CYAN_STRING = "DarkCyan";
	public static final int DARK_CYAN = 0x00BFBF;
	public static final String DARK_GREEN_STRING = "DarkGreen";
	public static final int DARK_GREEN = 0x007F00;
	public static final String DARK_GREY_STRING = "DarkGrey";
	public static final int DARK_GREY = 0x3F3F3F;
	public static final String DARK_ORANGE_STRING = "DarkOrange";
	public static final int DARK_ORANGE = 0xFF7F3F;
	public static final String DARK_PURPLE_STRING = "DarkPurple";
	public static final int DARK_PURPLE = 0x7F007F;
	public static final String DARK_RED_STRING = "DarkRed";
	public static final int DARK_RED = 0x7F0000;
	
	//very dark colors
	public static final String VERY_DARK_BLUE_STRING = "VeryDarkBlue";
	public static final int VERY_DARK_BLUE = 0x00003F;
	public static final String VERY_DARK_CYAN_STRING = "VeryDarkCyan";
	public static final int VERY_DARK_CYAN = 0x7F7F;
	public static final String VERY_DARK_GREEN_STRING = "VeryDarkGreen";
	public static final int VERY_DARK_GREEN = 0x003F00;
	public static final String VERY_DARK_GREY_STRING = "VeryDarkGrey";
	public static final int VERY_DARK_GREY = 0x1F1F1F;
	public static final String VERY_DARK_ORANGE_STRING = "VeryDarkOrange";
	public static final int VERY_DARK_ORANGE = 0x000001;
	public static final String VERY_DARK_PURPLE_STRING = "VeryDarkPurple";
	public static final int VERY_DARK_PURPLE = 0x3F003F;
	public static final String VERY_DARK_RED_STRING = "VeryDarkRed";
	public static final int VERY_DARK_RED = 0x3F0000;
	
	//true colors
	private static final int MIN_TRUE_COLOR = 0;
	private static final int MAX_TRUE_COLOR = 16777215;
	
	//true color components
	private static final int MIN_TRUE_COLOR_COMPONENT = 0;
	private static final int MAX_TRUE_COLOR_COMPONENT = 255;
	
	//attribute
	private int value = RED;
	
	//constructor
	/**
	 * Creates new color with default values.
	 */
	public Color() {}
	
	//constructor
	/**
	 * Creates new color with the given value.
	 * 
	 * @param value
	 * @throws OutOfRangeArgumentException if the given value is no true color value (in [0, 16'777'215]).
	 */
	public Color(final int value) {
		setValue(value);
	}
	
	//constructor
	/**
	 * Creates new color with the given red value, green value and blue value.
	 * 
	 * @param redValue
	 * @param greenValue
	 * @param blueValue
	 */
	public Color(final int redValue, final int greenValue, final int blueValue) {
		setRedValue(redValue);
		setGreenValue(greenValue);
		setBlueValue(blueValue);
	}
	
	//constructor
	/**
	 * Creates new color with the given value.
	 * 
	 * @param value
	 * @throws InvalidArgumentException if the given value is no color name or no true color value
	 */
	public Color(final String value) {
		setValue(value);
	}
	
	//method
	/**
	 * @return a new color that is the inverted color of this color.
	 */
	public final Color createInvertedColor() {
		return new Color(
			MAX_TRUE_COLOR_COMPONENT - getRedValue(),
			MAX_TRUE_COLOR_COMPONENT - getGreenValue(),
			MAX_TRUE_COLOR_COMPONENT - getBlueValue()
		);
	}
	
	//method
	/**
	 * @return the attributes of this color.
	 */
	public final List<StandardSpecification> getAttributes() {		
		return new List<StandardSpecification>().addAtEnd(new StandardSpecification(getStringValue()));
	}
	
	//method
	/**
	 * @return the blue value of this color.
	 */
	public final int getBlueValue() {
		return (value % 256);
	}
	
	//method
	/**
	 * @return a copy of this color.
	 */
	public Color getCopy() {
		return new Color(getValue());
	}
	
	//method
	/**
	 * @return the green value of this color.
	 */
	public final int getGreenValue() {
		return ((value / 256) % 256);
	}
	
	//method
	/**
	 * @return the java color of this color.
	 */
	public final java.awt.Color getJavaColor() {
		return new java.awt.Color(getValue());
	}
	
	//method
	/**
	 * @return the red value of this color.
	 */
	public final int getRedValue() {
		return (value / 65536);
	}
	
	//method
	/**
	 * @return the string value of this color.
	 */
	public final String getStringValue() {
		
		//Enumerates the value of this color.
		switch (getValue()) {			
			
			//Handles very light colors.
			case VERY_LIGHT_BLUE:
				return VERY_LIGHT_BLUE_STRING;
			case VERY_LIGHT_CYAN:
				return VERY_LIGHT_CYAN_STRING;
			case VERY_LIGHT_GREEN:
				return VERY_LIGHT_GREEN_STRING;
			case VERY_LIGHT_GREY:
				return VERY_LIGHT_GREY_STRING;
			case VERY_LIGHT_ORANGE:
				return VERY_LIGHT_ORANGE_STRING;
			case VERY_LIGHT_PURPLE:
				return VERY_LIGHT_PURPLE_STRING;
			case VERY_LIGHT_RED:
				return VERY_LIGHT_RED_STRING;
			
			//Handles light colors.
			case LIGHT_BLUE:
				return LIGHT_BLUE_STRING;
			case LIGHT_CYAN:
				return LIGHT_CYAN_STRING;
			case LIGHT_GREEN:
				return LIGHT_GREEN_STRING;
			case LIGHT_GREY:
				return LIGHT_GREY_STRING;
			case LIGHT_ORANGE:
				return LIGHT_ORANGE_STRING;
			case LIGHT_PURPLE:
				return LIGHT_PURPLE_STRING;
			case LIGHT_RED:
				return LIGHT_RED_STRING;
			
			//Handles normal colors.
			case ANTHRAZIT:
				return ANTHRAZIT_STRING;
			case BLACK:
				return BLACK_STRING;
			case BLUE:
				return BLUE_STRING;
			case BROWN:
				return BROWN_STRING;
			case CYAN:
				return CYAN_STRING;
			case GREEN:
				return GREEN_STRING;
			case GREY:
				return GREY_STRING;
			case ORANGE:
				return ORANGE_STRING;
			case PURPLE:
				return PURPLE_STRING;
			case RED:
				return RED_STRING;
			case WHITE:
				return WHITE_STRING;
			case YELLOW:
				return YELLOW_STRING;
				
			//Handles dark colors.
			case DARK_BLUE:
				return DARK_BLUE_STRING;
			case DARK_CYAN:
				return DARK_CYAN_STRING;
			case DARK_GREEN:
				return DARK_GREEN_STRING;
			case DARK_GREY:
				return DARK_GREY_STRING;
			case DARK_ORANGE:
				return DARK_ORANGE_STRING;
			case DARK_PURPLE:
				return DARK_PURPLE_STRING;
			case DARK_RED:
				return DARK_RED_STRING;
				
			//Handles very dark colors.
			case VERY_DARK_BLUE:
				return VERY_DARK_BLUE_STRING;
			case VERY_DARK_CYAN:
				return VERY_DARK_CYAN_STRING;
			case VERY_DARK_GREEN:
				return VERY_DARK_GREEN_STRING;
			case VERY_DARK_GREY:
				return VERY_DARK_GREY_STRING;
			case VERY_DARK_ORANGE:
				return VERY_DARK_ORANGE_STRING;
			case VERY_DARK_PURPLE:
				return VERY_DARK_PURPLE_STRING;
			case VERY_DARK_RED:
				return VERY_DARK_RED_STRING;
			
			//Handles other colors.
			default:
				String value = StringManager.HEXADECIMAL_PREFIX;
				int base = 16777216;
				for (int i = 1; i <= 6; i++) {
					switch ((getValue() / base) % 16) {
						case 0:
							value += '0';
							break;
						case 1:
							value += '1';
							break;
						case 2:
							value += '2';
							break;
						case 3:
							value += '3';
							break;
						case 4:
							value += '4';
							break;
						case 5:
							value += '5';
							break;
						case 6:
							value += '6';
							break;
						case 7:
							value += '7';
							break;
						case 8:
							value += '8';
							break;
						case 9:
							value += '9';
							break;
						case 10:
							value += 'A';
							break;
						case 11:
							value += 'B';
							break;
						case 12:
							value += 'C';
							break;
						case 13:
							value += 'D';
							break;
						case 14:
							value += 'E';
							break;
						case 15:
							value += 'F';
							break;
						}
					base /= 16;
				}
				return value;	
		}
	}
	
	//method
	/**
	 * @return the integer value of this color.
	 */
	public final int getValue() {
		return value;
	}
	
	//method
	/**
	 * Sets the blue value of this color.
	 * 
	 * @param blueValue
	 * @throws OutOfRangeException if the given blue value is no true color component (in [0, 255]).
	 */
	private void setBlueValue(final int blueValue) {
		
		//Checks if the given blue value is between 0 and 255.
		Validator
		.supposeThat(blueValue)
		.thatIsNamed("blue value")
		.isBetween(MIN_TRUE_COLOR_COMPONENT, MAX_TRUE_COLOR_COMPONENT);
		
		value = getValue() - getBlueValue() + blueValue;
	}
	
	//method
	/**
	 * Sets the green value of this color.
	 * 
	 * @param greenValue
	 * @throws OutOfRangeException if the given green value is no true color component (in [0, 255]5).
	 */
	private void setGreenValue(final int greenValue) {
		
		//Checks if the given blue value is between 0 and 255.
		Validator
		.supposeThat(greenValue)
		.thatIsNamed("green value")
		.isBetween(MIN_TRUE_COLOR_COMPONENT, MAX_TRUE_COLOR_COMPONENT);
		
        value = getValue() - (getGreenValue() * 256) + (greenValue * 256);
	}
	
	//method
	/**
	 * Sets the red value of this color.
	 * 
	 * @param redValue
	 * @throws OutOfRangeException if the given red value is no true color component (in [0, 255]).
	 */
	private void setRedValue(final int redValue) {
		
		//Checks if the given blue value is between 0 and 255.
		Validator
		.supposeThat(redValue)
		.thatIsNamed("red value")
		.isBetween(MIN_TRUE_COLOR_COMPONENT, MAX_TRUE_COLOR_COMPONENT);
		
        value = getValue() - (getRedValue() * 65536) + (redValue * 65536);
	}
	
	//method
	/**
	 * Sets the value of this color.
	 * 
	 * @param value
	 * @throws OutOfRangeException if the given value is no true color (in [0, 16'777'215]).
	 */
	private void setValue(final int value) {
		
		//Checks if the given value is between 0 and 16777215.
		Validator.supposeThat(value).isBetween(MIN_TRUE_COLOR, MAX_TRUE_COLOR);
		
		this.value = value;
	}
	
	//method
	/**
	 * Sets the value of this color.
	 * 
	 * @param value
	 * @throws InvalidArgumentException if the given value is no color name or no true color value.
	 */
	private void setValue(final String value) {
		
		//Enumerates the given value.
		switch (value) {
			
			//Handles very light colors.
			case VERY_LIGHT_BLUE_STRING:
				this.value = VERY_LIGHT_BLUE;
				break;
			case VERY_LIGHT_CYAN_STRING:
				this.value = VERY_LIGHT_CYAN;
				break;
			case VERY_LIGHT_GREEN_STRING:
				this.value = VERY_LIGHT_GREEN;
				break;
			case VERY_LIGHT_GREY_STRING:
				this.value = VERY_LIGHT_GREY;
				break;
			case VERY_LIGHT_ORANGE_STRING:
				this.value = VERY_LIGHT_ORANGE;
				break;
			case VERY_LIGHT_PURPLE_STRING:
				this.value = VERY_LIGHT_PURPLE;
				break;
			case VERY_LIGHT_RED_STRING:
				this.value = VERY_LIGHT_RED;
				break;
				
			//Handles light colors.
			case LIGHT_BLUE_STRING:
				this.value = LIGHT_BLUE;
				break;
			case LIGHT_CYAN_STRING:
				this.value = LIGHT_CYAN;
				break;
			case LIGHT_GREEN_STRING:
				this.value = LIGHT_GREEN;
				break;
			case LIGHT_GREY_STRING:
				this.value = LIGHT_GREY;
				break;
			case LIGHT_ORANGE_STRING:
				this.value = LIGHT_ORANGE;
				break;
			case LIGHT_PURPLE_STRING:
				this.value = LIGHT_PURPLE;
				break;
			case LIGHT_RED_STRING:
				this.value = LIGHT_RED;
				break;
				
			//Handles normal colors.
			case ANTHRAZIT_STRING:
				this.value = ANTHRAZIT;
				break;
			case BLACK_STRING:
				this.value = BLACK;
				break;
			case BLUE_STRING:
				this.value = BLUE;
				break;
			case BROWN_STRING:
				this.value = BROWN;
				break;
			case CYAN_STRING:
				this.value = CYAN;
				break;
			case GREEN_STRING:
				this.value = GREEN;
				break;
			case GREY_STRING:
				this.value = GREY;
				break;
			case ORANGE_STRING:
				this.value = ORANGE;
				break;
			case PURPLE_STRING:
				this.value = PURPLE;
				break;
			case RED_STRING:
				this.value = RED;
				break;
			case WHITE_STRING:
				this.value = WHITE;
				break;
			case YELLOW_STRING:
				this.value = YELLOW;
				break;
			
			//Handles dark colors.
			case DARK_BLUE_STRING:
				this.value = DARK_BLUE;
				break;
			case DARK_CYAN_STRING:
				this.value = DARK_CYAN;
				break;
			case DARK_GREEN_STRING:
				this.value = DARK_GREEN;
				break;
			case DARK_GREY_STRING:
				this.value = DARK_GREY;
				break;
			case DARK_ORANGE_STRING:
				this.value = DARK_ORANGE;
				break;
			case DARK_PURPLE_STRING:
				this.value = DARK_PURPLE;
				break;
			case DARK_RED_STRING:
				this.value = DARK_RED;
				break;
			
			//Handles very dark colors.
			case VERY_DARK_BLUE_STRING:
				this.value = VERY_DARK_BLUE;
				break;
			case VERY_DARK_CYAN_STRING:
				this.value = VERY_DARK_CYAN;
				break;
			case VERY_DARK_GREEN_STRING:
				this.value = VERY_DARK_GREEN;
				break;
			case VERY_DARK_GREY_STRING:
				this.value = VERY_DARK_GREY;
				break;
			case VERY_DARK_ORANGE_STRING:
				this.value = VERY_DARK_ORANGE;
				break;
			case VERY_DARK_PURPLE_STRING:
				this.value = VERY_DARK_PURPLE;
				break;
			case VERY_DARK_RED_STRING:
				this.value = VERY_DARK_RED;
				break;
				
			//Handles other colors.	
			default:
				if (value.length() != 8 && !value.substring(2).equals(StringManager.HEXADECIMAL_PREFIX)) {
					throw new InvalidArgumentException(
						new Argument(value),
						new ErrorPredicate("is no color name or true color value")
					);	
				}
				this.value = 0;
				int base = 1;
				for (int i = 7; i >= 2; i--) {
					int tempValue;
					switch (value.charAt(i)) {
					case '0':
						tempValue = 0;
						break;
					case '1':
						tempValue = 1;
						break;
					case '2':
						tempValue = 2;
						break;
					case '3':
						tempValue = 3;
						break;
					case '4':
						tempValue = 4;
						break;
					case '5':
						tempValue = 5;
						break;
					case '6':
						tempValue = 6;
						break;
					case '7':
						tempValue = 7;
						break;
					case '8':
						tempValue = 8;
						break;
					case '9':
						tempValue = 9;
						break;
					case 'A':
						tempValue = 10;
						break;
					case 'B':
						tempValue = 11;
						break;
					case 'C':
						tempValue = 12;
						break;
					case 'D':
						tempValue = 13;
						break;
					case 'E':
						tempValue = 14;
						break;
					case 'F':
						tempValue = 15;
						break;
					default:
						throw new InvalidArgumentException(
							new Argument(value),
							new ErrorPredicate("is no color name or true color value")
						);	
				}
				this.value += tempValue * base;
				base *= 16;
			}
		}
	}
}
