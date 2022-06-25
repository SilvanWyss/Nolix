//package declaration
package ch.nolix.core.attributeuniversalapi.optionalattributeuniversalapi;

//interface
/**
 * A {@link OptionalIdentified} can have an id.
 * 
 * @author Silvan Wyss
 * @date 2019-09-29
 */
public interface OptionalIdentified {
	
	//method declaration
	/**
	 * @return the id of the current {@link OptionalIdentified}.
	 */
	long getId();
	
	//method
	/**
	 * @return the id of the current {@link OptionalIdentified} as {@link String}.
	 */
	String getIdAsString();
	
	//method
	/**
	 * @return the id of the current {@link OptionalIdentified} in quotes.
	 */
	String getIdAsStringInQuotes();
	
	//method declaration
	/**
	 * @return true if the current {@link OptionalIdentified} has an id.
	 */
	boolean hasId();
	
	//method
	/**
	 * @param id
	 * @return true if the current {@link OptionalIdentified} has the given id.
	 */
	boolean hasId(final long id);
}
