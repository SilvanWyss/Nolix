//package declaration
package ch.nolix.coreapi.attributeapi.fluentmutablemandatoryattributeuniversalapi;

//own imports
import ch.nolix.coreapi.attributeapi.mandatoryattributeuniversalapi.Indexed;

//interface
/**
 * A {@link FluentIndexable} is a {@link Indexed} whose index can be set programmatically.
 * 
 * @author Silvan Wyss
 * @date 2020-03-29
 * @param <I> is the type of a {@link FluentIndexable}.
 */
public interface FluentIndexable<I> extends Indexed {
	
	//method declaration
	/**
	 * Sets the index of the current {@link FluentIndexable}.
	 * 
	 * @param index
	 * @return the current {@link FluentIndexable}.
	 */
	I setIndex(int index);
}
