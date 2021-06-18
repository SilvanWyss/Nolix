//package declaration
package ch.nolix.element.gui.color;

//own imports
import ch.nolix.common.constant.StringCatalogue;
import ch.nolix.common.container.IContainer;
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.container.pair.Pair;
import ch.nolix.common.document.node.BaseNode;
import ch.nolix.common.document.node.Node;
import ch.nolix.common.errorcontrol.invalidargumentexception.ArgumentIsOutOfRangeException;
import ch.nolix.common.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.common.errorcontrol.invalidargumentexception.UnrepresentingArgumentException;
import ch.nolix.common.errorcontrol.validator.Validator;
import ch.nolix.element.gui.base.Element;

//class
/**
 * A {@link Color} represents a true color with an alpha value.
 * A true color consists of a blue, green and red value that are integers in [0, 255].
 * So a {@link Color} consists of a blue, green, red and alpha value that are integers in [0, 255].
 * A {@link Color} is not mutable.
 * 
 * @author Silvan Wyss
 * @date 2016-01-01
 * @lines 1320
 */
public final class Color extends Element<Color> {
	
	//constants
	public static final int ALICE_BLUE_INT = 0xF0F8FF;
	public static final String ALICE_BLUE_STRING = "AliceBlue";
	public static final Color ALICE_BLUE = fromValue(ALICE_BLUE_INT);
	
	//constants
	public static final int ANTIQUE_WHITE_INT = 0xFAEBD7;
	public static final String ANTIQUE_WHITE_STRING = "AntiqueWhite";
	public static final Color ANTIQUE_WHITE = fromValue(ANTIQUE_WHITE_INT);
	
	//constants
	public static final int AQUA_INT = 0x00FFFF;
	public static final String AQUA_STRING = "Aqua";
	public static final Color AQUA = fromValue(AQUA_INT);
	
	//constants
	public static final int AQUAMARINE_INT = 0x7FFFD4;
	public static final String AQUAMARINE_STRING = "Aquamarine";
	public static final Color AQUAMARINE = fromValue(AQUAMARINE_INT);
	
	//constants
	public static final int AZURE_INT = 0xF0FFFF;
	public static final String AZURE_STRING = "Azure";
	public static final Color AZURE = fromValue(AZURE_INT);
	
	//constants
	public static final int BEIGE_INT = 0xF5F5DC;
	public static final String BEIGE_STRING = "Beige";
	public static final Color BEIGE = fromValue(BEIGE_INT);
	
	//constants
	public static final int BISQUE_INT = 0xFFE4C4;
	public static final String BISQUE_STRING = "Bisque";
	public static final Color BISQUE = fromValue(BISQUE_INT);
	
	//constants
	public static final int BLACK_INT = 0x000000;
	public static final String BLACK_STRING = "Black";
	public static final Color BLACK = fromValue(BLACK_INT);
	
	//constants
	public static final int BLANCHED_ALMOND_INT = 0xFFEBCD;
	public static final String BLANCHED_ALMOND_STRING = "BlacnhedAlmond";
	public static final Color BLANCHED_ALMOND = fromValue(BLANCHED_ALMOND_INT);
	
	//constants
	public static final int BLUE_INT = 0x0000FF;
	public static final String BLUE_STRING = "Blue";
	public static final Color BLUE = fromValue(BLUE_INT);
	
	//constants
	public static final int BLUE_VIOLET_INT = 0x8A2BE2;
	public static final String BLUE_VIOLET_STRING = "BlueViolet";
	public static final Color BLUE_VIOLET = fromValue(BLUE_VIOLET_INT);
	
	//constants
	public static final int BROWN_INT = 0xA52A2A;
	public static final String BROWN_STRING = "Brown";
	public static final Color BROWN = fromValue(BROWN_INT);
	
	//constants
	public static final int BURLY_WOOD_INT = 0xDEB887;
	public static final String BURLY_WOOD_STRING = "BurlyWood";
	public static final Color BURLY_WOOD = fromValue(BURLY_WOOD_INT);
	
	//constants
	public static final int CADET_BLUE_INT = 0x5F9EA0;
	public static final String CADET_BLUE_STRING = "CadetBlue";
	public static final Color CADET_BLUE = fromValue(CADET_BLUE_INT);
	
	//constants
	public static final int CHARTREUSE_INT = 0x7FFF00;
	public static final String CHARTREUSE_STRING = "Chartreuse";
	public static final Color CHARTREUSE = fromValue(CHARTREUSE_INT);
	
	//constants
	public static final int CHOCOLATE_INT = 0xD2691E;
	public static final String CHOCOLATE_STRING = "Chocolote";
	public static final Color CHOCOLATE = fromValue(CHOCOLATE_INT);
	
	//constants
	public static final int CORAL_INT = 0xFF7F50;
	public static final String CORAL_STRING = "Coral";
	public static final Color CORAL = fromValue(CORAL_INT);
	
	//constants
	public static final int CORNFLOWER_BLUE_INT = 0x6495ED;
	public static final String CORNFLOWER_BLUE_STRING = "CornflowerBlue";
	public static final Color CORNFLOWER_BLUE = fromValue(CORNFLOWER_BLUE_INT);
	
	//constants
	public static final int CORNSILK_INT = 0xFFF8DC;
	public static final String CORNSILK_STRING = "Cornsilk";
	public static final Color CORNSILK = fromValue(CORNSILK_INT);
	
	//constants
	public static final int CRIMSON_INT = 0xDC143C;
	public static final String CRIMSON_STRING = "Crimson";
	public static final Color CRIMSON = fromValue(CRIMSON_INT);
	
