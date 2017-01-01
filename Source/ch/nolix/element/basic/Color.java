/*
 * file:	Color.java
 * author:	Silvan Wyss
 * month:	2015
 * lines:	670
 */

//package declaration
package ch.nolix.element.basic;

//own imports
import java.awt.Graphics;

import ch.nolix.common.constants.StringManager;
import ch.nolix.common.container.List;
import ch.nolix.common.exception.UnsupportedMethodException;
import ch.nolix.common.specification.Specification;
import ch.nolix.common.specification.Statement;
import ch.nolix.common.util.Validator;

//class
/**
 * This class represents a true color.
 * A true color is a color that consists of a blue, green and red value that have all the size of 1 byte.
 */
public class Color extends Element {
	
	//constant
	public final static String SIMPLE_CLASS_NAME = "Color";

	//very light colors
	public final static String VERY_LIGHT_BLUE_STRING = "VeryLightBlue";
	public final static int VERY_LIGHT_BLUE = 0xBFBFFF;
	public final static String VERY_LIGHT_CYAN_STRING = "VeryLightCyan";
	public final static int VERY_LIGHT_CYAN = 0xBFFFFF;
	public final static String VERY_LIGHT_GREEN_STRING = "VeryLightGreen";
	public final static int VERY_LIGHT_GREEN = 0x3FFF3F;
	public final static String VERY_LIGHT_GREY_STRING = "VeryLightGrey";
	public final static int VERY_LIGHT_GREY = 0xEFEFEF;
	public final static String VERY_LIGHT_ORANGE_STRING = "VeryLightOrange";
	public final static int VERY_LIGHT_ORANGE = 0x000003;
	public final static String VERY_LIGHT_PURPLE_STRING = "VeryLightPurple";
	public final static int VERY_LIGHT_PURPLE = 0x000002;	
	public final static String VERY_LIGHT_RED_STRING = "VeryLightRed";
	public final static int VERY_LIGHT_RED = 0xF8E0E0;
	
	//light colors
	public final static String LIGHT_BLUE_STRING = "LightBlue";
	public final static int LIGHT_BLUE = 0x7F7FFF;
	public final static String LIGHT_CYAN_STRING = "LightCyan";
	public final static int LIGHT_CYAN = 0x7FFFFF;
	public final static String LIGHT_GREEN_STRING = "LightGreen";
	public final static int LIGHT_GREEN = 0x7FFF7F;
	public final static String LIGHT_GREY_STRING = "LightGrey";
	public final static int LIGHT_GREY = 0xDFDFDF;
	public final static String LIGHT_ORANGE_STRING = "LightOrange";
	public final static int LIGHT_ORANGE = 0xFFBF3F;
	public final static String LIGHT_PURPLE_STRING = "LightPurple";
	public final static int LIGHT_PURPLE = 0xE2A9F3;
	public final static String LIGHT_RED_STRING = "LightRed";
	public final static int LIGHT_RED = 0xFF7F7F;
	
	//normal colors
	public final static String ANTHRAZIT_STRING  = "Anthrazit";
	public final static int ANTHRAZIT = 0x101010;
	public final static String BLACK_STRING = "Black";
	public final static int BLACK = 0x000000;
	public final static String BLUE_STRING = "Blue";
	public final static int BLUE = 0x0000FF;
	public final static String BROWN_STRING = "Brown";
	public final static int BROWN = 0x7F3F00;
	public final static String CYAN_STRING = "Cyan";
	public final static int CYAN = 0x00FFFF;
	public final static String GREEN_STRING = "Green";
	public final static int GREEN = 0x00FF00;
	public final static String GREY_STRING = "Grey";
	public final static int GREY = 0x7F7F7F;
	public final static String ORANGE_STRING = "Orange";
	public final static int ORANGE = 0xFF7F00;
	public final static String PURPLE_STRING = "Purple";
	public final static int PURPLE = 0xBF00FF;
	public final static String RED_STRING = "Red";
	public final static int RED = 0xFF0000;
	public final static String WHITE_STRING = "White";
	public final static int WHITE = 0xFFFFFF;
	public final static String YELLOW_STRING = "Yellow";
	public final static int YELLOW = 0xFFFF00;
	
	//dark colors
	public final static String DARK_BLUE_STRING = "DarkBlue";
	public final static int DARK_BLUE = 0x00007F;
	public final static String DARK_CYAN_STRING = "DarkCyan";
	public final static int DARK_CYAN = 0x00BFBF;
	public final static String DARK_GREEN_STRING = "DarkGreen";
	public final static int DARK_GREEN = 0x007F00;
	public final static String DARK_GREY_STRING = "DarkGrey";
	public final static int DARK_GREY = 0x3F3F3F;
	public final static String DARK_ORANGE_STRING = "DarkOrange";
	public final static int DARK_ORANGE = 0xFF7F3F;
	public final static String DARK_PURPLE_STRING = "DarkPurple";
	public final static int DARK_PURPLE = 0x7F007F;
	public final static String DARK_RED_STRING = "DarkRed";
	public final static int DARK_RED = 0x7F0000;
	
	//very dark colors
	public final static String VERY_DARK_BLUE_STRING = "VeryDarkBlue";
	public final static int VERY_DARK_BLUE = 0x00003F;
	public final static String VERY_DARK_CYAN_STRING = "VeryDarkCyan";
	public final static int VERY_DARK_CYAN = 0x7F7F;
	public final static String VERY_DARK_GREEN_STRING = "VeryDarkGreen";
	public final static int VERY_DARK_GREEN = 0x003F00;
	public final static String VERY_DARK_GREY_STRING = "VeryDarkGrey";
	public final static int VERY_DARK_GREY = 0x1F1F1F;
	public final static String VERY_DARK_ORANGE_STRING = "VeryDarkOrange";
	public final static int VERY_DARK_ORANGE = 0x000001;
	public final static String VERY_DARK_PURPLE_STRING = "VeryDarkPurple";
	public final static int VERY_DARK_PURPLE = 0x3F003F;
	public final static String VERY_DARK_RED_STRING = "VeryDarkRed";
	public final static int VERY_DARK_RED = 0x3F0000;
	
