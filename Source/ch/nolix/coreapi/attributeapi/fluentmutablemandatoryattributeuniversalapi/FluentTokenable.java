//package declaration
package ch.nolix.coreapi.attributeapi.fluentmutablemandatoryattributeuniversalapi;

//own imports
import ch.nolix.coreapi.attributeapi.mandatoryattributeuniversalapi.Tokened;

//interface
/**
 * A {@link FluentTokenable} is a {@link Tokened} whose token can be set programmatically.
 * 
 * @author Silvan Wyss
 * @date 2023-02-06
 * @param <FT> is the type of a {@link FluentTokenable}.
 */
public interface FluentTokenable<FT extends FluentTokenable<FT>> extends Tokened {
	
	//method declaration
	/**
	 * Sets the token of the current {@link FluentTokenable}.
	 * 
	 * @param token
	 * @return the current {@link FluentNamable}.
	 * @throws RuntimeException if the given token is null or blank.
	 */
	FT setToken(String token);
}
