//package declaration
package ch.nolix.core.attributeuniversalapi.mandatoryattributeuniversalapi;

//interface
/**
 * A {@link Prefixed} has a prefix
 * 
 * @author Silvan Wyss
 * @date 2021-03-26
 */
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