	//constants
	public static final int CYAN_INT = 0x00FFFF;
	public static final String CYAN_STRING = "Cyan";
	public static final Color CYAN = fromValue(CYAN_INT);
	
	//constants
	public static final int DARK_BLUE_INT = 0x00008B;
	public static final String DARK_BLUE_STRING = "DarkBlue";
	public static final Color DARK_BLUE = fromValue(DARK_BLUE_INT);
	
	//constants
	public static final int DARK_CYAN_INT = 0x008B8B;
	public static final String DARK_CYAN_STRING = "DarkCyan";
	public static final Color DARK_CYAN = fromValue(DARK_CYAN_INT);
	
	//constants
	public static final int DARK_GOLDEN_ROD_INT = 0xB8860B;
	public static final String DARK_GOLDEN_ROD_STRING = "DarkGoldenRod";
	public static final Color DARK_GOLDEN_ROD = fromValue(DARK_GOLDEN_ROD_INT);
	
	//constants
	public static final int DARK_GREY_INT = 0xA9A9A9;
	public static final String DARK_GREY_STRING = "DarkGrey";
	public static final Color DARK_GREY = fromValue(DARK_GREY_INT);
	
	//constants
	public static final int DARK_GREEN_INT = 0x006400;
	public static final String DARK_GREEN_STRING = "DarkGreen";
	public static final Color DARK_GREEN = fromValue(DARK_GREEN_INT);
	
	//constants
	public static final int DARK_KHAKI_INT = 0xBDB76B;
	public static final String DARK_KHAKI_STRING = "DarkKhaki";
	public static final Color DARK_KHAKI = fromValue(DARK_KHAKI_INT);
	
	//constants
	public static final int DARK_MAGENTA_INT = 0x8B008B;
	public static final String DARK_MAGENTA_STRING = "DarkMagenta";
	public static final Color DARK_MAGENTA = fromValue(DARK_MAGENTA_INT);
	
	//constants
	public static final int DARK_OLIVE_GREEN_INT = 0x556B2F;
	public static final String DARK_OLIVE_GREEN_STRING = "DarkOliveGreen";
	public static final Color DARK_OLIVE_GREEN = fromValue(DARK_OLIVE_GREEN_INT);
	
	//constants
	public static final int DARK_ORANGE_INT = 0xFF8C00;
	public static final String DARK_ORANGE_STRING = "DarkOrange";
	public static final Color DARK_ORANGE = fromValue(DARK_ORANGE_INT);
	
	//constants
	public static final int DARK_ORCHID_INT = 0x9932CC;
	public static final String DARK_ORCHID_STRING = "DarkOrchid";
	public static final Color DARK_ORCHID = fromValue( DARK_ORCHID_INT);
	
	//constants
	public static final int DARK_RED_INT = 0x8B0000;
	public static final String DARK_RED_STRING = "DarkRed";
	public static final Color DARK_RED = fromValue(DARK_RED_INT);
	
	//constants
	public static final int DARK_SALMON_INT = 0xE9967A;
	public static final String DARK_SALMON_STRING = "DarkSalmon";
	public static final Color DARK_SALMON = fromValue(DARK_SALMON_INT);
	
	//constants
	public static final int DARK_SEA_GREEN_INT = 0x8FBC8F;
	public static final String DARK_SEA_GREEN_STRING = "DarkSeaGreen";
	public static final Color DARK_SEA_GREEN = fromValue(DARK_SEA_GREEN_INT);
	
	//constants
	public static final int DARK_SLATE_BLUE_INT = 0x483D8B;
	public static final String DARK_SLATE_BLUE_STRING = "DarkSlateBlue";
	public static final Color DARK_SLATE_BLUE = fromValue(DARK_SLATE_BLUE_INT);
	
	//constants
	public static final int DARK_SLATE_GREY_INT = 0x2F4F4F;
	public static final String DARK_SLATE_GREY_STRING = "DarkSlateGrey";
	public static final Color DARK_SLATE_GREY = fromValue(DARK_SLATE_GREY_INT);
	
	//constants
	public static final int DARK_TURQUOISE_INT = 0x00CED1;
	public static final String DARK_TURQUOISE_STRING = "DarkTurquoise";
	public static final Color DARK_TURQUOISE = fromValue(DARK_TURQUOISE_INT);
	
	//constants
	public static final int DARK_VIOLET_INT = 0x9400D3;
	public static final String DARK_VIOLET_STRING = "DarkViolet";
	public static final Color DARK_VIOLET = fromValue(DARK_VIOLET_INT);
	
	//constants
	public static final int DEEP_PINK_INT = 0xFF1493;
	public static final String DEEP_PINK_STRING = "DeepPink";
	public static final Color DEEP_PINK = fromValue(DEEP_PINK_INT);
	
	//constants
	public static final int DEEP_SKY_BLUE_INT = 0x00BFFF;
	public static final String DEEP_SKY_BLUE_STRING = "DeepSkyBlue";
	public static final Color DEEP_SKY_BLUE = fromValue(DEEP_SKY_BLUE_INT);
	
	//constants
	public static final int DIM_GREY_INT = 0x696969;
	public static final String DIM_GREY_STRING = "DimGrey";
	public static final Color DIM_GREY = fromValue(DIM_GREY_INT);
	
	//constants
	public static final int DODGER_BLUE_INT = 0x1E90FF;
	public static final String DODGER_BLUE_STRING = "DodgetBlue";
	public static final Color DODGER_BLUE = fromValue(DODGER_BLUE_INT);
	
	//constants
	public static final int FIREBRICK_INT = 0xB22222;
	public static final String FIREBRICK_STRING = "FireBrick";
	public static final Color FIREBRICK = fromValue(FIREBRICK_INT);
	
