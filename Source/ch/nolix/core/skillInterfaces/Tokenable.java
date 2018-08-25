//package declaration
package ch.nolix.core.skillInterfaces;

//interface
/**
 * A tokenable object is a tokened object
 * whose token can be set programmatically.
 * 
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 20
 * @param <T> The type of a tokenable object.
 */
public interface Tokenable<T extends Tokenable<T>>
extends Tokened {
	
	//abstract method
	/**
	 * Sets the token of this tokenable object.
	 * 
	 * @param token
	 * @return this tokenable object.
	 */
	public abstract T setToken(String token);
}
