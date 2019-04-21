//package declaration
package ch.nolix.core.attributeAPI;

//interface
/**
 * A {@link Headered} has a header.
 * 
 * @author Silvan Wyss
 * @month 2018-04
 * @lines 40
 */
public interface Headered {
	
	//abstract method
	/**
	 * @return the header of the current {@link Headered}.
	 */
	public abstract String getHeader();
	
	//default method
	/**
	 * @return the header of the current {@link Headered} in quotes.
	 */
	public default String getHeaderInQuotes() {
		return ("'" + getHeader() + "'");
	}
	
	//default method
	/**
	 * @param header
	 * @return true if the current {@link Headered}
	 * has the given header.
	 */
	public default boolean hasHeader(final String header) {
		return getHeader().equals(header);
	}
	
	//default method
	/**
	 * @param object
	 * @return true if the current {@link Headered}
	 * has the same header as the given object.
	 */
	public default boolean hasSameHeaderAs(final Headered object) {
		return getHeader().equals(object.getHeader());
	}
}
