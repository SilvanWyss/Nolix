//package declaration
package ch.nolix.element.color;

//Java imports
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

//own imports
import ch.nolix.core.constants.StringCatalogue;
import ch.nolix.core.container.ReadContainer;
import ch.nolix.core.helper.StringHelper;
import ch.nolix.core.container.List;
import ch.nolix.core.container.Pair;
import ch.nolix.core.specification.Specification;
import ch.nolix.core.specification.StandardSpecification;
import ch.nolix.element.core.Element;
import ch.nolix.primitive.invalidArgumentException.Argument;
import ch.nolix.primitive.invalidArgumentException.ErrorPredicate;
import ch.nolix.primitive.invalidArgumentException.InvalidArgumentException;
import ch.nolix.primitive.invalidStateException.UnexistingAttributeException;
import ch.nolix.primitive.validator2.Validator;

//class
/**
 * A {@link Color} represents a true color with an alpha value.
 * A true color consists of a blue, green and red value that are integers in [0, 255].
 * So a {@link Color} consists of a blue, green, red and alpha value that are integers in [0, 255].
 * A {@link Color} is not mutable.
 * 
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 1280
 */
public class Color extends Element {
	
	//constant
	public static final String TYPE_NAME = "Color";
	
	//default value
	public static final int DEFAULT_ALPHA_VALUE = 255;
	
	//constants
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
		
		public static final int LIGHT_GREY_INT = 0xD3D3D3;
		public static final String LIGHT_GREY_STRING = "LightGrey";
		public static final Color LIGHT_GREY = new Color(LIGHT_GREY_INT);
		
		public static final int LIGHT_GREEN_INT = 0x90EE90;
		public static final String LIGHT_GREEN_STRING = "LightGreen";
		public static final Color LIGHT_GREEN = new Color(LIGHT_GREEN_INT);
		
		public static final int LIGHT_PINK_INT = 0xFFB6C1;
		public static final String LIGHT_PINK_STRING = "LightPink";
		public static final Color LIGHT_PINK = new Color(LIGHT_PINK_INT);
		
		public static final int LIHGT_SALMON_INT = 0xFFA07A;
		public static final String LIHGT_SALMON_STRING = "LightSalmon";
		public static final Color LIHGT_SALMON = new Color(LIHGT_SALMON_INT);
		
		public static final int LIGHT_SEA_GREEN_INT = 0x20B2AA;
		public static final String LIGHT_SEA_GREEN_STRING = "LightSeaGreen";
		public static final Color LIGHT_SEA_GREEN = new Color(LIGHT_SEA_GREEN_INT);
		
		public static final int LIGHT_SKY_BLUE_INT = 0x87CEFA;
		public static final String LIGHT_SKY_BLUE_STRING = "LightSkyBlue";
		public static final Color LIGHT_SKY_BLUE = new Color(LIGHT_SKY_BLUE_INT);
		
		public static final int LIGHT_SLATE_GREY_INT = 0x778899;
		public static final String LIGHT_SLATE_GREY_STRING = "LightSlateGrey";
		public static final Color LIGHT_SLATE_GREY = new Color(LIGHT_SLATE_GREY_INT);
		
		public static final int LIGHT_STEEL_BLUE_INT = 0xB0C4DE;
		public static final String LIGHT_STEEL_BLUE_STRING = "LightSteelBlue";
		public static final Color LIGHT_STEEL_BLUE = new Color(LIGHT_STEEL_BLUE_INT);
		
		public static final int LIGHT_YELLOW_INT = 0xFFFFE0;
		public static final String LIGHT_YELLOW_STRING = "LightYellow";
		public static final Color LIGHT_YELLOW = new Color(LIGHT_YELLOW_INT);
		
		public static final int LIME_INT = 0x00FF00;
		public static final String LIME_STRING = "Lime";
		public static final Color LIME = new Color(LIME_INT);
		
		public static final int LIME_GREEN_INT = 0x32CD32;
		public static final String LIME_GREEN_STRING = "LimeGreen";
		public static final Color LIME_GREEN = new Color(LIME_GREEN_INT);
		
		public static final int LINEN_INT = 0xFAF0E6;
		public static final String LINEN_STRING = "Linen";
		public static final Color LINEN = new Color(LINEN_INT);
		
