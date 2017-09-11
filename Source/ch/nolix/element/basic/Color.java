//package declaration
package ch.nolix.element.basic;

//own imports
import ch.nolix.core.constants.StringCatalogue;
import ch.nolix.core.container.List;
import ch.nolix.core.invalidArgumentException.Argument;
import ch.nolix.core.invalidArgumentException.ErrorPredicate;
import ch.nolix.core.invalidArgumentException.InvalidArgumentException;
import ch.nolix.core.specification.StandardSpecification;
import ch.nolix.core.validator2.Validator;

//class
/**
 * This class represents a true color.
 * A true color is a color that consists of a blue, green and red value that are integers in [0, 255].
 * A color is not mutable.
 * 
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 680
 */
public class Color extends Element {
	
	//type name
	public static final String TYPE_NAME = "Color";
	
	//web colors
		public static final int ALICE_BLUE_INT = 0xF0F8FF;
		public static final String ALICE_BLUE_STRING = "AliceBlue";
		public static final Color ALICE_BLUE = new Color(ALICE_BLUE_INT);
		
		public static final int ANTIQUE_WHITE_INT = 0xFAEBD7;
		public static final String ANTIQUE_WHITE_STRING = "AntiqueWhite";
		public static final Color ANTIQUE_WHITE = new Color(ANTIQUE_WHITE_INT);
	
		public static final int AQUA_INT = 0x00FFFF;
		public static final String AQUA_STRING = "Aqua";
		public static final Color AQUA = new Color(AQUA_INT);
		
		public static final int AQUAMARINE_INT = 0x7FFFD4;
		public static final String AQUAMARINE_STRING = "Aquamarine";
		public static final Color AQUAMARINE = new Color(AQUAMARINE_INT);		
		
		public static final int AZURE_INT = 0xF0FFFF;
		public static final String AZURE_STRING = "Azure";
		public static final Color AZURE = new Color(AZURE_INT);
		
		public static final int BEIGE_INT = 0xF5F5DC;
		public static final String BEIGE_STRING = "Beige";
		public static final Color BEIGE = new Color(BEIGE_INT);
		
		public static final int BISQUE_INT = 0xFFE4C4;
		public static final String BISQUE_STRING = "Bisque";
		public static final Color BISQUE = new Color(BISQUE_INT);
		
		public static final int BLACK_INT = 0x000000;
		public static final String BLACK_STRING = "Black";
		public static final Color BLACK = new Color(BLACK_INT);
		
		public static final int BLANCHED_ALMOND_INT = 0xFFEBCD;
		public static final String BLANCHED_ALMOND_STRING = "BlacnhedAlmond";
		public static final Color BLANCHED_ALMOND = new Color(BLANCHED_ALMOND_INT);
		
		public static final int BLUE_INT = 0x0000FF;
		public static final String BLUE_STRING = "Blue";
		public static final Color BLUE = new Color(BLUE_INT);
		
		public static final int BLUE_VIOLET_INT = 0x8A2BE2;
		public static final String BLUE_VIOLET_STRING = "BlueViolet";
		public static final Color BLUE_VIOLET = new Color(BLUE_VIOLET_INT);
		
		public static final int BROWN_INT = 0xA52A2A;
		public static final String BROWN_STRING = "Brown";
		public static final Color BROWN = new Color(BROWN_INT);
		
		public static final int BURLY_WOOD_INT = 0xDEB887;
		public static final String BURLY_WOOD_STRING = "BurlyWood";
		public static final Color BURLY_WOOD = new Color(BURLY_WOOD_INT);
		
		public static final int CADET_BLUE_INT = 0x5F9EA0;
		public static final String CADET_BLUE_STRING = "CadetBlue";
		public static final Color CADET_BLUE = new Color(CADET_BLUE_INT);
		
		public static final int CHARTREUSE_INT = 0x7FFF00;
		public static final String CHARTREUSE_STRING = "Chartreuse";
		public static final Color CHARTREUSE = new Color(CHARTREUSE_INT);
		
		public static final int CHOCOLATE_INT = 0xD2691E;
		public static final String CHOCOLATE_STRING = "Chocolote";
		public static final Color CHOCOLATE = new Color(CHOCOLATE_INT);
		
		public static final int CORAL_INT = 0xFF7F50;
		public static final String CORAL_STRING = "Coral";
		public static final Color CORAL = new Color(CORAL_INT);
		
