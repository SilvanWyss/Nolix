//package declaration
package ch.nolix.common.attributeAPI;

//interface
/**
 * A {@link OptionalIdentifiableByString} can have an id that:
 * -Is a {@link String} 
 * -Can be set and removed programmatically.
 * 
 * @author Silvan Wyss
 * @month 2020-01
 * @lines 90
 * @param <OIBS> The type of a {@link OptionalIdentifiableByString}.
 */
public interface OptionalIdentifiableByString<OIBS extends OptionalIdentifiableByString<OIBS>> {
	
	//method declaration
	/**
	 * @return the id of the current {@link OptionalIdentifiableByString}.
	 * @throws Exception if the current {@link OptionalIdentifiableByString} does not have a id.
	 */
	public abstract String getId();
	
	//method
	/**
	 * @return the id of the current {@link OptionalIdentifiableByString} in quotes.
	 * @throws Exception if the current {@link OptionalIdentifiableByString} does not have a id.
	 */
	public default String getIdInQuotes() {
		return ("'" + getId() + "'");
	}
	//method declaration
	/**
	 * @return true if the current {@link OptionalIdentifiableByString} has a id.
	 */
	public abstract boolean hasId();
	
	//method
	/**
	 * @param id
	 * @return true if the current {@link OptionalIdentifiableByString} has the given id.
	 */
	public default boolean hasId(final String id) {
		
		//Handles the case that the current OptionalIdentifiableByString does not have a id.
		if (!hasId()) {
			return false;
		}
		
		//Handles the case that the current OptionalIdentifiableByString has a id.
		return getId().equals(id);
	}
	
	//method
	/**
	 * @param object
	 * @return true if the current {@link OptionalIdentifiableByString} has the same id as the given object.
	 */
	public default boolean hasSameIdAs(final OptionalIdentifiableByString<?> object) {
		
		//Handles the case that the given object is null.
		if (object == null) {
			return false;
		}
		
		//Handles the case that the given object does not have a id.
		if (!object.hasId()) {
			return false;
		}
		
		//Calls other method.
		return hasId(object.getId());
	}
	
	//method declaration
	/**
	 * Removes the id of the current {@link OptionalIdentifiableByString}.
	 * 
	 * @return the current {@link OptionalIdentifiableByString}.
	 */
	public abstract OIBS removeId();
	
	//method declaration
	/**
	 * Sets the id of the current {@link OptionalIdentifiableByString}.
	 * 
	 * @param id
	 * @return the current {@link OptionalIdentifiableByString}.
	 */
	public abstract OIBS setId(String id);
}