	//constants
	public static final int FLORAL_WHITE_INT = 0xFFFAF0;
	public static final String FLORAL_WHITE_STRING = "FloralWhite";
	public static final Color FLORAL_WHITE = fromValue(FLORAL_WHITE_INT);
	
	//constants
	public static final int FOREST_GREEN_INT = 0x228B22;
	public static final String FOREST_GREEN_STRING = "ForestGreen";
	public static final Color FOREST_GREEN = fromValue(FOREST_GREEN_INT);
	
	//constants
	public static final int FUCHSIA_INT = 0xFF00FF;
	public static final String FUCHSIA_STRING = "Fuchsia";
	public static final Color FUCHSIA = fromValue(FUCHSIA_INT);
	
	//constants
	public static final int GAINSBORO_INT = 0xDCDCDC;
	public static final String GAINSBORO_STRING = "Gainsboro";
	public static final Color GAINSBORO = fromValue(GAINSBORO_INT);
	
	//constants
	public static final int GHOST_WHITE_INT = 0xF8F8FF;
	public static final String GHOST_WHITE_STRING = "GhostWhite";
	public static final Color GHOST_WHITE = fromValue(GHOST_WHITE_INT);
	
	//constants
	public static final int GOLD_INT = 0xFFD700;
	public static final String GOLD_STRING = "Gold";
	public static final Color GOLD = fromValue(GOLD_INT);
	
	//constants
	public static final int GOLDEN_ROD_INT = 0xDAA520;
	public static final String GOLDEN_ROD_STRING = "GoldenRod";
	public static final Color GOLDEN_ROD = fromValue(GOLDEN_ROD_INT);
	
	//constants
	public static final int GREY_INT = 0x808080;
	public static final String GREY_STRING = "Grey";
	public static final Color GREY = fromValue(GREY_INT);
	
	//constants
	public static final int GREEN_INT = 0x008000;
	public static final String GREEN_STRING = "Green";
	public static final Color GREEN = fromValue(GREEN_INT);
	
	//constants
	public static final int GREEN_YELLOW_INT = 0xADFF2F;
	public static final String GREEN_YELLOW_STRING = "GreenYellow";
	public static final Color GREEN_YELLOW = fromValue(GREEN_YELLOW_INT);
	
	//constants
	public static final int HONEY_DEW_INT = 0xF0FFF0;
	public static final String HONEY_DEW_STRING = "HoneyDew";
	public static final Color HONEY_DEW = fromValue(HONEY_DEW_INT);
	
	//constants
	public static final int HOT_PINK_INT = 0xFF69B4;
	public static final String HOT_PINK_STRING = "HotPink";
	public static final Color HOT_PINK = fromValue(HOT_PINK_INT);
	
	//constants
	public static final int INDIAN_RED_INT = 0xCD5C5C;
	public static final String INDIAN_RED_STRING = "IndianRed";
	public static final Color INDIAN_RED = fromValue(INDIAN_RED_INT);
	
	//constants
	public static final int INDIGO_INT = 0x4B0082;
	public static final String INDIGO_STRING = "Indigo";
	public static final Color INDIGO = fromValue(INDIGO_INT);
	
	//constants
	public static final int IVORY_INT = 0xFFFFF0;
	public static final String IVORY_STRING = "Ivory";
	public static final Color IVORY = fromValue(IVORY_INT);
	
	//constants
	public static final int KHAKI_INT = 0xF0E68C;
	public static final String KHAKI_STRING = "Khaki";
	public static final Color KHAKI = fromValue(KHAKI_INT);
	
	//constants
	public static final int LAVENDER_INT = 0xE6E6FA;
	public static final String LAVENDER_STRING = "Lavender";
	public static final Color LAVENDER = fromValue(LAVENDER_INT);
	
	//constants
	public static final int LAVENDER_BLUSH_INT = 0xFFF0F5;
	public static final String LAVENDER_BLUSH_STRING = "LavenderBlush";
	public static final Color LAVENDER_BLUSH = fromValue(LAVENDER_BLUSH_INT);
	
	//constants
	public static final int LAWN_GREEN_INT = 0x7CFC00;
	public static final String LAWN_GREEN_STRING = "LawnGreen";
	public static final Color LAWN_GREEN = fromValue(LAWN_GREEN_INT);
	
	//constants
	public static final int LEMON_CHIFFON_INT = 0xFFFACD;
	public static final String LEMON_CHIFFON_STRING = "LemonChiffon";
	public static final Color LEMON_CHIFFON = fromValue(LEMON_CHIFFON_INT);
	
	//constants
	public static final int LIGHT_BLUE_INT = 0xADD8E6;
	public static final String LIGHT_BLUE_STRING = "LightBlue";
	public static final Color LIGHT_BLUE = fromValue(LIGHT_BLUE_INT);
	
	//constants
	public static final int LIGHT_CORAL_INT = 0xF08080;
	public static final String LIGHT_CORAL_STRING = "LightCoral";
	public static final Color LIGHT_CORAL = fromValue(LIGHT_CORAL_INT);
	
	//constants
	public static final int LIGHT_CYAN_INT = 0xE0FFFF;
	public static final String LIGHT_CYAN_STRING = "LightCyan";
	public static final Color LIGHT_CYAN = fromValue(LIGHT_CYAN_INT);
	
	//constants
	public static final int LIGHT_GOLDEN_ROD_YELLOW_INT = 0xFAFAD2;
	public static final String LIGHT_GOLDEN_ROD_YELLOW_STRING = "LightGoldenRodYellow";
	public static final Color LIGHT_GOLDEN_ROD_YELLOW = fromValue(LIGHT_GOLDEN_ROD_YELLOW_INT);
	
