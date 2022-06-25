//package declaration
package ch.nolix.core.attributeuniversalapi.mutableoptionalattributeuniversalapi;

import ch.nolix.coreapi.attributeuniversalapi.optionalattributeuniversalapi.OptionalTokened;

//interface
/**
 * A {@link OptionalTokenable} is a {@link OptionalTokened} whose token can be set and removed programmatically.
 * 
 * @author Silvan Wyss
 * @date 2016-01-01
 * @param <OT> is the type of a {@link OptionalTokenable}.
 */
public interface OptionalTokenable<OT extends OptionalTokenable<OT>> extends OptionalTokened {
		
	//method declaration
	/**
	 * Removes the token of the current {@link OptionalTokenable}.
	 */
	void removeToken();
	
	//method declaration
	/**
	 * Sets the token of the current {@link OptionalTokenable}.
	 * 
	 * @param token
	 * @return the current {@link OptionalTokenable}.
	 */
	OT setToken(String token);
}