		public static final int CORNFLOWER_BLUE_INT = 0x6495ED;
		public static final String CORNFLOWER_BLUE_STRING = "CornflowerBlue";
		public static final Color CORNFLOWER_BLUE = new Color(CORNFLOWER_BLUE_INT);
		
		public static final int CORNSILK_INT = 0xFFF8DC;
		public static final String CORNSILK_STRING = "Cornsilk";
		public static final Color CORNSILK = new Color(CORNSILK_INT);
		
		public static final int CRIMSON_INT = 0xDC143C;
		public static final String CRIMSON_STRING = "Crimson";
		public static final Color CRIMSON = new Color(CRIMSON_INT);
		
		public static final int CYAN_INT = 0x00FFFF;
		public static final String CYAN_STRING = "Cyan";
		public static final Color CYAN = new Color(CYAN_INT);
		
		public static final int DARK_BLUE_INT = 0x00008B;
		public static final String DARK_BLUE_STRING = "DarkBlue";
		public static final Color DARK_BLUE = new Color(DARK_BLUE_INT);
		
		public static final int DARK_CYAN_INT = 0x008B8B;
		public static final String DARK_CYAN_STRING = "DarkCyan";
		public static final Color DARK_CYAN = new Color(DARK_CYAN_INT);
		
		public static final int DARK_GOLDEN_ROD_INT = 0xB8860B;
		public static final String DARK_GOLDEN_ROD_STRING = "DarkGoldenRod";
		public static final Color DARK_GOLDEN_ROD = new Color(DARK_GOLDEN_ROD_INT);
		
		public static final int DARK_GREY_INT = 0xA9A9A9;
		public static final String DARK_GREY_STRING = "DarkGrey";
		public static final Color DARK_GREY = new Color(DARK_GREY_INT);
		
		public static final int DARK_GREEN_INT = 0x006400;
		public static final String DARK_GREEN_STRING = "DarkGreen";
		public static final Color DARK_GREEN = new Color(DARK_GREEN_INT);
		
		public static final int DARK_KHAKI_INT = 0xBDB76B;
		public static final String DARK_KHAKI_STRING = "DarkKhaki";
		public static final Color DARK_KHAKI = new Color(DARK_KHAKI_INT);
		
		public static final int DARK_MAGENTA_INT = 0x8B008B;
		public static final String DARK_MAGENTA_STRING = "DarkMagenta";
		public static final Color DARK_MAGENTA = new Color(DARK_MAGENTA_INT);
		
		public static final int DARK_OLIVE_GREEN_INT = 0x556B2F;
		public static final String DARK_OLIVE_GREEN_STRING = "DarkOliveGreen";
		public static final Color DARK_OLIVE_GREEN = new Color(DARK_OLIVE_GREEN_INT);
		
		public static final int DARK_ORANGE_INT = 0xFF8C00;
		public static final String DARK_ORANGE_STRING = "DarkOrange";
		public static final Color DARK_ORANGE = new Color(DARK_ORANGE_INT);
		
		public static final int DARK_ORCHID_INT = 0x9932CC;
		public static final String  DARK_ORCHID_STRING = "DarkOrchid";
		public static final Color  DARK_ORCHID = new Color( DARK_ORCHID_INT);
		
		public static final int DARK_RED_INT = 0x8B0000;
		public static final String DARK_RED_STRING = "DarkRed";
		public static final Color DARK_RED = new Color(DARK_RED_INT);
		
		public static final int DARK_SALMON_INT = 0xE9967A;
		public static final String DARK_SALMON_STRING = "DarkSalmon";
		public static final Color DARK_SALMON = new Color(DARK_SALMON_INT);
		
		public static final int DARK_SEA_GREEN_INT = 0x8FBC8F;
		public static final String DARK_SEA_GREEN_STRING = "DarkSeaGreen";
		public static final Color DARK_SEA_GREEN = new Color(DARK_SEA_GREEN_INT);
		
		public static final int DARK_SLATE_BLUE_INT = 0x483D8B;
		public static final String DARK_SLATE_BLUE_STRING = "DarkSlateBlue";
		public static final Color DARK_SLATE_BLUE = new Color(DARK_SLATE_BLUE_INT);
		
