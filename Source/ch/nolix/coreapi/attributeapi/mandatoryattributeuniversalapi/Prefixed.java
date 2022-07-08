//package declaration
package ch.nolix.coreapi.attributeapi.mandatoryattributeuniversalapi;

//own imports
import ch.nolix.coreapi.markerapi.AllowDefaultMethodsAsDesignPattern;

//interface
/**
 * A {@link Prefixed} has a prefix
 * 
 * @author Silvan Wyss
 * @date 2021-03-26
 */
@AllowDefaultMethodsAsDesignPattern
public interface Prefixed {
	
	//method declaration
	/**
	 * @return the prefix of the current {@link Prefixed}.
	 */
	String getPrefix();
	
	//method
	/**
	 * @param prefix
	 * @return true if the current {@link Prefixed} has the given prefix.
	 */
	default boolean hasPrefix(final String prefix) {
		return getPrefix().equals(prefix);
	}
}
