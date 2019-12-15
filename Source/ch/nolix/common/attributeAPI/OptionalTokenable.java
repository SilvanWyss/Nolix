//package declaration
package ch.nolix.common.attributeAPI;

//interface
/**
 * A {@link OptionalTokenable} can have a token that can be set and removed programmatically.
 * 
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 80
 * @param <OT> The type of a {@link OptionalTokenable}.
 */
public interface OptionalTokenable<OT extends OptionalTokenable<OT>> {
	
	//method declaration
	/**
	 * @return the token of the current {@link OptionalTokenable}.
	 * @throws Exception if the current {@link OptionalTokenable} does not have a token.
	 */
	public abstract String getToken();
	
	//method declaration
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
	public default boolean hasToken(final String token) {
		
		//Handles the case that the current OptionalTokenable does not have a token.
		if (!hasToken()) {
			return false;
		}
		
		//Handles the case that the current OptionalTokenable has a token.
		return getToken().equals(token);
	}
	
	//method declaration
	/**
	 * Removes the token of the current {@link OptionalTokenable}.
	 * 
	 * @return the current {@link OptionalTokenable}.
	 */
	public abstract OT removeToken();
	
	//method declaration
	/**
	 * Sets the token of the current {@link OptionalTokenable}.
	 * 
	 * @param token
	 * @return the current {@link OptionalTokenable}.
	 */
	public abstract OT setToken(String token);
}