		public static final int MAGENTA_INT = 0xFF00FF;
		public static final String MAGENTA_STRING = "Magenta";
		public static final Color MAGENTA = new Color(MAGENTA_INT);
		
		public static final int MAROON_INT = 0x800000;
		public static final String MAROON_STRING = "Maroon";
		public static final Color MAROON = new Color(MAROON_INT);
		
		public static final int MEDIUM_AQUA_MARINE_INT = 0x66CDAA;
		public static final String MEDIUM_AQUA_MARINE_STRING = "MediumAquaMarine";
		public static final Color MEDIUM_AQUA_MARINE = new Color(MEDIUM_AQUA_MARINE_INT);
		
		public static final int MEDIUM_BLUE_INT = 0x0000CD;
		public static final String MEDIUM_BLUE_STRING = "MediumBlue";
		public static final Color MEDIUM_BLUE = new Color(MEDIUM_BLUE_INT);
		
		public static final int MEDIUM_ORCHID_INT = 0xBA55D3;
		public static final String MEDIUM_ORCHID_STRING = "";
		public static final Color MEDIUM_ORCHID = new Color(MEDIUM_ORCHID_INT);
		
		public static final int MEDIUM_PURPLE_INT = 0x9370DB;
		public static final String MEDIUM_PURPLE_STRING = "MediumPurple";
		public static final Color MEDIUM_PURPLE = new Color(MEDIUM_PURPLE_INT);
		
		public static final int MEDIUM_SEA_GREEN_INT = 0x3CB371;
		public static final String MEDIUM_SEA_GREEN_STRING = "";
		public static final Color MEDIUM_SEA_GREEN = new Color(MEDIUM_SEA_GREEN_INT);
		
		public static final int MEDIUM_SLATE_BLUE_INT = 0x7B68EE;
		public static final String MEDIUM_SLATE_BLUE_STRING = "MediumSlateBlue";
		public static final Color MEDIUM_SLATE_BLUE = new Color(MEDIUM_SLATE_BLUE_INT);
		
		public static final int MEDIUM_SPRING_GREEN_INT = 0x00FA9A;
		public static final String MEDIUM_SPRING_GREEN_STRING = "";
		public static final Color MEDIUM_SPRING_GREEN = new Color(MEDIUM_SPRING_GREEN_INT);
		
		public static final int MEDIUM_TURQUOISE_INT = 0x48D1CC;
		public static final String MEDIUM_TURQUOISE_STRING = "MediumTurquoise";
		public static final Color MEDIUM_TURQUOISE = new Color(MEDIUM_TURQUOISE_INT);
		
		public static final int MEDIUM_VIOLET_RED_INT = 0xC71585;
		public static final String MEDIUM_VIOLET_RED_STRING = "MediumVioletRed";
		public static final Color MEDIUM_VIOLET_RED = new Color(MEDIUM_VIOLET_RED_INT);
		
		public static final int MIDNIGHT_BLUE_INT = 0x191970;
		public static final String MIDNIGHT_BLUE_STRING = "MidnightBlue";
		public static final Color MIDNIGHT_BLUE = new Color(MIDNIGHT_BLUE_INT);
		
		public static final int MINT_CREAM_INT = 0xF5FFFA;
		public static final String MINT_CREAM_STRING = "MintCream";
		public static final Color MINT_CREAM = new Color(MINT_CREAM_INT);
		
		public static final int MISTY_ROSE_INT = 0xFFE4E1;
		public static final String MISTY_ROSE_STRING = "MistyRose";
		public static final Color MISTY_ROSE = new Color(MISTY_ROSE_INT);
		
		public static final int MOCCASIN_INT = 0xFFE4B5;
		public static final String MOCCASIN_STRING = "Moccasin";
		public static final Color MOCCASIN = new Color(MOCCASIN_INT);
		
		public static final int NAVAJO_WHITE_INT = 0xFFDEAD;
		public static final String NAVAJO_WHITE_STRING = "NavajoWhite";
		public static final Color NAVAJO_WHITE = new Color(NAVAJO_WHITE_INT);
		
		public static final int NAVY_INT = 0x000080;
		public static final String NAVY_STRING = "Navy";
		public static final Color NAVY = new Color(NAVY_INT);
		
