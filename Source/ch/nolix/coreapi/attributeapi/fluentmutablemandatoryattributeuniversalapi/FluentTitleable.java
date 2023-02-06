//package declaration
package ch.nolix.coreapi.attributeapi.fluentmutablemandatoryattributeuniversalapi;

//own imports
import ch.nolix.coreapi.attributeapi.mandatoryattributeuniversalapi.Titled;

//interface
/**
 * A {@link FluentTitleable} is a {@link Titled} whose title can be set programmatically.
 * 
 * @author Silvan Wyss
 * @date 2019-07-26
 * @param <T> is the type of a {@link FluentTitleable}.
 */
public interface FluentTitleable<T extends FluentTitleable<T>> extends Titled {
	
	//method declaration
	/**
	 * Sets the title of the current {@link FluentTitleable}.
	 * 
	 * @param title
	 * @return the current {@link FluentTitleable}.
	 */
	T setTitle(String title);
}
