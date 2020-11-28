//package declaration
package ch.nolix.common.attributeapi;

//interface
/**
 * A {@link Labeled} has an info string.
 * 
 * @author Silvan Wyss
 * @month 2016-12
 * @lines 20
 */
public interface Labeled {
	
	//method declaration
	/**
	 * @return the info string of the current {@link Labeled}.
	 */
	String getInfoString();
	
	//method
	/**
	 * @param infoString
	 * @return true if the current {@link Labeled} has the given info string.
	 */
	default boolean hasInfoString(final String infoString) {
		return getInfoString().equals(infoString);
	}
}
