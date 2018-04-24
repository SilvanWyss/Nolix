//package declaration
package ch.nolix.core.interfaces;

//interface
/**
 * A {@link Identifiable} has an id.
 * 
 * @author Silvan Wyss
 * @month 2018-04
 * @lines 30
 */
public interface Identifiable {

	//abstract method
	/**
	 * @return the id of the current {@link Identifiable}.
	 */
	public abstract int getId();
	
	//default method
	/**
	 * @return true if the current {@link Identifiable}
	 * has the given id.
	 */
	public default boolean hasId(final int id) {
		return (getId() == id);
	}
	
	//default method
	/**
	 * @param object
	 * @return true if the current {@link Identifiable}
	 * has the same id as the given object.
	 */
	public default boolean hasId(final Identifiable object) {
		return hasId(object.getId());
	}
}
