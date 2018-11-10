//package declaration
package ch.nolix.core.skillInterfaces;

//interface
/**
 * A {@link Identified} has an id.
 * 
 * @author Silvan Wyss
 * @month 2018-04
 * @lines 30
 */
public interface Identified {
	
	//abstract method
	/**
	 * @return the id of the current {@link Identified}.
	 */
	public abstract int getId();
	
	//default method
	/**
	 * @param object
	 * @return true if the current {@link Identified}
	 * has the same id as the given object.
	 */
	public default boolean hasId(final Identified object) {
		return hasId(object.getId());
	}
	
	//default method
	/**
	 * @return true if the current {@link Identified}
	 * has the given id.
	 */
	public default boolean hasId(final long id) {
		return (getId() == id);
	}
}