		public static final int OLD_LACE_INT = 0xFDF5E6;
		public static final String OLD_LACE_STRING = "OldLace";
		public static final Color OLD_LACE = new Color(OLD_LACE_INT);
		
		public static final int OLIVE_INT = 0x808000;
		public static final String OLIVE_STRING = "Olive";
		public static final Color OLIVE = new Color(OLIVE_INT);
		
		public static final int OLIVE_DRAB_INT = 0x6B8E23;
		public static final String OLIVE_DRAB_STRING = "OliveDrab";
		public static final Color OLIVE_DRAB = new Color(OLIVE_DRAB_INT);
		
		public static final int ORANGE_INT = 0xFFA500;
		public static final String ORANGE_STRING = "Orange";
		public static final Color ORANGE = new Color(ORANGE_INT);
		
		public static final int ORANGE_RED_INT = 0xFF4500;
		public static final String ORANGE_RED_STRING = "OrangeRed";
		public static final Color ORANGE_RED = new Color(ORANGE_RED_INT);
		
		public static final int ORCHID_INT = 0xDA70D6;
		public static final String ORCHID_STRING = "Orchid";
		public static final Color ORCHID = new Color(ORCHID_INT);
		
		public static final int PALE_GOLDEN_ROD_INT = 0xEEE8AA;
		public static final String PALE_GOLDEN_ROD_STRING = "PaleGoldenRod";
		public static final Color PALE_GOLDEN_ROD = new Color(PALE_GOLDEN_ROD_INT);
		
		public static final int PALE_GREEN_INT = 0x98FB98;
		public static final String PALE_GREEN_STRING = "PaleGreen";
		public static final Color PALE_GREEN = new Color(PALE_GREEN_INT);
		
		public static final int PALE_TURQUOISE_INT = 0xAFEEEE;
		public static final String PALE_TURQUOISE_STRING = "PaleTurquoise";
		public static final Color PALE_TURQUOISE = new Color(PALE_TURQUOISE_INT);
		
		public static final int PALE_VIOLET_RED_INT = 0xDB7093;
		public static final String PALE_VIOLET_RED_STRING = "PaleVioletRed";
		public static final Color PALE_VIOLET_RED = new Color(PALE_VIOLET_RED_INT);
		
		public static final int PAPAYA_WHIP_INT = 0xFFEFD5;
		public static final String PAPAYA_WHIP_STRING = "PapayaWhip";
		public static final Color PAPAYA_WHIP = new Color(PAPAYA_WHIP_INT);
		
		public static final int PEACH_PUFF_INT = 0xFFDAB9;
		public static final String PEACH_PUFF_STRING = "PeachPuff";
		public static final Color PEACH_PUFF = new Color(PEACH_PUFF_INT);
		
		public static final int PERU_INT = 0xCD853F;
		public static final String PERU_STRING = "Peru";
		public static final Color PERU = new Color(PERU_INT);
		
		public static final int PINK_INT = 0xFFC0CB;
		public static final String PINK_STRING = "Pink";
		public static final Color PINK = new Color(PINK_INT);
		
		public static final int PLUM_INT = 0xDDA0DD;
		public static final String PLUM_STRING = "Plum";
		public static final Color PLUM = new Color(PLUM_INT);
		
		public static final int POWDER_BLUE_INT = 0xB0E0E6;
		public static final String POWDER_BLUE_STRING = "PowderBlue";
		public static final Color POWDER_BLUE = new Color(POWDER_BLUE_INT);
		
		public static final int PURPLE_INT = 0x800080;
		public static final String PURPLE_STRING = "Purple";
		public static final Color PURPLE = new Color(PURPLE_INT);
		
		public static final int REBECCA_PURPLE_INT = 0x663399;
		public static final String REBECCA_PURPLE_STRING = "RebeccaPurple";
		public static final Color REBECCA_PURPLE = new Color(REBECCA_PURPLE_INT);
		
		public static final int RED_INT = 0xFF0000;
		public static final String RED_STRING = "Red";
		public static final Color RED = new Color(RED_INT);
		
		public static final int ROSY_BROWN_INT = 0xBC8F8F;
		public static final String ROSY_BROWN_STRING = "RosyBrown";
		public static final Color ROSY_BROWN = new Color(ROSY_BROWN_INT);
		
