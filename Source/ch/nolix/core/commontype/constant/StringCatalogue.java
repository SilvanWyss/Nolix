//package declaration
package ch.nolix.core.commontype.constant;

//class
/**
 * Of the {@link StringCatalogue} an instance cannot be created.
 * 
 * @author Silvan Wyss
 * @date 2016-10-01
 */
public final class StringCatalogue {
	
	//constants
	public static final String DEFAULT_STRING = "Default";
	public static final String DOT = ".";
	public static final String ELLIPSIS = "...";
	public static final String EMPTY_STRING = "";
	public static final String FALSE_HEADER = "false";
	public static final String HEXADECIMAL_PREFIX = "0x";
	public static final String MINUS = "-";
	public static final String NULL_HEADER = "null";
	public static final String SPACE = " ";
	public static final String TABULATOR = "\t";
	public static final String TRUE_HEADER = "true";
	public static final String UNDERSCORE = "_";
	
	//constructor
	/**
	 * Prevents that an instance of the {@link StringCatalogue} can be created.
	 */
	private StringCatalogue() {}
}
