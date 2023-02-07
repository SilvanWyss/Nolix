//package declaration
package ch.nolix.coreapi.attributeapi.fluentmutablemandatoryattributeuniversalapi;

//own imports
import ch.nolix.coreapi.attributeapi.mandatoryattributeuniversalapi.Identified;

//interface
/**
 * A {@link FluentIdentifiable} is a {@link Identified} whose id can be set programmatically.
 * 
 * @author Silvan Wyss
 * @date 2020-03-29
 * @param <FI> is the type of a {@link FluentIdentifiable}.
 */
public interface FluentIdentifiable<FI> extends Identified {
	
	//method declaration
	/**
	 * Sets the id of the current {@link FluentIdentifiable}.
	 * 
	 * @param id
	 * @return the current {@link FluentIdentifiable}.
	 * @throws RuntimeException if the given id is null.
	 * @throws RuntimeException if the given id is blank.
	 */
	FI setId(String id);
}
