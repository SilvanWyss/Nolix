//package declaration
package ch.nolix.coreapi.attributeapi.multiattributeapi;

//own imports
import ch.nolix.coreapi.containerapi.baseapi.IContainer;

//interface
/**
* A {@link IMultiValueHolder} can contain several values.
* 
* @author Silvan Wyss
* @date 2023-08-25
* @param <V> is the type of the values of a {@link IMultiValueHolder}.
*/
public interface IMultiValueHolder<V> {
	
	//method declaration
	/**
	 * @return the values of the current {@link IMultiValueHolder}.
	 */
	IContainer<V> getStoredValues();
}
