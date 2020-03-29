//package declaration
package ch.nolix.common.optionalAttributeAPI;

//interface
/**
 * A {@link OptionalTokened} can have a token
 * 
 * @author Silvan Wyss
 * @month 2020-03
 * @lines 60
 */
public interface OptionalTokened {
	
	//method declaration
	/**
	 * @return the token of the current {@link OptionalTokened}.
	 * @throws Exception if the current {@link OptionalTokened} does not have a token.
	 */
	public abstract String getToken();
	
	//method declaration
	/**
	 * @return true if the current {@link OptionalTokened} has a token.
	 */
	public abstract boolean hasToken();
	
	//method
	/**
	 * @param object
	 * @return true if current {@link OptionalTokened} has the same token as the given object.
	 */
	public default boolean hasSameTokenAs(final OptionalTokened object) {
		
		//Handles the case that the given object is null.
		if (object == null) {
			return false;
		}
		
		//Handles the case that the given object does not have a token.
		if (!object.hasToken()) {
			return false;
		}
		
		//Handles the case that the given object has a token.
		return hasToken(object.getToken());
	}
	
	//method
	/**
	 * @param token
	 * @return true if the current {@link OptionalTokened} has the given token.
	 */
	public default boolean hasToken(final String token) {
		
		//Handles the case that the current OptionalTokened does not have a token.
		if (!hasToken()) {
			return false;
		}
		
		//Handles the case that the current OptionalTokened has a token.
		return getToken().equals(token);
	}
}