		public static final int ROYAL_BLUE_INT = 0x4169E1;
		public static final String ROYAL_BLUE_STRING = "RoyalBlue";
		public static final Color ROYAL_BLUE = new Color(ROYAL_BLUE_INT);
		
		public static final int SADDLE_BROWN_INT = 0x8B4513;
		public static final String SADDLE_BROWN_STRING = "SaddleBrown";
		public static final Color SADDLE_BROWN = new Color(SADDLE_BROWN_INT);
		
		public static final int SALMON_INT = 0xFA8072;
		public static final String SALMON_STRING = "Salmon";
		public static final Color SALMON = new Color(SALMON_INT);
		
		public static final int SANDY_BROWN_INT = 0xF4A460;
		public static final String SANDY_BROWN_STRING = "SandyBrown";
		public static final Color SANDY_BROWN = new Color(SANDY_BROWN_INT);
		
		public static final int SEA_GREEN_INT = 0x2E8B57;
		public static final String SEA_GREEN_STRING = "SeaGreen";
		public static final Color SEA_GREEN = new Color(SEA_GREEN_INT);
		
		public static final int SEA_SHELL_INT = 0xFFF5EE;
		public static final String SEA_SHELL_STRING = "SeaShell";
		public static final Color SEA_SHELL = new Color(SEA_SHELL_INT);
		
		public static final int SIENNA_INT = 0xA0522D;
		public static final String SIENNA_STRING = "Sienna";
		public static final Color SIENNA = new Color(SIENNA_INT);
		
		public static final int SILVER_INT = 0xC0C0C0;
		public static final String SILVER_STRING = "Silver";
		public static final Color SILVER = new Color(SILVER_INT);
		
		public static final int SKY_BLUE_INT = 0x87CEEB;
		public static final String SKY_BLUE_STRING = "SkyBlue";
		public static final Color SKY_BLUE = new Color(SKY_BLUE_INT);
		
		public static final int SLATE_BLUE_INT = 0x6A5ACD;
		public static final String SLATE_BLUE_STRING = "SlateBlue";
		public static final Color SLATE_BLUE = new Color(SLATE_BLUE_INT);
		
		public static final int SLATE_GREY_INT = 0x708090;
		public static final String SLATE_GREY_STRING = "SlateGrey";
		public static final Color SLATE_GREY = new Color(SLATE_GREY_INT);
		
		public static final int SNOW_INT = 0xFFFAFA;
		public static final String SNOW_STRING = "Snow";
		public static final Color SNOW = new Color(SNOW_INT);
		
		public static final int SPRING_GREEN_INT = 0x00FF7F;
		public static final String SPRING_GREEN_STRING = "SpringGreen";
		public static final Color SPRING_GREEN = new Color(SPRING_GREEN_INT);
		
		public static final int STEEL_BLUE_INT = 0x4682B4;
		public static final String STEEL_BLUE_STRING = "SteelBlue";
		public static final Color STEEL_BLUE = new Color(STEEL_BLUE_INT);
		
		public static final int TAN_INT = 0xD2B48C;
		public static final String TAN_STRING = "Tan";
		public static final Color TAN = new Color(TAN_INT);
		
		public static final int TEAL_INT = 0x008080;
		public static final String TEAL_STRING = "Teal";
		public static final Color TEAL = new Color(TEAL_INT);
		
		public static final int THISTLE_INT = 0xD8BFD8;
		public static final String THISTLE_STRING = "Thistle";
		public static final Color THISTLE = new Color(THISTLE_INT);
		
		public static final int TOMATO_INT = 0xFF6347;
		public static final String TOMATO_STRING = "Tomato";
		public static final Color TOMATO = new Color(TOMATO_INT);
		
		public static final int TURQUOISE_INT = 0x40E0D0;
		public static final String TURQUOISE_STRING = "Turquoise";
		public static final Color TURQUOISE = new Color(TURQUOISE_INT);
		
		public static final int VIOLET_INT = 0xEE82EE;
		public static final String VIOLET_STRING = "VIOLET";
		public static final Color VIOLET = new Color(VIOLET_INT);
		
		public static final int WHEAT_INT = 0xF5DEB3;
		public static final String WHEAT_STRING = "Wheat";
		public static final Color WHEAT = new Color(WHEAT_INT);
		
