//package declaration
package ch.nolix.core.attributeAPI;

//interface
/**
* A {@link Titleble} is a {@link Titled} whose title can be set programmatically.
* 
* @author Silvan Wyss
* @month 2019-07
* @lines 20
* @param <T> The type of a {@link Titleble}.
*/
public interface Titleble<T extends Titleble<T>> extends Titled {
	
	//abstract method
	/**
	 * Sets the title of the current {@link Titleble}.
	 * 
	 * @param title
	 * @return the current {@link Titleble}.
	 */
	public abstract T setTitle(String title);
}
