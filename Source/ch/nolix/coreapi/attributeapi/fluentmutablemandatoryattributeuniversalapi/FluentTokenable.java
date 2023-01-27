//package declaration
package ch.nolix.coreapi.attributeapi.fluentmutablemandatoryattributeuniversalapi;

//own imports
import ch.nolix.coreapi.attributeapi.mandatoryattributeuniversalapi.Tokened;

//interface
/**
 * A {@link FluentTokenable} is a {@link Tokened} whose token can be set programmatically.
 * 
 * @author Silvan Wyss
 * @date 2016-01-01
 * @param <T> is the type of a {@link FluentTokenable}.
 */
public interface FluentTokenable<T extends FluentTokenable<T>> extends Tokened {
	
	//method declaration
	/**
	 * Sets the token of the current {@link FluentTokenable}.
	 * 
	 * @param token
	 * @return the current {@link FluentTokenable}.
	 */
	T setToken(String token);
}