	//constants
	public static final int LIGHT_GREY_INT = 0xD3D3D3;
	public static final String LIGHT_GREY_STRING = "LightGrey";
	public static final Color LIGHT_GREY = fromValue(LIGHT_GREY_INT);
	
	//constants
	public static final int LIGHT_GREEN_INT = 0x90EE90;
	public static final String LIGHT_GREEN_STRING = "LightGreen";
	public static final Color LIGHT_GREEN = fromValue(LIGHT_GREEN_INT);
	
	//constants
	public static final int LIGHT_PINK_INT = 0xFFB6C1;
	public static final String LIGHT_PINK_STRING = "LightPink";
	public static final Color LIGHT_PINK = fromValue(LIGHT_PINK_INT);
	
	//constants
	public static final int LIHGT_SALMON_INT = 0xFFA07A;
	public static final String LIHGT_SALMON_STRING = "LightSalmon";
	public static final Color LIHGT_SALMON = fromValue(LIHGT_SALMON_INT);
	
	//constants
	public static final int LIGHT_SEA_GREEN_INT = 0x20B2AA;
	public static final String LIGHT_SEA_GREEN_STRING = "LightSeaGreen";
	public static final Color LIGHT_SEA_GREEN = fromValue(LIGHT_SEA_GREEN_INT);
	
	//constants
	public static final int LIGHT_SKY_BLUE_INT = 0x87CEFA;
	public static final String LIGHT_SKY_BLUE_STRING = "LightSkyBlue";
	public static final Color LIGHT_SKY_BLUE = fromValue(LIGHT_SKY_BLUE_INT);
	
	//constants
	public static final int LIGHT_SLATE_GREY_INT = 0x778899;
	public static final String LIGHT_SLATE_GREY_STRING = "LightSlateGrey";
	public static final Color LIGHT_SLATE_GREY = fromValue(LIGHT_SLATE_GREY_INT);
	
	//constants
	public static final int LIGHT_STEEL_BLUE_INT = 0xB0C4DE;
	public static final String LIGHT_STEEL_BLUE_STRING = "LightSteelBlue";
	public static final Color LIGHT_STEEL_BLUE = fromValue(LIGHT_STEEL_BLUE_INT);
	
	//constants
	public static final int LIGHT_YELLOW_INT = 0xFFFFE0;
	public static final String LIGHT_YELLOW_STRING = "LightYellow";
	public static final Color LIGHT_YELLOW = fromValue(LIGHT_YELLOW_INT);
	
	//constants
	public static final int LIME_INT = 0x00FF00;
	public static final String LIME_STRING = "Lime";
	public static final Color LIME = fromValue(LIME_INT);
	
	//constants
	public static final int LIME_GREEN_INT = 0x32CD32;
	public static final String LIME_GREEN_STRING = "LimeGreen";
	public static final Color LIME_GREEN = fromValue(LIME_GREEN_INT);
	
	//constants
	public static final int LINEN_INT = 0xFAF0E6;
	public static final String LINEN_STRING = "Linen";
	public static final Color LINEN = fromValue(LINEN_INT);
	
	//constants
	public static final int MAGENTA_INT = 0xFF00FF;
	public static final String MAGENTA_STRING = "Magenta";
	public static final Color MAGENTA = fromValue(MAGENTA_INT);
	
	//constants
	public static final int MAROON_INT = 0x800000;
	public static final String MAROON_STRING = "Maroon";
	public static final Color MAROON = fromValue(MAROON_INT);
	
	//constants
	public static final int MEDIUM_AQUA_MARINE_INT = 0x66CDAA;
	public static final String MEDIUM_AQUA_MARINE_STRING = "MediumAquaMarine";
	public static final Color MEDIUM_AQUA_MARINE = fromValue(MEDIUM_AQUA_MARINE_INT);
	
	//constants
	public static final int MEDIUM_BLUE_INT = 0x0000CD;
	public static final String MEDIUM_BLUE_STRING = "MediumBlue";
	public static final Color MEDIUM_BLUE = fromValue(MEDIUM_BLUE_INT);
	
	//constants
	public static final int MEDIUM_ORCHID_INT = 0xBA55D3;
	public static final String MEDIUM_ORCHID_STRING = "";
	public static final Color MEDIUM_ORCHID = fromValue(MEDIUM_ORCHID_INT);
	
	//constants
	public static final int MEDIUM_PURPLE_INT = 0x9370DB;
	public static final String MEDIUM_PURPLE_STRING = "MediumPurple";
	public static final Color MEDIUM_PURPLE = fromValue(MEDIUM_PURPLE_INT);
	
	//constants
	public static final int MEDIUM_SEA_GREEN_INT = 0x3CB371;
	public static final String MEDIUM_SEA_GREEN_STRING = "";
	public static final Color MEDIUM_SEA_GREEN = fromValue(MEDIUM_SEA_GREEN_INT);
	
	//constants
	public static final int MEDIUM_SLATE_BLUE_INT = 0x7B68EE;
	public static final String MEDIUM_SLATE_BLUE_STRING = "MediumSlateBlue";
	public static final Color MEDIUM_SLATE_BLUE = fromValue(MEDIUM_SLATE_BLUE_INT);
	
	//constants
	public static final int MEDIUM_SPRING_GREEN_INT = 0x00FA9A;
	public static final String MEDIUM_SPRING_GREEN_STRING = "";
	public static final Color MEDIUM_SPRING_GREEN = fromValue(MEDIUM_SPRING_GREEN_INT);
	
