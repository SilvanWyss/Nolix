//package declaration
package ch.nolix.core.interfaces;

//interface
/**
 * An optional tokenable object is a tokenable object whose token can be removed dynamically.
 * 
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 30
 * @param <OT> - The concrete type of an optional tokenable object.
 */
public interface OptionalTokenable<OT extends OptionalTokenable<OT>>
extends Tokenable<OT> {
	
	//abstract method
	/**
	 * @return true if this optional tokenable object has a token.
	 */
	public abstract boolean hasToken();
	
	//default method
	/**
	 * @param token
	 * @return true if this optional tokenable object has the given token.
	 */
	public default boolean hasToken(final String token) {
		
		//Handles the case if this optional tokenable object has no token.
		if (!hasToken()) {
			return false;
		}
		
		//Handles the case if this optional tokenable object has a token.
		return getToken().equals(token);
	}
	
	//abstract method
	/**
	 * Removes the token of this optional tokenable object.
	 * 
	 * @return this optional tokenable object.
	 */
	public abstract OT removeToken();
}
