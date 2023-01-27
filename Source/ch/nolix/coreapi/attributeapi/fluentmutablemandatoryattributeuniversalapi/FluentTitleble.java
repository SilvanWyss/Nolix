//package declaration
package ch.nolix.coreapi.attributeapi.fluentmutablemandatoryattributeuniversalapi;

//own imports
import ch.nolix.coreapi.attributeapi.mandatoryattributeuniversalapi.Titled;

//interface
/**
 * A {@link FluentTitleble} is a {@link Titled} whose title can be set programmatically.
 * 
 * @author Silvan Wyss
 * @date 2019-07-26
 * @param <T> is the type of a {@link FluentTitleble}.
 */
public interface FluentTitleble<T extends FluentTitleble<T>> extends Titled {
	
	//method declaration
	/**
	 * Sets the title of the current {@link FluentTitleble}.
	 * 
	 * @param title
	 * @return the current {@link FluentTitleble}.
	 */
	T setTitle(String title);
}
