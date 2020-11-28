//package declaration
package ch.nolix.common.mutableattributeapi;

import ch.nolix.common.attributeapi.Indexed;

//interface
/**
 * A {@link Indexable} is a {@link Indexed} whose index can be set programmatically.
 * 
 * @author Silvan Wyss
 * @month 2020-03
 * @lines 20
 * @param <I> The type of a {@link Indexable}.
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
