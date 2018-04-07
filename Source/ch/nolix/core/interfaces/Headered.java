//package declaration
package ch.nolix.core.interfaces;

//interface
/**
 * A {@link Headered} has a header.
 * 
 * @author Silvan Wyss
 * @month 2018-04
 * @lines 20
 */
public interface Headered {
	
	//abstract method
	/**
	 * @return the header of the current {@link Headered}.
	 */
	public abstract String getHeader();
	
	//default method
	/**
	 * @param header
	 * @return true if the current {@link Headered} has the given header.
	 */
	public default boolean hasHeader(final String header) {
		return getHeader().equals(header);
	}
}