		public static final int WHITE_INT = 0xFFFFFF;
		public static final String WHITE_STRING = "White";
		public static final Color WHITE = new Color(WHITE_INT);
		
		public static final int WHITE_SMOKE_INT = 0xF5F5F5;
		public static final String WHITE_SMOKE_STRING = "WhiteSmoke";
		public static final Color WHITE_SMOKE = new Color(WHITE_SMOKE_INT);
		
		public static final int YELLOW_INT = 0xFFFF00;
		public static final String YELLOW_STRING = "Yellow";
		public static final Color YELLOW = new Color(YELLOW_INT);
		
		public static final int YELLOW_GREEN_INT = 0x9ACD32;
		public static final String YELLOW_GREEN_STRING = "YellowGreen";
		public static final Color YELLOW_GREEN = new Color(YELLOW_GREEN_INT);
	
	//limits
	private static final long MIN_COLOR_INT = 0;
	private static final long MAX_COLOR_INT = 4294967296l;
	
	//limits
	private static final short MIN_COLOR_COMPONENT = 0;
	private static final short MAX_COLOR_COMPONENT = 255;
	
	//static attribute
	private static final List<Pair<String, java.lang.Long>> webColorPairs =
	new List<Pair<String, java.lang.Long>>();
	
	//static method
	/**
	 * @param specification
	 * @return a new {@link Color} from the given specification
	 * @throws InvalidArgumentException if the given specification is not valid.
	 */
	public static Color createFromSpecification(final Specification specification) {
		return new Color(specification.getOneAttributeAsString());
	}
	
	//static method
	/**
	 * @param field
	 * @return true if the given field declares a web color string.
	 */
	private static boolean declaresWebColorString(final Field field) {
		return
		Modifier.isStatic(field.getModifiers())
		&& field.getName().endsWith("_STRING");
	}
	
	//static method
	/**
	 * Fills up the web color pairs.
	 */
	private static void fillUpWebColorPairs() {
		
		//Handles the case that the web color pairs are not filled up already.
		if (!webColorPairsAreFilledUp()) {		
			try {
				
				//Iterates the declared fields of the color class.
				for (final var f : Color.class.getDeclaredFields()) {
					
					//Handles the case that the current field declares a web color string.
					if (declaresWebColorString(f)) {
						webColorPairs.addAtEnd(
							new Pair<String, java.lang.Long>(
								f.get(null).toString(),
								getWebColorInt(f)
							)
						);
					}
				}
			}
			catch (final IllegalAccessException illegalAccessException) {
				throw new RuntimeException(illegalAccessException);
			}
		}
	}	
	
	//static method
	/**
	 * 
	 * @param webColorStringField
	 * @return the web color int that corresponds to the given web color string field.
	 */
	private static long getWebColorInt(final Field webColorStringField) {
		try {
			return (int)getWebColorIntField(webColorStringField).get(null);
		}
		catch (final IllegalArgumentException | IllegalAccessException exception) {
			throw new RuntimeException(exception);
		}
	}
	
	//static method
	/**
	 * 
	 * @param webColorStringField
	 * @return the web color int field that corresponds to the given web color string field.
	 * @throws UnexistingAttributeException if the {@link Color} class
	 * does not contain a web color int field for the given web color string field.
	 */
	private static Field getWebColorIntField(final Field webColorStringField) {
		final var webColorIntFieldName =
		StringHelper.createStringWithoutLastCharacters(webColorStringField.getName(), 7) + "_INT";
		
		//Iterates the fields of the color class.
		for (final var f : Color.class.getDeclaredFields()) {
			
			//Handles the case that the current field 2 declares the int value of the web color with the current color name.
			if (
				Modifier.isStatic(f.getModifiers())
				&& f.getName().equals(webColorIntFieldName)
			) {
				return f;
			}
		}
		
		throw
		new UnexistingAttributeException(
			Color.class,
			"web color int field for the given web color string field '"
			+ webColorStringField.getName()
			+ "'"
		);
	}
	
	//static method
	/**
	 * @return the web color pairs.
	 */
	private static ReadContainer<Pair<String, java.lang.Long>> getWebColorPairs() {
		
		fillUpWebColorPairs();
		
		return new ReadContainer<Pair<String, java.lang.Long>>(webColorPairs);
	}
	
