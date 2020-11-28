//package declaration
package ch.nolix.common.attributeapi;

//interface
/**
 * A {@link Tokened} has a token.
 * 
 * @author Silvan Wyss
 * @month 2016-12
 * @lines 50
 */
public interface Tokened {
	
	//method declaration
	/**
	 * @return the token of the current {@link Tokened}.
	 */
	String getToken();
	
	//method
	/**
	 * @return the token of the current {@link Tokened} in quotes.
	 */
	default String getTokenInQuotes() {
		return ("'" + getToken() + "'");
	}
	
	//method
	/**
	 * @param token
	 * @return true if the current {@link Tokened} has the given token.
	 */
	default boolean hasToken(final String token) {
		return getToken().equals(token);
	}
	
	//method
	/**
	 * @param object
	 * @return true if the current {@link Tokened} has the same token as the given object.
	 */
	default boolean hasSameTokenAs(final Tokened object) {
		
		//Handles the case that the given object is null.
		if (object == null) {
			return false;
		}
		
		//Handles the case that the given object is not null.
		return getToken().equals(object.getToken());
	}
}
