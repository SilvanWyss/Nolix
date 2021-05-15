//package declaration
package ch.nolix.common.attributeapi.mutablemandatoryattributeapi;

import ch.nolix.common.attributeapi.mandatoryattributeapi.Identified;

//interface
/**
 * A {@link Identifiable} is a {@link Identified} whose id can be set programmatically.
 * 
 * @author Silvan Wyss
 * @date 2020-03-29
 * @lines 20
 * @param <I> is the type of a {@link Identifiable}.
 */
public interface Identifiable<I> extends Identified {
	
	//method declaration
	/**
	 * Sets the id of the current {@link Identifiable}.
	 * 
	 * @param id
	 * @return the current {@link Identifiable}.
	 */
	I setId(long id);
}
