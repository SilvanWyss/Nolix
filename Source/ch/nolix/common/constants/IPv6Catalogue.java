//package declaration
package ch.nolix.common.constants;

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
	
	//visibility-reducing constructor
	/**
	 * Avoids that an instance of the {@link IPv6Catalogue} can be created.
	 */
	private IPv6Catalogue() {}
}
