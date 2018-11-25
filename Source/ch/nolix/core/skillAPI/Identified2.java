//package declaration
package ch.nolix.core.skillAPI;

//interface
/**
 * A {@link Identified2} has an id.
 * 
 * @author Silvan Wyss
 * @month 2018-11
 * @lines 30
 */
public interface Identified2 {
	
	//abstract method
	/**
	 * @return the id of the current {@link Identified2}.
	 */
	public abstract long getId();
	
	//default method
	/**
	 * @param object
	 * @return true if the current {@link Identified}
	 * has the same id as the given object.
	 */
	public default boolean hasId(final Identified2 object) {
		return hasId(object.getId());
	}
	
	//default method
	/**
	 * @return true if the current {@link Identified2}
	 * has the given id.
	 */
	public default boolean hasId(final long id) {
		return (getId() == id);
	}
}
