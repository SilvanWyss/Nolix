//package declaration
package ch.nolix.common.mutableattributeapi;

//own imports
import ch.nolix.common.attributeapi.Identified;

//interface
/**
 * A {@link Identifiable} is a {@link Identified} whose id can be set programmatically.
 * 
 * @author Silvan Wyss
 * @month 2020-03
 * @lines 20
 * @param <I> The type of a {@link Identifiable}.
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
