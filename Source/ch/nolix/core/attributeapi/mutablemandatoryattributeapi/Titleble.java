//package declaration
package ch.nolix.core.attributeapi.mutablemandatoryattributeapi;

//own imports
import ch.nolix.core.attributeapi.mandatoryattributeapi.Titled;

//interface
/**
 * A {@link Titleble} is a {@link Titled} whose title can be set programmatically.
 * 
 * @author Silvan Wyss
 * @date 2019-07-26
 * @param <T> is the type of a {@link Titleble}.
 */
public interface Titleble<T extends Titleble<T>> extends Titled {
	
	//method declaration
	/**
	 * Sets the title of the current {@link Titleble}.
	 * 
	 * @param title
	 * @return the current {@link Titleble}.
	 */
	T setTitle(String title);
}
