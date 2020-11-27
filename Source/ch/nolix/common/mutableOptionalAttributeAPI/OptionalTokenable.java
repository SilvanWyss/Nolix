//package declaration
package ch.nolix.common.mutableOptionalAttributeAPI;

//own import
import ch.nolix.common.optionalAttributeAPI.OptionalTokened;

//interface
/**
 * A {@link OptionalTokenable} is a {@link OptionalTokened} whose token can be set and removed programmatically.
 * 
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 30
 * @param <OT> The type of a {@link OptionalTokenable}.
 */
public interface OptionalTokenable<OT extends OptionalTokenable<OT>> extends OptionalTokened {
		
	//method declaration
	/**
	 * Removes the token of the current {@link OptionalTokenable}.
	 * 
	 * @return the current {@link OptionalTokenable}.
	 */
	OT removeToken();
	
	//method declaration
	/**
	 * Sets the token of the current {@link OptionalTokenable}.
	 * 
	 * @param token
	 * @return the current {@link OptionalTokenable}.
	 */
	OT setToken(String token);
}