	//attribute
	private int value = RED;
	
	//constructor
	/**
	 * Creates new color with default attributes.
	 */
	public Color() {}
	
	//constructor
	/**
	 * Creates new color with the given value.
	 * 
	 * @param value
	 * @throws Exception if the given value is no true color value (negative or bigger than 16'777'215).
	 */
	public Color(int value) {
		setValue(value);
	}
	
	//constructor
	/**
	 * Creates new color with the given value.
	 * 
	 * @param value
	 * @throws Exception if the given value is no color name or no true color value
	 */
	public Color(String value) {
		setValue(value);
	}
	
	//method
	/**
	 * @return the attributes of this color
	 */
	public final List<Specification> getAttributes() {		
		return new List<Specification>().addAtEnd(new Specification(getStringValue()));
	}
	
	//method
	/**
	 * @return the blue value of this color
	 */
	public final int getBlueValue() {
		return (value % 256);
	}
	
	//method
	/**
	 * @return a clone of this color
	 */
	public Color getCopy() {
		Color color = new Color();
		color.value = value;
		return color;
	}
	
	//method
	/**
	 * @return the green value of this color
	 */
	public final int getGreenValue() {
		return ((value / 256) % 256);
	}
	
	//method
	/**
	 * @return the integer value of this color
	 */
	public final int getValue() {
		return value;
	}
	
	//method
	/**
	 * @return the java color of this color
	 */
	public final java.awt.Color getJavaColor() {
		return new java.awt.Color(getValue());
	}
	
	//method
	/**
	 * @throws UnsupportedMethodException
	 */
	public Object getRawReference(Statement request) {
		throw new UnsupportedMethodException(this, "get raw reference");
	}
	
	//method
	/**
	 * @return the red value of this color
	 */
	public final int getRedValue() {
		return (value / 65536);
	}
	
	//method
	/**
	 * @return the value of this color
	 */
	public final String getStringValue() {
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
	 * Inverts this color.
	 */
	public final void invert() {
		try {
			setRedValue(255 - getRedValue());
			setGreenValue(255 - getGreenValue());
			setBlueValue(255 - getBlueValue());
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//method
	/**
	 * Paints this background color using the given graphics.
	 * @param graphics
	 * @param distanceFromLeftPanelBorder
	 * @param distanceFromTopPanelBorder
	 * @param width
	 * @param height
	 */
	public final void paintRectangle(Graphics graphics, int distanceFromLeftPanelBorder, int distanceFromTopPanelBorder, int width, int height) {
		graphics.setColor(getJavaColor());
		graphics.fillRect(distanceFromLeftPanelBorder, distanceFromTopPanelBorder, width, height);
	}
	
	//method
	/**
	 * @throws UnsupportedMethodException
	 */
	public void removeAttribute(String attribute) {
		throw new UnsupportedMethodException(this, "remove attribute");
	}
	
	//method
	/**
	 * Resets this color.
	 */
	public void reset() {
		setValue(RED);
	}
	
	//method
	/**
	 * @throws UnsupportedMethodException
	 */
	public void addOrChangeAttribute(Specification attribute) {
		throw new UnsupportedMethodException(this, "set attribute");	
	}
	
	//method
	/**
	 * Sets the blue value of this color.
	 * 
	 * @param blueValue
	 * @throws Exception if the given blue value is negative or bigger than 255
	 */
	public final void setBlueValue(int blueValue) {
		
		Validator.throwExceptionIfValueIsNotInRange("blue value", 0, 255, blueValue);
		
		value = getValue() - getBlueValue() + blueValue;
	}
	
	/**
	 * Sets the green value of this color.
	 * 
	 * @param greenValue
	 * @throws Exception if the given green value is negative or bigger than 255
	 */
	public final void setGreenValue(int greenValue) {
		
		Validator.throwExceptionIfValueIsNotInRange("green value", 0, 255, greenValue);
		
		value = getValue() - (getGreenValue() * 256) + (greenValue * 256);
	}
	
	//method
	/**
	 * Sets the red value of this color.
	 * 
	 * @param redValue
	 * @throws Exception if the given red value is negative or bigger than 255
	 */
	public final void setRedValue(int redValue) {
		
		Validator.throwExceptionIfValueIsNotInRange("red value", 0, 255, redValue);
		
		value = getValue() - (getRedValue() * 65536) + (redValue * 65536);
	}
	
	//method
	/**
	 * Sets the value of this color.
	 * @param value
	 * @throws Exception if the given value is no true color value (negative or bigger than 16777215).
	 */
	public final void setValue(int value) {
		
		Validator.throwExceptionIfValueIsNotInRange("value", 0, 16777215, value);
		
		this.value = value;
	}
	
	//method
	/**
	 * Sets the value of this color.
	 * @param value
	 * @throws Exception if the given value is no color name or no true color value
	 */
	public final void setValue(String value) {
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
					throw new RuntimeException("Value '" + value + "' is no color name and no color value.");
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
						throw new RuntimeException("Value '" + value + "' is no color name and no color value.");
				}
				this.value += tempValue * base;
				base *= 16;
			}
		}
	}
}
