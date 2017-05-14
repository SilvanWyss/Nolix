//package declaration
package ch.nolix.core.interfaces;

//interface
/**
 * A tokenable object is a tokened object whose token can be set dynamically.
 * 
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 20
 * @param <T> - The concrete type of a tokenable object.
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
