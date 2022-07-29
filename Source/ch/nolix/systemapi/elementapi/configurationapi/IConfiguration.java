//package declaration
package ch.nolix.systemapi.elementapi.configurationapi;

//own imports
import ch.nolix.systemapi.elementapi.mainuniversalapi.Specified;

//interface
/**
 * @author Silvan Wyss
 * @date 2022-05-29
 */
public interface IConfiguration extends Specified {
	
	//method
	/**
	 * Lets the current {@link IConfiguration} configure the given element.
	 * 
	 * @param element
	 */
	void configure(IStylableElement<?> element);
}
