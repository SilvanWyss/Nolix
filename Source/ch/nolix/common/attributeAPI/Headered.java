//package declaration
package ch.nolix.common.attributeAPI;

//interface
/**
 * A {@link Headered} has a header.
 * 
 * @author Silvan Wyss
 * @month 2018-04
 * @lines 50
 */
public interface Headered {
	
	//method declaration
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
	 * @return true if the current {@link Headered} has the given header.
	 */
	public default boolean hasHeader(final String header) {
		return getHeader().equals(header);
	}
	
	//default method
	/**
	 * @param object
	 * @return true if the current {@link Headered} has the same header as the given object.
	 */
	public default boolean hasSameHeaderAs(final Headered object) {
		
		//Handles the case that the given object is null.
		if (object == null) {
			return false;
		}
		
		//Handles the case that the given object is not null.
		return getHeader().equals(object.getHeader());
	}
}
