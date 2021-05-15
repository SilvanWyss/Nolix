//package declaration
package ch.nolix.common.attributeapi.mandatoryattributeapi;

//interface
/**
* A {@link Titled} has a title.
* 
* @author Silvan Wyss
* @date 2019-07-26
* @lines 10
*/
public interface Titled {
	
	//method declaration
	/**
	 * @return the title of the current {@link Titled}.
	 */
	String getTitle();
	
	//method
	/**
	 * @return the title of the current {@link Titled} in quotes.
	 */
	default String getTitleInQuotes() {
		return ("'" + getTitle() + "'");
	}
	
	//method
	/**
	 * @param title
	 * @return true if the current {@link Titled} has the given title.
	 */
	default boolean hasTitle(final String title) {
		return getTitle().equals(title);
	}
	
	//method
	/**
	 * @param object
	 * @return true if the current {@link Titled} has the same title as the given object.
	 */
	default boolean hasSameTitleAs(final Titled object) {
		
		//Handles the case that the given object is null.
		if (object == null) {
			return false;
		}
		
		//Handles the case that the given object is not null.
		return getTitle().equals(object.getTitle());
	}
}