	//static method
	/**
	 * @return true if the web color pairs are filled up.
	 */
	private static boolean webColorPairsAreFilledUp() {
		return webColorPairs.containsAny();
	}	
	
	//attributes
	private short redValue;
	private short greenValue;
	private short blueValue;
	private short alphaValue = DEFAULT_ALPHA_VALUE;
	
	//constructor
	/**
	 * Creates a new {@link Color} with default values.
	 */
	public Color() {}
	
	//constructor
	/**
	 * Creates a new {@link Color} with the given value.
	 * 
	 * @param value
	 * @throws OutOfRangeArgumentException if the given value is no true color value with an optional alpha value.
	 */
	public Color(final long value) {
		setValue(value);
	}
	
	//constructor
	/**
	 * Creates a new {@link Color} with the given red value, green value and blue value.
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
	 * Creates a new {@link Color} with the given red value, green value, blue value and alpha value.
	 * 
	 * @param redValue
	 * @param greenValue
	 * @param blueValue
	 * @param alphaValue
	 */
	public Color(final int redValue, final int greenValue, final int blueValue, final int alphaValue) {
		setRedValue(redValue);
		setGreenValue(greenValue);
		setBlueValue(blueValue);
		setAlphaValue(alphaValue);
	}
	
	//constructor
	/**
	 * Creates a new {@link Color} with the given value.
	 * 
	 * @param value
	 * @throws InvalidArgumentException if the given value is no color name
	 * or no true color value with an optional alpha value.
	 */
	public Color(final String value) {
		setValue(value);
	}
	
	//method
	/**
	 * @return a new {@link java.awt.Color} from the current {@link Color}.
	 */
	public java.awt.Color createSwingColor() {
		
		//For a better performance, this implementation does not use all comfortable methods.
		return new java.awt.Color(redValue, greenValue, blueValue, alphaValue);
	}
	
	//method
	/**
	 * @return the alpha value of the current {@link Color}.
	 */
	public int getAlphaValue() {
		return alphaValue;
	}
	
	//method
	/**
	 * @return the attributes of the current {@link Color}.
	 */
	public List<StandardSpecification> getAttributes() {		
		return new List<StandardSpecification>(new StandardSpecification(getStringValue()));
	}
	
	//method
	/**
	 * @return the blue value of the current {@link Color}.
	 */
	public int getBlueValue() {
		return blueValue;
	}
	
	//method
	/**
	 * @return the green value of the current {@link Color}.
	 */
	public int getGreenValue() {
		return greenValue;
	}
	
	//method
	/**
	 * @return the integer value of the current {@link Color}.
	 */
	public long getIntValue() {
		
		//Handles the case that the current color does not have a full alpha value.
		if (!hasFullAlphaValue()) {
			return
			16777216 * getRedValue()
			+ 65536 * getGreenValue()
			+ 256 * getBlueValue()
			+ getAlphaValue();
		}
		
		//Handles the case that the current color has a fuel alpha value.
		return
		65536 * getRedValue()
		+ 256 * getGreenValue()
		+ getBlueValue();
	}
	
	//method
	/**
	 * When a {@link Color} is inverted, the alpha value does not change.
	 * 
	 * @return a new {@link Color} that is the inverted color of the current {@link Color}.
	 */
	public Color getInvertedColor() {
		
		//For a better performance, this implementation does not use all comfortable methods.
		return new Color(
			MAX_COLOR_COMPONENT - redValue,
			MAX_COLOR_COMPONENT - greenValue,
			MAX_COLOR_COMPONENT - blueValue,
			alphaValue
		);
	}
	
	//method
	/**
	 * @return the inverted normalized alpha value of the current {@link Color}.
	 */
	public double getInvertedNormalizedAlphaValue() {
		return (1.0 - getNormalizedAlphaValue());
	}
	
	//method
	/**
	 * @return the inverted normalized blue value of the current {@link Color}.
	 */
	public double getInvertedNormalizedBlueValue() {
		return (1.0 - getNormalizedBlueValue());
	}
	
	//method
	/**
	 * @return the inverted normalized green value of the current {@link Color}.
	 */
	public double getInvertedNormalizedGreenValue() {
		return (1.0 - getNormalizedGreenValue());
	}
	
	//method
	/**
	 * @return the inverted normalized red value of the current {@link Color}.
	 */
	public double getInvertedNormalizedRedValue() {
		return (1.0 - getNormalizedRedValue());
	}
	