	//constants
	public static final int MEDIUM_TURQUOISE_INT = 0x48D1CC;
	public static final String MEDIUM_TURQUOISE_STRING = "MediumTurquoise";
	public static final Color MEDIUM_TURQUOISE = fromValue(MEDIUM_TURQUOISE_INT);
	
	//constants
	public static final int MEDIUM_VIOLET_RED_INT = 0xC71585;
	public static final String MEDIUM_VIOLET_RED_STRING = "MediumVioletRed";
	public static final Color MEDIUM_VIOLET_RED = fromValue(MEDIUM_VIOLET_RED_INT);
	
	//constants
	public static final int MIDNIGHT_BLUE_INT = 0x191970;
	public static final String MIDNIGHT_BLUE_STRING = "MidnightBlue";
	public static final Color MIDNIGHT_BLUE = fromValue(MIDNIGHT_BLUE_INT);
	
	//constants
	public static final int MINT_CREAM_INT = 0xF5FFFA;
	public static final String MINT_CREAM_STRING = "MintCream";
	public static final Color MINT_CREAM = fromValue(MINT_CREAM_INT);
	
	//constants
	public static final int MISTY_ROSE_INT = 0xFFE4E1;
	public static final String MISTY_ROSE_STRING = "MistyRose";
	public static final Color MISTY_ROSE = fromValue(MISTY_ROSE_INT);
	
	//constants
	public static final int MOCCASIN_INT = 0xFFE4B5;
	public static final String MOCCASIN_STRING = "Moccasin";
	public static final Color MOCCASIN = fromValue(MOCCASIN_INT);
	
	//constants
	public static final int NAVAJO_WHITE_INT = 0xFFDEAD;
	public static final String NAVAJO_WHITE_STRING = "NavajoWhite";
	public static final Color NAVAJO_WHITE = fromValue(NAVAJO_WHITE_INT);
	
	//constants
	public static final int NAVY_INT = 0x000080;
	public static final String NAVY_STRING = "Navy";
	public static final Color NAVY = fromValue(NAVY_INT);
	
	//constants
	public static final int OLD_LACE_INT = 0xFDF5E6;
	public static final String OLD_LACE_STRING = "OldLace";
	public static final Color OLD_LACE = fromValue(OLD_LACE_INT);
	
	//constants
	public static final int OLIVE_INT = 0x808000;
	public static final String OLIVE_STRING = "Olive";
	public static final Color OLIVE = fromValue(OLIVE_INT);
	
	//constants
	public static final int OLIVE_DRAB_INT = 0x6B8E23;
	public static final String OLIVE_DRAB_STRING = "OliveDrab";
	public static final Color OLIVE_DRAB = fromValue(OLIVE_DRAB_INT);
	
	//constants
	public static final int ORANGE_INT = 0xFFA500;
	public static final String ORANGE_STRING = "Orange";
	public static final Color ORANGE = fromValue(ORANGE_INT);
	
	//constants
	public static final int ORANGE_RED_INT = 0xFF4500;
	public static final String ORANGE_RED_STRING = "OrangeRed";
	public static final Color ORANGE_RED = fromValue(ORANGE_RED_INT);
	
	//constants
	public static final int ORCHID_INT = 0xDA70D6;
	public static final String ORCHID_STRING = "Orchid";
	public static final Color ORCHID = fromValue(ORCHID_INT);
	
	//constants
	public static final int PALE_GOLDEN_ROD_INT = 0xEEE8AA;
	public static final String PALE_GOLDEN_ROD_STRING = "PaleGoldenRod";
	public static final Color PALE_GOLDEN_ROD = fromValue(PALE_GOLDEN_ROD_INT);
	
	//constants
	public static final int PALE_GREEN_INT = 0x98FB98;
	public static final String PALE_GREEN_STRING = "PaleGreen";
	public static final Color PALE_GREEN = fromValue(PALE_GREEN_INT);
	
	//constants
	public static final int PALE_TURQUOISE_INT = 0xAFEEEE;
	public static final String PALE_TURQUOISE_STRING = "PaleTurquoise";
	public static final Color PALE_TURQUOISE = fromValue(PALE_TURQUOISE_INT);
	
	//constants
	public static final int PALE_VIOLET_RED_INT = 0xDB7093;
	public static final String PALE_VIOLET_RED_STRING = "PaleVioletRed";
	public static final Color PALE_VIOLET_RED = fromValue(PALE_VIOLET_RED_INT);
	
	//constants
	public static final int PAPAYA_WHIP_INT = 0xFFEFD5;
	public static final String PAPAYA_WHIP_STRING = "PapayaWhip";
	public static final Color PAPAYA_WHIP = fromValue(PAPAYA_WHIP_INT);
	
	//constants
	public static final int PEACH_PUFF_INT = 0xFFDAB9;
	public static final String PEACH_PUFF_STRING = "PeachPuff";
	public static final Color PEACH_PUFF = fromValue(PEACH_PUFF_INT);
	
	//constants
	public static final int PERU_INT = 0xCD853F;
	public static final String PERU_STRING = "Peru";
	public static final Color PERU = fromValue(PERU_INT);
	
	//constants
	public static final int PINK_INT = 0xFFC0CB;
	public static final String PINK_STRING = "Pink";
	public static final Color PINK = fromValue(PINK_INT);
	
	//constants
	public static final int PLUM_INT = 0xDDA0DD;
	public static final String PLUM_STRING = "Plum";
	public static final Color PLUM = fromValue(PLUM_INT);
	
	//constants
	public static final int POWDER_BLUE_INT = 0xB0E0E6;
	public static final String POWDER_BLUE_STRING = "PowderBlue";
	public static final Color POWDER_BLUE = fromValue(POWDER_BLUE_INT);
	
