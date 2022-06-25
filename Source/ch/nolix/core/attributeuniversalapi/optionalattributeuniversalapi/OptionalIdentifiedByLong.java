//package declaration
package ch.nolix.core.attributeuniversalapi.optionalattributeuniversalapi;

//interface
/**
 * A {@link OptionalIdentifiedByLong} can have an id.
 * 
 * @author Silvan Wyss
 * @date 2019-09-29
 */
public interface OptionalIdentifiedByLong {
	
	//method declaration
	/**
	 * @return the id of the current {@link OptionalIdentifiedByLong}.
	 */
	long getId();
	
	//method declaration
	/**
	 * @return the id of the current {@link OptionalIdentifiedByLong} as {@link String}.
	 */
	String getIdAsString();
	
	//method
	/**
	 * @return the id of the current {@link OptionalIdentifiedByLong} in quotes.
	 */
	String getIdAsStringInQuotes();
	
	//method declaration
	/**
	 * @return true if the current {@link OptionalIdentifiedByLong} has an id.
	 */
	boolean hasId();
	
	//method declaration
	/**
	 * @param id
	 * @return true if the current {@link OptionalIdentifiedByLong} has the given id.
	 */
	boolean hasId(final long id);
}