	//method
	/**
	 * @return the normalized alpha value of the current {@link Color}.
	 */
	public double getNormalizedAlphaValue() {
		
		//For a better performance, this implementation does not use all comfortable methods.
		return ((double)alphaValue / 256);
	}
	
	//method
	/**
	 * @return the normalized blue value of the current {@link Color}.
	 */
	public double getNormalizedBlueValue() {
		
		//For a better performance, this implementation does not use all comfortable methods.
		return ((double)blueValue / 256);
	}
	
	//method
	/**
	 * @return the normalized green value of the current {@link Color}.
	 */
	public double getNormalizedGreenValue() {
		
		//For a better performance, this implementation does not use all comfortable methods.
		return ((double)greenValue / 256);
	}
	
	//method
	/**
	 * @return the normalized red value of the current {@link Color}.
	 */
	public double getNormalizedRedValue() {
		
		//For a better performance, this implementation does not use all comfortable methods.
		return ((double)redValue / 256);
	}
	
	//method
	/**
	 * @return the red value of the current {@link Color}.
	 */
	public int getRedValue() {
		return redValue;
	}
	
	//method
	/**
	 * @return the string value of the current {@link Color}.
	 */
	public String getStringValue() {
		
		final Pair<String, Long> pair =
		getWebColorPairs().getRefFirstOrNull(wc -> wc.getRefElement2().equals(getIntValue()));
		
		//Handles the case that the current color has a color name.
		if (pair != null) {
			return pair.getRefElement1();
		}
		
		//Handles the case that the current color has no color name.
			var string =
			String.format("0x%02X", redValue)
			+ String.format("0x02X", greenValue)
			+ String.format("0x02X", blueValue);
			
			//Handles the case that the current color has a full alpha value.
			if (!hasFullAlphaValue()) {
				string += String.format("0x02X", alphaValue);
			}
			
			return string;			
	}
	
	
	
	//method
	/**
	 * @return true if the current {@link Color} has an alpha value.
	 */
	public boolean hasAlphaValue() {
		
		//For a better performance, this implementation does not use all comfortable methods.
		return (alphaValue > 0);
	}
	
	//method
	/**
	 * @return true if the current {@link Color} has a blue value.
	 */
	public boolean hasBlueValue() {
		
		//For a better performance, this implementation does not use all comfortable methods.
		return (blueValue > 0);
	}
	
	//method
	/**
	 * @return true if the current {@link Color} has a full alpha value.
	 */
	public boolean hasFullAlphaValue() {
		
		//For a better performance, this implementation does not use all comfortable methods.
		return (alphaValue == MAX_COLOR_COMPONENT);
	}
	
	//method
	/**
	 * @return true if the current {@link Color} has a full blue value.
	 */
	public boolean hasFullBlueValue() {
		
		//For a better performance, this implementation does not use all comfortable methods.
		return (blueValue == MAX_COLOR_COMPONENT);
	}
	
	//method
	/**
	 * @return true if the current {@link Color} has a full green value.
	 */
	public boolean hasFullGreenValue() {
		
		//For a better performance, this implementation does not use all comfortable methods.
		return (greenValue == MAX_COLOR_COMPONENT);
	}
	
	//method
	/**
	 * @return true if the current {@link Color} has a full red value.
	 */
	public boolean hasFullRedValue() {
		
		//For a better performance, this implementation does not use all comfortable methods.
		return (redValue == MAX_COLOR_COMPONENT);
	}
	
	//method
	/**
	 * @return true if the current {@link Color} has a green value.
	 */
	public boolean hasGreenValue() {
		
		//For a better performance, this implementation does not use all comfortable methods.
		return (greenValue > 0);
	}
	
	//method
	/**
	 * @return true if the current {@link Color} has a red value.
	 */
	public boolean hasRedValue() {
		
		//For a better performance, this implementation does not use all comfortable methods.
		return (redValue > 0);
	}
	
	//method
	/**
	 * Sets the alpha value of the current {@link Color}.
	 * 
	 * @param alphaValue
	 */
	private void setAlphaValue(final int alphaValue) {
	
		//Checks if the given alpha value is between 0 and 255.
		Validator
		.suppose(alphaValue)
		.thatIsNamed("alpha value")
		.isBetween(MIN_COLOR_COMPONENT, MAX_COLOR_COMPONENT);
		
		this.alphaValue = (short)alphaValue;
	}
	
