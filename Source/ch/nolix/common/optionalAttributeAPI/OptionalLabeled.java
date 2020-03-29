//package declaration
package ch.nolix.common.optionalAttributeAPI;

//interface
/**
 * A {@link OptionalLabeled} can have an info string.
 * 
 * @author Silvan Wyss
 * @month 2020-03
 * @lines 50
 */
public interface OptionalLabeled {
	
	//method declaration
	/**
	 * @return the info string of the current {@link OptionalLabeled}.
	 * @throws Exception if the current {@link OptionalLabeled} does not have an info string.
	 */
	public abstract String getInfoString();
	
	//method
	/**
	 * @return the info string of the current {@link OptionalLabeled} in quotes.
	 * @throws Exception if the current {@link OptionalLabeled} does not have an info string.
	 */
	public default String getInfoStringInQuotes() {
		return ("'" + getInfoString() + "'");
	}
	
	//method declaration
	/**
	 * @return true if the current {@link OptionalLabeled} has an info string.
	 */
	public abstract boolean hasInfoString();
	
	//method
	/**
	 * @param infoString
	 * @return true if the current {@link OptionalLabeled} has the given info string.
	 */
	public default boolean hasInfoString(final String infoString) {
		
		//Handles the case that the current OptionalLabeled does not have an info string.
		if (!hasInfoString()) {
			return false;
		}
		
		//Handles the case that the current OptionalLabeled has an info string.
		return getInfoString().equals(infoString);
	}
}
