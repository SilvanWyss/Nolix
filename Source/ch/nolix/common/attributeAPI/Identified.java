//package declaration
package ch.nolix.common.attributeAPI;

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
	public abstract long getId();
	
	//method
	/**
	 * @return the id of the current {@link Identified} as {@link String}.
	 */
	public default String getIdAsString() {
		return String.valueOf(getId());
	}
	
	//method
	/**
	 * @return the id of the current {@link Identified} in quotes.
	 */
	public default String getIdInQuotes() {
		return ("'" + getIdAsString() + "'");
	}
	
	//method
	/**
	 * @param id
	 * @return true if the current {@link Identified} has the given id.
	 */
	public default boolean hasId(final long id) {
		return (getId() == id);
	}
	
	//method
	/**
	 * @param object
	 * @return true if the current {@link Identified} has the given id as the given object.
	 */
	public default boolean hasSameIdAs(final Identified object) {
		
		//For a better performance, this implementation does not use all comfortable methods.
		return (object != null && getId() == object.getId());
	}
}
