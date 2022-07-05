//package declaration
package ch.nolix.coreapi.attributeapi.optionalattributeuniversalapi;

//interface
/**
 * A {@link OptionalIdentifiedByString} can have an id that is a {@link String}.
 * 
 * @author Silvan Wyss
 * @date 2020-01-05
 */
public interface OptionalIdentifiedByString {
	
	//method declaration
	/**
	 * @return the id of the current {@link OptionalIdentifiedByString}.
	 */
	String getId();
	
	//method declaration
	/**
	 * @return the id of the current {@link OptionalIdentifiedByString} in quotes.
	 */
	String getIdInQuotes();
	
	//method declaration
	/**
	 * @return true if the current {@link OptionalIdentifiedByString} has an id.
	 */
	boolean hasId();
	
	//method declaration
	/**
	 * @param id
	 * @return true if the current {@link OptionalIdentifiedByString} has the given id.
	 */
	boolean hasId(String id);
}
