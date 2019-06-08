//package declaration
package ch.nolix.core.attributeAPI;

//interface
/**
 * A {@link Tokened} has a token.
 * 
 * @author Silvan Wyss
 * @month 2016-12
 * @lines 60
 */
public interface Tokened {
	
	//abstract method
	/**
	 * @return the token of the current {@link Tokened}.
	 */
	public abstract String getToken();
	
	//default method
	/**
	 * @return the token of the current {@link Tokened} in brackets.
	 */
	public default String getTokenInBrackets() {
		return ("(" + getToken() + ")");
	}
	
	//default method
	/**
	 * @return the token of the current {@link Tokened} in quotes.
	 */
	public default String getTokenInQuotes() {
		return ("'" + getToken() + "'");
	}
	
	//default method
	/**
	 * @param token
	 * @return true if the current {@link Tokened} has the given token.
	 */
	public default boolean hasToken(final String token) {
		return getToken().equals(token);
	}
	
	//default method
	/**
	 * @param object
	 * @return true if the current {@link Tokened} has the same token as the given object.
	 */
	public default boolean hasSameHeaderAs(final Tokened object) {
		
		//Handles the case that the given object is null.
		if (object == null) {
			return false;
		}
		
		//Handles the case that the given object is not null.
		return getToken().equals(object.getToken());
	}
}
