//package declaration
package ch.nolix.coreapi.attributeapi.mutablemandatoryattributeuniversalapi;

//own imports
import ch.nolix.coreapi.attributeapi.mandatoryattributeuniversalapi.Tokened;

//interface
/**
 * A {@link Tokenable} is a {@link Tokened} whose token can be set programmatically.
 * 
 * @author Silvan Wyss
 * @date 2023-02-09
 */
public interface Tokenable extends Tokened {
	
	//method declaration
	/**
	 * Sets the token of the current {@link Tokenable}.
	 * 
	 * @param token
	 * @throws RuntimeException if the given token is null.
	 * @throws RuntimeException if the given token is blank.
	 */
	void setToken(String token);
}
