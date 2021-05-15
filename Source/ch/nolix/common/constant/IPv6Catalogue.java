//package declaration
package ch.nolix.common.constant;

//class
/**
 * Of the {@link IPv6Catalogue} an instance cannot be created.
 * 
 * @author Silvan Wyss
 * @date 2017-03-05
 * @lines 20
 */
public final class IPv6Catalogue {
	
	//constant
	public static final String LOOP_BACK_ADDRESS = "::1";
	
	//constructor
	/**
	 * Prevents that an instance of the {@link IPv6Catalogue} can be created.
	 */
	private IPv6Catalogue() {}
}