		public static final int DARK_SLATE_GREY_INT = 0x2F4F4F;
		public static final String DARK_SLATE_GREY_STRING = "DarkSlateGrey";
		public static final Color DARK_SLATE_GREY = new Color(DARK_SLATE_GREY_INT);
		
		public static final int DARK_TURQUOISE_INT = 0x00CED1;
		public static final String DARK_TURQUOISE_STRING = "DarkTurquoise";
		public static final Color DARK_TURQUOISE = new Color(DARK_TURQUOISE_INT);
		
		public static final int DARK_VIOLET_INT = 0x9400D3;
		public static final String DARK_VIOLET_STRING = "DarkViolet";
		public static final Color DARK_VIOLET = new Color(DARK_VIOLET_INT);
		
		public static final int DEEP_PINK_INT = 0xFF1493;
		public static final String DEEP_PINK_STRING = "DeepPink";
		public static final Color DEEP_PINK = new Color(DEEP_PINK_INT);
		
		public static final int DEEP_SKY_BLUE_INT = 0x00BFFF;
		public static final String DEEP_SKY_BLUE_STRING = "DeepSkyBlue";
		public static final Color DEEP_SKY_BLUE = new Color(DEEP_SKY_BLUE_INT);
		
		public static final int DIM_GREY_INT = 0x696969;
		public static final String DIM_GREY_STRING = "DimGrey";
		public static final Color DIM_GREY = new Color(DIM_GREY_INT);
		
		public static final int DODGER_BLUE_INT = 0x1E90FF;
		public static final String DODGER_BLUE_STRING = "DodgetBlue";
		public static final Color DODGER_BLUE = new Color(DODGER_BLUE_INT);
		
		public static final int FIREBRICK_INT = 0xB22222;
		public static final String FIREBRICK_STRING = "FireBrick";
		public static final Color FIREBRICK = new Color(FIREBRICK_INT);
		
		public static final int FLORAL_WHITE_INT = 0xFFFAF0;
		public static final String FLORAL_WHITE_STRING = "FloralWhite";
		public static final Color FLORAL_WHITE = new Color(FLORAL_WHITE_INT);
		
		public static final int FOREST_GREEN_INT = 0x228B22;
		public static final String FOREST_GREEN_STRING = "ForestGreen";
		public static final Color FOREST_GREEN = new Color(FOREST_GREEN_INT);
		
		public static final int FUCHSIA_INT = 0xFF00FF;
		public static final String FUCHSIA_STRING = "Fuchsia";
		public static final Color FUCHSIA = new Color(FUCHSIA_INT);
		
		public static final int GAINSBORO_INT = 0xDCDCDC;
		public static final String GAINSBORO_STRING = "Gainsboro";
		public static final Color GAINSBORO = new Color(GAINSBORO_INT);
		
		public static final int GHOST_WHITE_INT = 0xF8F8FF;
		public static final String GHOST_WHITE_STRING = "GhostWhite";
		public static final Color GHOST_WHITE = new Color(GHOST_WHITE_INT);
		
		public static final int GOLD_INT = 0xFFD700;
		public static final String GOLD_STRING = "Gold";
		public static final Color GOLD = new Color(GOLD_INT);
		
		public static final int GOLDEN_ROD_INT = 0xDAA520;
		public static final String GOLDEN_ROD_STRING = "GoldenRod";
		public static final Color GOLDEN_ROD = new Color(GOLDEN_ROD_INT);
		
		public static final int GREY_INT = 0x808080;
		public static final String GREY_STRING = "Grey";
		public static final Color GREY = new Color(GREY_INT);
		
		public static final int GREEN_INT = 0x008000;
		public static final String GREEN_STRING = "Green";
		public static final Color GREEN = new Color(GREEN_INT);
		
		public static final int GREEN_YELLOW_INT = 0xADFF2F;
		public static final String GREEN_YELLOW_STRING = "GreenYellow";
		public static final Color GREEN_YELLOW = new Color(GREEN_YELLOW_INT);
		
		public static final int HONEY_DEW_INT = 0xF0FFF0;
		public static final String HONEY_DEW_STRING = "HoneyDew";
		public static final Color HONEY_DEW = new Color(HONEY_DEW_INT);
		
		public static final int HOT_PINK_INT = 0xFF69B4;
		public static final String HOT_PINK_STRING = "HotPink";
		public static final Color HOT_PINK = new Color(HOT_PINK_INT);
		
