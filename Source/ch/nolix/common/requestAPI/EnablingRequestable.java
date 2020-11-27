//package declaration
package ch.nolix.common.requestAPI;

//interface
/**
 * A {@link EnablingRequestable} can be asked if it is enabled or disabled.
 * 
 * @author Silvan Wyss
 * @month 2020-10
 * @lines 20
 */
public interface EnablingRequestable {
	
	//method
	/**
	 * @return true if the current {@link EnablingRequestable} is disabled.
	 */
	default boolean isDisabled() {
		return !isEnabled();
	}
	
	//method declaration
	/**
	 * @return true if the current {@link EnablingRequestable} is enabled.
	 */
	boolean isEnabled();
}
