//package declaration
package ch.nolix.core.attributeuniversalapi.mutablemandatoryattributeuniversalapi;

import ch.nolix.core.attributeuniversalapi.mandatoryattributeuniversalapi.Identified;

//interface
/**
 * A {@link Identifiable} is a {@link Identified} whose id can be set programmatically.
 * 
 * @author Silvan Wyss
 * @date 2020-03-29
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
