//package declaration
package ch.nolix.common.interfaces;

//interface
/**
 * A code named object has a code name.
 * 
 * @author Silvan Wyss
 * @month 2016-04
 * @lines 20
 */
public interface CodeNamed {

	//abstract method
	/**
	 * @return the code name of this code named object.
	 */
	public abstract String getCodeName();
	
	//default method
	/**
	 * @param codeName
	 * @return true if this code named object has the given code name.
	 */
	public default boolean hasCodeName(final String codeName) {
		return getCodeName().equals(codeName);
	}
}
