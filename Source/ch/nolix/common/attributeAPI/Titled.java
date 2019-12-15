//package declaration
package ch.nolix.common.attributeAPI;

//interface
/**
* A {@link Titled} has a title.
* 
* @author Silvan Wyss
* @month 2019-07
* @lines 10
*/
public interface Titled {
	
	//method declaration
	/**
	 * @return the title of the current {@link Titled}.
	 */
	public abstract String getTitle();
	
	//method
	/**
	 * @return the title of the current {@link Titled} in quotes.
	 */
	public default String getTitleInQuotes() {
		return ("'" + getTitle() + "'");
	}
	
	//method
	/**
	 * @param title
	 * @return true if the current {@link Titled} has the given title.
	 */
	public default boolean hasTitle(final String title) {
		return getTitle().equals(title);
	}
	
	//method
	/**
	 * @param object
	 * @return true if the current {@link Titled} has the same title as the given object.
	 */
	public default boolean hasSameTitleAs(final Titled object) {
		
		//Handles the case that the given object is null.
		if (object == null) {
			return false;
		}
		
		//Handles the case that the given object is not null.
		return getTitle().equals(object.getTitle());
	}
}
