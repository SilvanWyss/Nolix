//package declaration
package ch.nolix.common.attributeapi;

//interface
/**
 * A {@link Identified} has an id.
 * 
 * @author Silvan Wyss
 * @month 2018-04
 * @lines 50
 */
public interface Identified {
	
	//method declaration
	/**
	 * @return the id of the current {@link Identified}.
	 */
	long getId();
	
	//method
	/**
	 * @return the id of the current {@link Identified} as {@link String}.
	 */
	default String getIdAsString() {
		return String.valueOf(getId());
	}
	
	//method
	/**
	 * @return the id of the current {@link Identified} in quotes.
	 */
	default String getIdInQuotes() {
		return ("'" + getIdAsString() + "'");
	}
	
	//method
	/**
	 * @param id
	 * @return true if the current {@link Identified} has the given id.
	 */
	default boolean hasId(final long id) {
		return (getId() == id);
	}
	
	//method
	/**
	 * @param object
	 * @return true if the current {@link Identified} has the same id as the given object.
	 */
	default boolean hasSameIdAs(final Identified object) {
		
		//For a better performance, this implementation does not use all comfortable methods.
		return (object != null && getId() == object.getId());
	}
}
