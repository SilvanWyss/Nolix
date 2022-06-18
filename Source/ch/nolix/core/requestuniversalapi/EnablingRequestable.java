//package declaration
package ch.nolix.core.requestuniversalapi;

//own imports
import ch.nolix.core.programatom.marker.AllowDefaultMethodsAsDesignPattern;

//interface
/**
 * A {@link EnablingRequestable} can be asked if it is enabled or disabled.
 * 
 * @author Silvan Wyss
 * @date 2020-10-02
 */
@AllowDefaultMethodsAsDesignPattern
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
