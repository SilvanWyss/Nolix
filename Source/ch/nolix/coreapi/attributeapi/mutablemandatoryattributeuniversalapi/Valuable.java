//package declaration
package ch.nolix.coreapi.attributeapi.mutablemandatoryattributeuniversalapi;

//own imports
import ch.nolix.coreapi.attributeapi.mandatoryattributeuniversalapi.Valued;

//interface
/**
* A {@link Valuable} is a {@link Valued} whose value can be set and removed programmatically.
* 
* @author Silvan Wyss
* @date 2023-02-07
* @param <V> is the value of a {@link Valuable}.
*/
public interface Valuable<V> extends Valued<V> {
	
	//method declaration
	/**
	 * Removes the value of the current {@link Valuable}.
	 */
	void removeValue();
	
	//method declaration
	/**
	 * Sets the value of the current {@link Valuable}.
	 * 
	 * @param value
	 * @throws RuntimeException if the given value is null.
	 */
	void setValue(V value);
}
