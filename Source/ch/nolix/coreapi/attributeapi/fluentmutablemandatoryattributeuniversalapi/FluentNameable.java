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
 * @param <N> is the type of a {@link FluentNameable}.
 */
public interface FluentNameable<N extends FluentNameable<N>> extends Named {
	
	//method declaration
	/**
	 * Sets the name of the current {@link FluentNameable}.
	 * 
	 * @param name
	 * @return the current {@link FluentNameable}.
	 */
	N setName(String name);
}
