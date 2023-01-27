//package declaration
package ch.nolix.coreapi.attributeapi.fluentmutableoptionalattributeuniversalapi;

//own imports
import ch.nolix.coreapi.attributeapi.optionalattributeuniversalapi.OptionalTokened;

//interface
/**
 * A {@link FluentOptionalTokenable} is a {@link OptionalTokened} whose token can be set and removed programmatically.
 * 
 * @author Silvan Wyss
 * @date 2016-01-01
 * @param <OT> is the type of a {@link FluentOptionalTokenable}.
 */
public interface FluentOptionalTokenable<OT extends FluentOptionalTokenable<OT>> extends OptionalTokened {
		
	//method declaration
	/**
	 * Removes the token of the current {@link FluentOptionalTokenable}.
	 */
	void removeToken();
	
	//method declaration
	/**
	 * Sets the token of the current {@link FluentOptionalTokenable}.
	 * 
	 * @param token
	 * @return the current {@link FluentOptionalTokenable}.
	 */
	OT setToken(String token);
}