	//constants
	public static final int PURPLE_INT = 0x800080;
	public static final String PURPLE_STRING = "Purple";
	public static final Color PURPLE = fromValue(PURPLE_INT);
	
	//constants
	public static final int REBECCA_PURPLE_INT = 0x663399;
	public static final String REBECCA_PURPLE_STRING = "RebeccaPurple";
	public static final Color REBECCA_PURPLE = fromValue(REBECCA_PURPLE_INT);
	
	//constants
	public static final int RED_INT = 0xFF0000;
	public static final String RED_STRING = "Red";
	public static final Color RED = fromValue(RED_INT);
	
	//constants
	public static final int ROSY_BROWN_INT = 0xBC8F8F;
	public static final String ROSY_BROWN_STRING = "RosyBrown";
	public static final Color ROSY_BROWN = fromValue(ROSY_BROWN_INT);
	
	//constants
	public static final int ROYAL_BLUE_INT = 0x4169E1;
	public static final String ROYAL_BLUE_STRING = "RoyalBlue";
	public static final Color ROYAL_BLUE = fromValue(ROYAL_BLUE_INT);
	
	//constants
	public static final int SADDLE_BROWN_INT = 0x8B4513;
	public static final String SADDLE_BROWN_STRING = "SaddleBrown";
	public static final Color SADDLE_BROWN = fromValue(SADDLE_BROWN_INT);
	
	//constants
	public static final int SALMON_INT = 0xFA8072;
	public static final String SALMON_STRING = "Salmon";
	public static final Color SALMON = fromValue(SALMON_INT);
	
	//constants
	public static final int SANDY_BROWN_INT = 0xF4A460;
	public static final String SANDY_BROWN_STRING = "SandyBrown";
	public static final Color SANDY_BROWN = fromValue(SANDY_BROWN_INT);
	
	//constants
	public static final int SEA_GREEN_INT = 0x2E8B57;
	public static final String SEA_GREEN_STRING = "SeaGreen";
	public static final Color SEA_GREEN = fromValue(SEA_GREEN_INT);
	
	//constants
	public static final int SEA_SHELL_INT = 0xFFF5EE;
	public static final String SEA_SHELL_STRING = "SeaShell";
	public static final Color SEA_SHELL = fromValue(SEA_SHELL_INT);
	
	//constants
	public static final int SIENNA_INT = 0xA0522D;
	public static final String SIENNA_STRING = "Sienna";
	public static final Color SIENNA = fromValue(SIENNA_INT);
	
	//constants
	public static final int SILVER_INT = 0xC0C0C0;
	public static final String SILVER_STRING = "Silver";
	public static final Color SILVER = fromValue(SILVER_INT);
	
	//constants
	public static final int SKY_BLUE_INT = 0x87CEEB;
	public static final String SKY_BLUE_STRING = "SkyBlue";
	public static final Color SKY_BLUE = fromValue(SKY_BLUE_INT);
	
	//constants
	public static final int SLATE_BLUE_INT = 0x6A5ACD;
	public static final String SLATE_BLUE_STRING = "SlateBlue";
	public static final Color SLATE_BLUE = fromValue(SLATE_BLUE_INT);
	
	//constants
	public static final int SLATE_GREY_INT = 0x708090;
	public static final String SLATE_GREY_STRING = "SlateGrey";
	public static final Color SLATE_GREY = fromValue(SLATE_GREY_INT);
	
	//constants
	public static final int SNOW_INT = 0xFFFAFA;
	public static final String SNOW_STRING = "Snow";
	public static final Color SNOW = fromValue(SNOW_INT);
	
	//constants
	public static final int SPRING_GREEN_INT = 0x00FF7F;
	public static final String SPRING_GREEN_STRING = "SpringGreen";
	public static final Color SPRING_GREEN = fromValue(SPRING_GREEN_INT);
	
	//constants
	public static final int STEEL_BLUE_INT = 0x4682B4;
	public static final String STEEL_BLUE_STRING = "SteelBlue";
	public static final Color STEEL_BLUE = fromValue(STEEL_BLUE_INT);
	
	//constants
	public static final int TAN_INT = 0xD2B48C;
	public static final String TAN_STRING = "Tan";
	public static final Color TAN = fromValue(TAN_INT);
	
	//constants
	public static final int TEAL_INT = 0x008080;
	public static final String TEAL_STRING = "Teal";
	public static final Color TEAL = fromValue(TEAL_INT);
	
	//constants
	public static final int THISTLE_INT = 0xD8BFD8;
	public static final String THISTLE_STRING = "Thistle";
	public static final Color THISTLE = fromValue(THISTLE_INT);
	
	//constants
	public static final int TOMATO_INT = 0xFF6347;
	public static final String TOMATO_STRING = "Tomato";
	public static final Color TOMATO = fromValue(TOMATO_INT);
	
	//constants
	public static final int TURQUOISE_INT = 0x40E0D0;
	public static final String TURQUOISE_STRING = "Turquoise";
	public static final Color TURQUOISE = fromValue(TURQUOISE_INT);
	
	//constants
	public static final int VIOLET_INT = 0xEE82EE;
	public static final String VIOLET_STRING = "VIOLET";
	public static final Color VIOLET = fromValue(VIOLET_INT);
	
	//constants
	public static final int WHEAT_INT = 0xF5DEB3;
	public static final String WHEAT_STRING = "Wheat";
	public static final Color WHEAT = fromValue(WHEAT_INT);
	
	//constants
	public static final int WHITE_INT = 0xFFFFFF;
	public static final String WHITE_STRING = "White";
	public static final Color WHITE = fromValue(WHITE_INT);
	
