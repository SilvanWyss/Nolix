//package declaration
package ch.nolix.coreapi.attributeapi.fluentmutablemandatoryattributeuniversalapi;

//own imports
import ch.nolix.coreapi.attributeapi.mandatoryattributeuniversalapi.Named;

//interface
/**
 * A {@link FluentNameable} is a {@link Named} whose name can be set programmatically.
 * 
 * @author Silvan Wyss
 * @date 2016-01-01
 * @param <FN> is the type of a {@link FluentNameable}.
 */
public interface FluentNameable<FN extends FluentNameable<FN>> extends Named {
	
	//method declaration
	/**
	 * Sets the name of the current {@link FluentNameable}.
	 * 
	 * @param name
	 * @return the current {@link FluentNameable}.
	 * @throws RuntimeException if the given name is null.
	 * @throws RuntimeException if the given name is blank.
	 */
	FN setName(String name);
}
