//package declaration
package ch.nolix.common.attributeAPI;

//interface
/**
 * A {@link Tokenable} is a {@link Tokened} whose token can be set programmatically.
 * 
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 20
 * @param <T> The type of a {@link Tokenable}.
 */
public interface Tokenable<T extends Tokenable<T>> extends Tokened {
	
	//abstract method
	/**
	 * Sets the token of the current {@link Tokenable}.
	 * 
	 * @param token
	 * @return the current {@link Tokenable}.
	 */
	public abstract T setToken(String token);
}