	//constants
	public static final int WHITE_SMOKE_INT = 0xF5F5F5;
	public static final String WHITE_SMOKE_STRING = "WhiteSmoke";
	public static final Color WHITE_SMOKE = fromValue(WHITE_SMOKE_INT);
	
	//constants
	public static final int YELLOW_INT = 0xFFFF00;
	public static final String YELLOW_STRING = "Yellow";
	public static final Color YELLOW = fromValue(YELLOW_INT);
	
	//constants
	public static final int YELLOW_GREEN_INT = 0x9ACD32;
	public static final String YELLOW_GREEN_STRING = "YellowGreen";
	public static final Color YELLOW_GREEN = fromValue(YELLOW_GREEN_INT);
	
	//constants
	public static final IContainer<Color> WEB_COLORS;
	public static final IContainer<Pair<String, Color>> WEB_COLOR_NAMES;
	
	//constant
	public static final int DEFAULT_ALPHA_VALUE = 255;
	
	//constants
	public static final long MIN_COLOR_INT = 0;
	public static final long MAX_COLOR_INT = 4_294_967_296L;
	
	//constants
	public static final short MIN_COLOR_COMPONENT = 0;
	public static final short MAX_COLOR_COMPONENT = 255;
	
	//static initializer
	static {
		WEB_COLORS = new ColorConstantExtractor().getColors();
		WEB_COLOR_NAMES = new ColorNameConstantExtractor().getColorNames();
	}
	
	//static method
	/**
	 * @param specification
	 * @return a new {@link Color} from the given specification
	 * @throws InvalidArgumentException if the given specification is not valid.
	 */
	public static Color fromSpecification(final BaseNode specification) {
		return Color.fromString(specification.getOneAttributeHeader());
	}
	
	//static method
	/**
	 * @param string
	 * @return a new {@link Color} from the given string.
	 * @throws UnrepresentingArgumentException if the given string does not represent a {@link Color}.
	 */
	public static Color fromString(final String string) {
		
		final var pair = WEB_COLOR_NAMES.getOptionalRefFirst(p -> p.getRefElement1().equals(string));
		
		//Handles the case that the given string is not a color name.
		if (pair.isEmpty()) {
			
			if (
				(string.length() != 8 || string.length() != 10)
				&& !string.substring(0, 2).equals(StringCatalogue.HEXADECIMAL_PREFIX)) {
				throw new UnrepresentingArgumentException(string, Color.class);
			}
			
			final var redValue = getColorComponentFrom(string.substring(2, 4));
			final var greenValue = getColorComponentFrom(string.substring(4, 6));
			final var blueValue = getColorComponentFrom(string.substring(6, 8));
			
			//Handles the case that the given string does not specify an alpha value.
			if (string.length() == 8) {
				return new Color(redValue, greenValue, blueValue);
			}
			
			//Handles the case that the given string specifies an alpha value.
			final var alphaValue = getColorComponentFrom(string.substring(8, 10));
			return new Color(redValue, greenValue, blueValue, alphaValue);
		}
			
		//Handles the case that the given value is a color name.
		return fromValue(Long.valueOf(string));
	}
	
	//static method
	/**
	 * @param value
	 * @return a new {@link Color} from the given value.
	 * @throws UnrepresentingArgumentException if the given value does not represent a {@link Color}.
	 */
	public static Color fromValue(long value) {
		
		//Asserts that the given value is a true color value.
		Validator.assertThat(value).isBetween(MIN_COLOR_INT, MAX_COLOR_INT);
		
		var alphaValue = DEFAULT_ALPHA_VALUE;
		
		//Handles the case that the given value specifies an alpha value.
		if (value >= 16_777_216) {
			alphaValue = ((int)(value % 256));
			value /= 256;
		}
				
		var blueValue =(int)(value % 256);
		value /= 256;
		
		var greenValue = ((int)(value % 256));
		value /= 256;
				
		var redValue = (int)value;
		
		return new Color(redValue, greenValue, blueValue, alphaValue);
	}
	
	//static method
	/**
	 * @param redValue
	 * @param greenValue
	 * @param blueValue
	 * @return a new {@link Color} with the given redValue, greenValue and blueValue.
	 * @throws ArgumentIsOutOfRangeException if the given blueValue is not a true color component (in [0, 255]).
	 * @throws ArgumentIsOutOfRangeException if the given greenValue is not a true color component (in [0, 255]).
	 * @throws ArgumentIsOutOfRangeException if the given blueValue is not a true color component (in [0, 255]).
	 */
	public static Color withRedValueAndGreenValueAndBlueValue(
		final int redValue,
		final int greenValue,
		final int blueValue
	) {
		return new Color(redValue, greenValue, blueValue);
	}
	
	//static method
	/**
	 * @param redValue
	 * @param greenValue
	 * @param blueValue
	 * @param alphaValue
	 * @return a new {@link Color} with the given redValue, greenValue, blueValue and alphaValue.
	 * @throws ArgumentIsOutOfRangeException if the given blueValue is not a true color component (in [0, 255]).
	 * @throws ArgumentIsOutOfRangeException if the given greenValue is not a true color component (in [0, 255]).
	 * @throws ArgumentIsOutOfRangeException if the given blueValue is not a true color component (in [0, 255]).
	 * @throws ArgumentIsOutOfRangeException if the given alphaValue is not a true color component (in [0, 255]).
	 */
	public static Color withRedValueAndGreenValueAndBlueValueAndAlphaValue(
		final int redValue,
		final int greenValue,
		final int blueValue,
		final int alphaValue
	) {
		return new Color(redValue, greenValue, blueValue, alphaValue);
	}
	
