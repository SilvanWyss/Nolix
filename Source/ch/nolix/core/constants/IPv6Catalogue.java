//package declaration
package ch.nolix.core.constants;

//class
/**
 * Of the {@link IPv6Catalogue} no instance can be created.
 * 
 * @author Silvan Wyss
 * @month 2017-02
 * @lines 20
 */
public final class IPv6Catalogue {
	
	//loop back address
	public static final String LOOP_BACK_ADDRESS = "::1";

	//private constructor
	/**
	 * Avoids that an instance of the {@link IPv6Catalogue} can be created.
	 */
	private IPv6Catalogue() {}
}
