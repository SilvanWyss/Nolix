//package declaration
package ch.nolix.core.attributeuniversalapi.mutableoptionalattributeuniversalapi;

//own imports
import ch.nolix.coreapi.attributeuniversalapi.optionalattributeuniversalapi.OptionalHeadered;

//interface
/**
 * A {@link OptionalHeaderable} is a {@link OptionalHeadered} whose header can be set and removed programmatically.
 * 
 * @author Silvan Wyss
 * @date 2019-02-24
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
	 * @throws RuntimeException if the given header is null.
	 * @throws RuntimeException if the given header is blank.
	 */
	OH setHeader(String header);
}