	//method
	/**
	 * @param string
	 * @return the color component the given string represents.
	 * @throws UnrepresentingArgumentException if the given string does not represent a color component.
	 */
	private static int getColorComponentFrom(final String string) {
		
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
					throw new InvalidArgumentException(string);
			}
			
			value += tempValue * base;
			base *= 16;
		}
		
		return value;
	}

	//attributes
	private final short redValue;
	private final short greenValue;
	private final short blueValue;
	private final short alphaValue;
	
	//constructor
	/**
	 * Creates a new {@link Color} with the given redValue, greenValue and blueValue.
	 * 
	 * @param redValue
	 * @param greenValue
	 * @param blueValue
	 * @throws ArgumentIsOutOfRangeException if the given blueValue is not a true color component (in [0, 255]).
	 * @throws ArgumentIsOutOfRangeException if the given greenValue is not a true color component (in [0, 255]).
	 * @throws ArgumentIsOutOfRangeException if the given blueValue is not a true color component (in [0, 255]).
	 * @throws ArgumentIsOutOfRangeException if the given alphaValue is not a true color component (in [0, 255]).
	 */
	private Color(final int redValue, final int greenValue, final int blueValue) {
		this(redValue, greenValue, blueValue, DEFAULT_ALPHA_VALUE);
	}
	
	//constructor
	/**
	 * Creates a new {@link Color} with the given redValue, greenValue, blueValue and alphaValue.
	 * 
	 * @param redValue
	 * @param greenValue
	 * @param blueValue
	 * @param alphaValue
	 * @throws ArgumentIsOutOfRangeException if the given blueValue is not a true color component (in [0, 255]).
	 * @throws ArgumentIsOutOfRangeException if the given greenValue is not a true color component (in [0, 255]).
	 * @throws ArgumentIsOutOfRangeException if the given blueValue is not a true color component (in [0, 255]).
	 * @throws ArgumentIsOutOfRangeException if the given alphaValue is not a true color component (in [0, 255]).
	 */
	private Color(final int redValue, final int greenValue, final int blueValue, final int alphaValue) {
		
		Validator
		.assertThat(redValue)
		.thatIsNamed("red value")
		.isBetween(MIN_COLOR_COMPONENT, MAX_COLOR_COMPONENT);
		
		Validator
		.assertThat(greenValue)
		.thatIsNamed("green value")
		.isBetween(MIN_COLOR_COMPONENT, MAX_COLOR_COMPONENT);
		
		Validator
		.assertThat(blueValue)
		.thatIsNamed("blue value")
		.isBetween(MIN_COLOR_COMPONENT, MAX_COLOR_COMPONENT);
		
		Validator
		.assertThat(blueValue)
		.thatIsNamed("alpha value")
		.isBetween(MIN_COLOR_COMPONENT, MAX_COLOR_COMPONENT);
		
		this.redValue = (short)redValue;
		this.greenValue = (short)greenValue;
		this.blueValue = (short)blueValue;
		this.alphaValue = (short)alphaValue;
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
	 * {@inheritDoc}
	 */
	@Override
	public void fillUpAttributesInto(final LinkedList<Node> list) {
		list.addAtEnd(Node.fromString(getHexadecimalValue()));
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
	 * @return the hexadecimal value of the current {@link Color}.
	 */
	public String getHexadecimalValue() {
		
		var string =
		StringCatalogue.HEXADECIMAL_PREFIX
		+ String.format("%02X", redValue)
		+ String.format("%02X", greenValue)
		+ String.format("%02X", blueValue);
		
		//Handles the case that the current color does not have a full alpha value.
		if (!hasFullAlphaValue()) {
			string += String.format("%02X", alphaValue);
		}
		
		return string;
	}
	
	//method
	/**
	 * @return the hexadecimal value of the current {@link Color} always with alpha value.
	 */
	public String getHexadecimalValueAlwaysWithAlphaValue() {
		return
		StringCatalogue.HEXADECIMAL_PREFIX
		+ String.format("%02X", redValue)
		+ String.format("%02X", greenValue)
		+ String.format("%02X", blueValue)
		+ String.format("%02X", alphaValue);
	}
	
	//method
	/**
	 * @return the hexadecimal value of the current {@link Color} or its color name.
	 */
	public String getHexadecimalValueOrColorName() {
		
		final var pair = WEB_COLOR_NAMES.getRefFirstOrNull(wc -> wc.getRefElement2().equals(this));
		
		//Handles the case that the current Color has a color name.
		if (pair != null) {
			return pair.getRefElement1();
		}
		
		return getHexadecimalValue();
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
	 * @return an integer that represents the current {@link Color} in the schema alpha-red-green-blue.
	 */
	public int toAlphaRedGreenBlueValue() {
		return ((getAlphaValue() << 24) | (getRedValue() << 16) | (getGreenValue() << 8) | getBlueValue());
	}
	
	//method
	/**
	 * @return a new {@link Color} that is like the current {@link Color} with a full alpha value.
	 */
	public Color toColorWithFullAlphaValue() {
		
		//For a better performance, this implementation does not use all comfortable methods.
		return new Color(redValue, greenValue, blueValue);
	}
	
	//method
	/**
	 * @return the integer value of the current {@link Color}.
	 */
	public long toValue() {
		
		//Handles the case that the current Color does not have a full alpha value.
		if (!hasFullAlphaValue()) {
			return
			16_777_216L * getRedValue()
			+ 65536 * getGreenValue()
			+ 256 * getBlueValue()
			+ getAlphaValue();
		}
		
		//Handles the case that the current Color has a full alpha value.
		return
		65536L * getRedValue()
		+ 256 * getGreenValue()
		+ getBlueValue();
	}
}
