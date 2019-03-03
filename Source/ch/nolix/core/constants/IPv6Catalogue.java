//package declaration
package ch.nolix.core.constants;

//own import
import ch.nolix.core.invalidArgumentException.UninstantiableClassException;

//class
/**
 * Of the {@link IPv6Catalogue} an instance cannot be created.
 * 
 * @author Silvan Wyss
 * @month 2017-02
 * @lines 20
 */
public final class IPv6Catalogue {
	
	//constant
	public static final String LOOP_BACK_ADDRESS = "::1";
	
	//private constructor
	/**
	 * Avoids that an instance of the {@link IPv6Catalogue} can be created.
	 * 
	 * @throws UninstantiableClassException
	 */
	private IPv6Catalogue() {
		throw new UninstantiableClassException(IPv6Catalogue.class);
	}
}
