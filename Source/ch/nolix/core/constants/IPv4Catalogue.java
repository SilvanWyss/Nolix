//package declaration
package ch.nolix.core.constants;

//class
/**
 * Of the {@link IPv4Catalogue} no instance can be created.
 * 
 * @author Silvan Wyss
 * @month 2018-12
 * @lines 20
 */
public final class IPv4Catalogue {
	
	//constant
	public static final String LOOP_BACK_ADDRESS = "127.0.0.1";
	
	//private constructor
	/**
	 * Avoids that an instance of the {@link IPv4Catalogue} can be created.
	 */
	private IPv4Catalogue() {}
}
