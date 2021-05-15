//package declaration
package ch.nolix.common.attributeapi.mutablemandatoryattributeapi;

import ch.nolix.common.attributeapi.mandatoryattributeapi.Indexed;

//interface
/**
 * A {@link Indexable} is a {@link Indexed} whose index can be set programmatically.
 * 
 * @author Silvan Wyss
 * @date 2020-03-29
 * @lines 20
 * @param <I> is the type of a {@link Indexable}.
 */
public interface Indexable<I> extends Indexed {
	
	//method declaration
	/**
	 * Sets the index of the current {@link Indexable}.
	 * 
	 * @param index
	 * @return the current {@link Indexable}.
	 */
	I setIndex(int index);
}