		public static final int INDIAN_RED_INT = 0xCD5C5C;
		public static final String INDIAN_RED_STRING = "IndianRed";
		public static final Color INDIAN_RED = new Color(INDIAN_RED_INT);
		
		public static final int INDIGO_INT = 0x4B0082;
		public static final String INDIGO_STRING = "Indigo";
		public static final Color INDIGO = new Color(INDIGO_INT);
		
		public static final int IVORY_INT = 0xFFFFF0;
		public static final String IVORY_STRING = "Ivory";
		public static final Color IVORY = new Color(IVORY_INT);
		
		public static final int KHAKI_INT = 0xF0E68C;
		public static final String KHAKI_STRING = "Khaki";
		public static final Color KHAKI = new Color(KHAKI_INT);
		
		public static final int LAVENDER_INT = 0xE6E6FA;
		public static final String LAVENDER_STRING = "Lavender";
		public static final Color LAVENDER = new Color(LAVENDER_INT);
		
		public static final int LAVENDER_BLUSH_INT = 0xFFF0F5;
		public static final String LAVENDER_BLUSH_STRING = "LavenderBlush";
		public static final Color LAVENDER_BLUSH = new Color(LAVENDER_BLUSH_INT);
		
		public static final int LAWN_GREEN_INT = 0x7CFC00;
		public static final String LAWN_GREEN_STRING = "LawnGreen";
		public static final Color LAWN_GREEN = new Color(LAWN_GREEN_INT);
		
		public static final int LEMON_CHIFFON_INT = 0xFFFACD;
		public static final String LEMON_CHIFFON_STRING = "LemonChiffon";
		public static final Color LEMON_CHIFFON = new Color(LEMON_CHIFFON_INT);
		
		public static final int LIGHT_BLUE_INT = 0xADD8E6;
		public static final String LIGHT_BLUE_STRING = "LightBlue";
		public static final Color LIGHT_BLUE = new Color(LIGHT_BLUE_INT);
		
		public static final int LIGHT_CORAL_INT = 0xF08080;
		public static final String LIGHT_CORAL_STRING = "LightCoral";
		public static final Color LIGHT_CORAL = new Color(LIGHT_CORAL_INT);
		
		public static final int LIGHT_CYAN_INT = 0xE0FFFF;
		public static final String LIGHT_CYAN_STRING = "LightCyan";
		public static final Color LIGHT_CYAN = new Color(LIGHT_CYAN_INT);
		
		public static final int LIGHT_GOLDEN_ROD_YELLOW_INT = 0xFAFAD2;
		public static final String LIGHT_GOLDEN_ROD_YELLOW_STRING = "LightGoldenRodYellow";
		public static final Color LIGHT_GOLDEN_ROD_YELLOW = new Color(LIGHT_GOLDEN_ROD_YELLOW_INT);
		
		/*
		public static final int _INT = 0x;
		public static final String _STRING = "";
		public static final Color  = new Color(_INT);
		*/

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
	/*
	public static final String LIGHT_BLUE_STRING = "LightBlue";
	public static final int LIGHT_BLUE = 0x7F7FFF;
	public static final String LIGHT_CYAN_STRING = "LightCyan";
	public static final int LIGHT_CYAN = 0x7FFFFF;
	*/
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
	/*
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
	*/
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
	/*
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
	*/
	
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
			/*
			case LIGHT_BLUE:
				return LIGHT_BLUE_STRING;
			case LIGHT_CYAN:
				return LIGHT_CYAN_STRING;
			*/
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
			/*
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
			*/
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
			/*
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
			*/
				
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
				String value = StringCatalogue.HEXADECIMAL_PREFIX;
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
		
			//TODO: Handle all web colors.
			case ALICE_BLUE_STRING:
				this.value = ALICE_BLUE_INT;
				break;
			case ANTIQUE_WHITE_STRING:
				this.value = ANTIQUE_WHITE_INT;
				break;
			
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
			/*
			case LIGHT_BLUE_STRING:
				this.value = LIGHT_BLUE;
				break;
			case LIGHT_CYAN_STRING:
				this.value = LIGHT_CYAN;
				break;
			*/
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
			/*
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
			*/
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
			/*
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
			*/
			
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
				if (value.length() != 8 && !value.substring(2).equals(StringCatalogue.HEXADECIMAL_PREFIX)) {
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
