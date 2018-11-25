//package declaration
package ch.nolix.core.skillAPI;

//interface
/**
 * A signed object has an info string.
 * 
 * @author Silvan Wyss
 * @month 2016-12
 * @lines 20
 */
public interface Labeled {

	//abstract method
	/**
	 * @return the info string of this signed object.
	 */
	public abstract String getInfoString();
	
	//default method
	/**
	 * @param infoString
	 * @return true if this signed object has the given info string.
	 */
	public default boolean hasInfoString(final String infoString) {
		return getInfoString().equals(infoString);
	}
}
