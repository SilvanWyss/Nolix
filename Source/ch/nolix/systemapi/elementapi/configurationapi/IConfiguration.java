//package declaration
package ch.nolix.systemapi.elementapi.configurationapi;

//interface
/**
 * @author Silvan Wyss
 * @date 2022-05-29
 */
public interface IConfiguration {
	
	//method
	/**
	 * Lets the current {@link IConfiguration} configure the given element.
	 * 
	 * @param element
	 */
	void configure(IConfigurableElement<?> element);
}
