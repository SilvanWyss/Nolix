//package declaration
package ch.nolix.common.attributeAPI;

//interface
/**
 * A {@link Labeled} has an info string.
 * 
 * @author Silvan Wyss
 * @month 2016-12
 * @lines 20
 */
public interface Labeled {
	
	//abstract method
	/**
	 * @return the info string of the current {@link Labeled}.
	 */
	public abstract String getInfoString();
	
	//default method
	/**
	 * @param infoString
	 * @return true if the current {@link Labeled} has the given info string.
	 */
	public default boolean hasInfoString(final String infoString) {
		return getInfoString().equals(infoString);
	}
}
