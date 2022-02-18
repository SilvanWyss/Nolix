//package declaration
package ch.nolix.core.attributeapi.mutablemandatoryattributeapi;

import ch.nolix.core.attributeapi.mandatoryattributeapi.Valued;

//interface
/**
 * A {@link Valueable} is a {@link Valued} whose value can be set programmatically.
 * 
 * @author Silvan Wyss
 * @date 2018-09-06
 * @param <VA> is the type of a {@link Valueable}.
 * @param <V> is the type of the value of a {@link Valueable}.
 */
public interface Valueable<VA extends Valueable<VA, V>, V> extends Valued<VA, V> {
	
	//method declaration
	/**
	 * Sets the value of the current {@link Valueable}.
	 * 
	 * @param value
	 * @return the current {@link Valueable}.
	 */
	VA setValue(V value);
}
