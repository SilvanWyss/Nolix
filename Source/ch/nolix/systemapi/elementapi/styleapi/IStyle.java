//package declaration
package ch.nolix.systemapi.elementapi.styleapi;

//own imports
import ch.nolix.systemapi.elementapi.mainuniversalapi.Specified;

//interface
/**
 * @author Silvan Wyss
 * @date 2022-05-29
 */
public interface IStyle extends Specified {
	
	//method
	/**
	 * Lets the current {@link IStyle} style the given element.
	 * 
	 * @param element
	 */
	void styleElement(IStylableElement<?> element);
}
