//package declaration
package ch.nolix.core.constants;

//own import
import ch.nolix.core.invalidArgumentException.UninstantiableClassException;

//class
/**
 * Of the {@link StringCatalogue} an instance cannot be created.
 * 
 * @author Silvan Wyss
 * @month 2016-09
 * @lines 30
 */
public final class StringCatalogue {
	
	//constants
	public static final String EMPTY_STRING = "";
	public static final String DEFAULT_STRING = "Default";
	public static final String ELLIPSIS = "...";
	public static final String HEXADECIMAL_PREFIX = "0x";
	public static final String NULL_NAME = "null";
	
	//private constructor
	/**
	 * Avoids that an instance of the {@link StringCatalogue} can be created.
	 * 
	 * @throws UninstantiableClassException
	 */
	private StringCatalogue() {
		throw new UninstantiableClassException(StringCatalogue.class);
	}
}
