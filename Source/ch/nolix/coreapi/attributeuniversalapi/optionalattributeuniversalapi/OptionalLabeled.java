//package declaration
package ch.nolix.coreapi.attributeuniversalapi.optionalattributeuniversalapi;

//interface
/**
 * A {@link OptionalLabeled} can have an info string.
 * 
 * @author Silvan Wyss
 * @date 2020-03-29
 */
public interface OptionalLabeled {
	
	//method declaration
	/**
	 * @return the info string of the current {@link OptionalLabeled}.
	 */
	String getInfoString();
	
	//method declaration
	/**
	 * @return the info string of the current {@link OptionalLabeled} in quotes.
	 */
	String getInfoStringInQuotes();
	
	//method declaration
	/**
	 * @return true if the current {@link OptionalLabeled} has an info string.
	 */
	boolean hasInfoString();
}
