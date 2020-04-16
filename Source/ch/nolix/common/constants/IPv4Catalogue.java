//package declaration
package ch.nolix.common.constants;

//class
/**
 * Of the {@link IPv4Catalogue} an instance cannot be created.
 * 
 * @author Silvan Wyss
 * @month 2018-12
 * @lines 20
 */
public final class IPv4Catalogue {
	
	//constant
	public static final String LOOP_BACK_ADDRESS = "127.0.0.1";
	
	//visibility-reducing constructor
	/**
	 * Avoids that an instance of the {@link IPv4Catalogue} can be created.
	 */
	private IPv4Catalogue() {}
}
