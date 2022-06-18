//package declaration
package ch.nolix.core.requestuniversalapi;

//own imports
import ch.nolix.core.programatom.marker.AllowDefaultMethodsAsDesignPattern;

//interface
/**
 * A {@link MutabilityRequestable} can be asked if it is mutable.
 * 
 * @author Silvan Wyss
 * @date 2021-03-19
 */
@AllowDefaultMethodsAsDesignPattern
public interface MutabilityRequestable {
	
	//method
	/**
	 * @return true if the current {@link MutabilityRequestable} is not mutable.
	 */
	default boolean isImmutable() {
		return !isMutable();
	}
	
	//method declaration
	/**
	 * @return true if the current {@link MutabilityRequestable} is mutable.
	 */
	boolean isMutable();
}
