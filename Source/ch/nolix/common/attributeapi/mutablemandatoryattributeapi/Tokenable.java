//package declaration
package ch.nolix.common.attributeapi.mutablemandatoryattributeapi;

//own imports
import ch.nolix.common.attributeapi.mandatoryattributeapi.Tokened;

//interface
/**
 * A {@link Tokenable} is a {@link Tokened} whose token can be set programmatically.
 * 
 * @author Silvan Wyss
 * @date 2016-01-01
 * @lines 20
 * @param <T> is the type of a {@link Tokenable}.
 */
public interface Tokenable<T extends Tokenable<T>> extends Tokened {
	
	//method declaration
	/**
	 * Sets the token of the current {@link Tokenable}.
	 * 
	 * @param token
	 * @return the current {@link Tokenable}.
	 */
	T setToken(String token);
}
