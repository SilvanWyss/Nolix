//package declaration
package ch.nolix.core.attributeAPI;

//interface
/**
 * A {@link OptionalTokenable} is a {@link Tokenable} whose token can be removed programmatically.
 * 
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 30
 * @param <OT> The type of a {@link OptionalTokenable}.
 */
public interface OptionalTokenable<OT extends OptionalTokenable<OT>> extends Tokenable<OT> {
	
	//abstract method
	/**
	 * @return true if the current {@link OptionalTokenable} has a token.
	 */
	public abstract boolean hasToken();
	
	//default method
	/**
	 * @param object
	 * @return true if current {@link OptionalTokenable} has the same token as the given object.
	 */
	public default boolean hasSameTokenAs(final OptionalTokenable<?> object) {
		
		//Handles the case that the given object is null.
		if (object == null) {
			return false;
		}
		
		//Handles the case that the given object does not have a token.
		if (!object.hasToken()) {
			return false;
		}
		
		//Calls other method.
		return hasToken(object.getToken());
	}
	
	//default method
	/**
	 * @param token
	 * @return true if the current {@link OptionalTokenable} has the given token.
	 */
	@Override
	public default boolean hasToken(final String token) {
		
		//Handles the case that the current OptionalTokenable does not have a token.
		if (!hasToken()) {
			return false;
		}
		
		//Handles the case that the current OptionalTokenable has a token.
		return getToken().equals(token);
	}
	
	//abstract method
	/**
	 * Removes the token of the current {@link OptionalTokenable}.
	 * 
	 * @return the current {@link OptionalTokenable}.
	 */
	public abstract OT removeToken();
}
