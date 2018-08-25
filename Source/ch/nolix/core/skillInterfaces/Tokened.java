//package declaration
package ch.nolix.core.skillInterfaces;

//interface
/**
 * A tokened object has a token.
 * 
 * @author Silvan Wyss
 * @month 2016-12
 * @lines 20
 */
public interface Tokened {

	//abstract method
	/**
	 * @return the token of this tokened object.
	 */
	public abstract String getToken();
	
	//default method
	/**
	 * @param token
	 * @return true if this tokened object has the given token.
	 */
	public default boolean hasToken(final String token) {
		return getToken().equals(token);
	}
}
