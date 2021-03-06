//package declaration
package ch.nolix.common.attributeapi.mutableoptionalattributeapi;

import ch.nolix.common.attributeapi.optionalattributeapi.OptionalHeadered;

//interface
/**
 * A {@link OptionalHeaderable} is a {@link OptionalHeadered} whose header can be set and removed programmatically.
 * 
 * @author Silvan Wyss
 * @month 2019-02
 * @lines 30
 * @param <OH> is the type of a {@link OptionalHeaderable}.
 */
public interface OptionalHeaderable<OH extends OptionalHeaderable<OH>> extends OptionalHeadered {
	
	//method declaration
	/**
	 * Removes the header of current {@link OptionalHeaderable}.
	 * 
	 * @return the current {@link OptionalHeaderable}.
	 */
	OH removeHeader();
	
	//method declaration
	/**
	 * Sets the header of the current {@link OptionalHeaderable}.
	 * 
	 * @param header
	 * @return the current {@link OptionalHeaderable}.
	 */
	OH setHeader(String header);
}
