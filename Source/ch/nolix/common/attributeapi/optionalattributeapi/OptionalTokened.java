//package declaration
package ch.nolix.common.attributeapi.optionalattributeapi;

//interface
/**
 * A {@link OptionalTokened} can have a token
 * 
 * @author Silvan Wyss
 * @date 2020-03-29
 * @lines 60
 */
public interface OptionalTokened {
	
	//method declaration
	/**
	 * @return the token of the current {@link OptionalTokened}.
	 */
	String getToken();
	
	//method declaration
	/**
	 * @return true if the current {@link OptionalTokened} has a token.
	 */
	boolean hasToken();
	
	//method
	/**
	 * @param object
	 * @return true if current {@link OptionalTokened} has the same token as the given object.
	 */
	default boolean hasSameTokenAs(final OptionalTokened object) {
		
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
	default boolean hasToken(final String token) {
		
		//Handles the case that the current OptionalTokened does not have a token.
		if (!hasToken()) {
			return false;
		}
		
		//Handles the case that the current OptionalTokened has a token.
		return getToken().equals(token);
	}
}
