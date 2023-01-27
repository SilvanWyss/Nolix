//package declaration
package ch.nolix.coreapi.attributeapi.fluentmutableoptionalattributeuniversalapi;

//own imports
import ch.nolix.coreapi.attributeapi.optionalattributeuniversalapi.OptionalHeadered;

//interface
/**
 * A {@link FluentOptionalHeaderable} is a {@link OptionalHeadered} whose header can be set and removed programmatically.
 * 
 * @author Silvan Wyss
 * @date 2019-02-24
 * @param <OH> is the type of a {@link FluentOptionalHeaderable}.
 */
public interface FluentOptionalHeaderable<OH extends FluentOptionalHeaderable<OH>> extends OptionalHeadered {
	
	//method declaration
	/**
	 * Removes the header of current {@link FluentOptionalHeaderable}.
	 */
	void removeHeader();
	
	//method declaration
	/**
	 * Sets the header of the current {@link FluentOptionalHeaderable}.
	 * 
	 * @param header
	 * @return the current {@link FluentOptionalHeaderable}.
	 * @throws RuntimeException if the given header is null.
	 * @throws RuntimeException if the given header is blank.
	 */
	OH setHeader(String header);
}