	//method
	/**
	 * Sets the blue value of the current {@link Color}.
	 * 
	 * @param blueValue
	 * @throws OutOfRangeException if the given blue value is no true color component (in [0, 255]).
	 */
	private void setBlueValue(final int blueValue) {
		
		//Checks if the given blue value is between 0 and 255.
		Validator
		.suppose(blueValue)
		.thatIsNamed("blue value")
		.isBetween(MIN_COLOR_COMPONENT, MAX_COLOR_COMPONENT);
		
		this.blueValue = (short)blueValue;
	}
	
	//method
	/**
	 * Sets the green value of the current {@link Color}.
	 * 
	 * @param greenValue
	 * @throws OutOfRangeException if the given green value is no true color component (in [0, 255]5).
	 */
	private void setGreenValue(final int greenValue) {
		
		//Checks if the given blue value is between 0 and 255.
		Validator
		.suppose(greenValue)
		.thatIsNamed("green value")
		.isBetween(MIN_COLOR_COMPONENT, MAX_COLOR_COMPONENT);
		
        this.greenValue = (short)greenValue;
	}
	
	//method
	/**
	 * Sets the red value of the current {@link Color}.
	 * 
	 * @param redValue
	 * @throws OutOfRangeException if the given red value is no true color component (in [0, 255]).
	 */
	private void setRedValue(final int redValue) {
		
		//Checks if the given red value is between 0 and 255.
		Validator
		.suppose(redValue)
		.thatIsNamed("red value")
		.isBetween(MIN_COLOR_COMPONENT, MAX_COLOR_COMPONENT);
		
        this.redValue = (short)redValue;
	}
	
	//method
	/**
	 * Sets the value of the current {@link Color}.
	 * 
	 * @param value
	 * @throws OutOfRangeException if the given value is no true color value.
	 */
	private void setValue(long value) {
		
		//Checks if the given value is a true color value.
		Validator.suppose(value).isBetween(MIN_COLOR_INT, MAX_COLOR_INT);
		
		//Handles the case that the given value specifies an alpha value.
		if (value >= 16777216) {
			setAlphaValue((int)(value % 256));
			value /= 256;
		}
		
		setBlueValue((int)(value % 256));
		value /= 256;
		setGreenValue((int)(value % 256));
		value /= 256;
		setRedValue((int)value);
	}
	
	//method
	/**
	 * Sets the value of the current {@link Color}.
	 * 
	 * @param value
	 * @throws InvalidArgumentException if the given value is no color name or no color value.
	 */
	private void setValue(final String value) {
		
		final Pair<String, java.lang.Long> pair
		= getWebColorPairs().getRefFirstOrNull(p -> p.getRefElement1().equals(value));
		
		//Handles the case that the given value is not a color name.
		if (pair == null) {
			
			if (
				(value.length() != 8 || value.length() != 10)
				&& !value.substring(0, 2).equals(StringCatalogue.HEXADECIMAL_PREFIX)) {
				throw new InvalidArgumentException(
					new Argument(value),
					new ErrorPredicate("is no color name or color value")
				);	
			}
			
			//For a better performance, this implementation does not use all comfortable methods.
			redValue = (short)getValue(value.substring(2, 4));
			greenValue = (short)getValue(value.substring(4, 6));
			blueValue = (short)getValue(value.substring(6, 8));
			
			//Handles the case that the given value specifies an alpha value.
			if (value.length() == 10) {
				
				//For a better performance, this implementation does not use all comfortable methods.
				alphaValue = (short)getValue(value.substring(8, 10));
			}
		}
		
		//Handles the case that the given value is a color name.
		else {
			setValue(pair.getRefElement2());
		}
	}
		
	private int getValue(final String string) {
		
		var value = 0;
		var base = 1;
		
		//Iterates the given string.
		for (var i = string.length() - 1; i >= 0; i--) {
			
			var tempValue = 0;
			
			//Enumerates the current character.
			switch (string.charAt(i)) {
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
					throw new InvalidArgumentException(new Argument(string));
			}
			
			value += tempValue * base;
			base *= 16;
		}
		
		return value;
	}
}
