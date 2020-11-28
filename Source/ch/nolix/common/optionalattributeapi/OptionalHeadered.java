//package declaration
package ch.nolix.common.optionalattributeapi;

import ch.nolix.common.constant.StringCatalogue;

//interface
/**
 * A {@link OptionalHeadered} can have a header.
 * 
 * @author Silvan Wyss
 * @month 2020-03
 * @lines 90
 */
public interface OptionalHeadered {
	
	//method declaration
	/**
	 * @return the header of the current {@link OptionalHeadered}.
	 * @throws Exception if the current {@link OptionalHeadered} does not have a header.
	 */
	String getHeader();
	
	//method
	/**
	 * @return the header of the current {@link OptionalHeadered}.
	 * @throws Exception if the current {@link OptionalHeadered} does not have a header.
	 */
	default String getHeaderInQuotes() {
		return ("'" + getHeader() + "'");
	}
	
	//method
	/**
	 * @return the header of the current {@link OptionalHeadered} if it has a header,
	 * otherwise an empty {@link String}.
	 */
	default String getHeaderOrEmptyString() {
		
		//Handles the case that current OptionalHeadered does not have a header.
		if (!hasHeader()) {
			return StringCatalogue.EMPTY_STRING;
		}
		
		//Handles the case that current OptionalHeadered has a header.
		return getHeader();
	}
	
	//method declaration
	/**
	 * @return true if the current {@link OptionalHeadered} has a header.
	 */
	boolean hasHeader();
	
	//method
	/**
	 * @param header
	 * @return true if the current {@link OptionalHeadered} has the given header.
	 */
	default boolean hasHeader(final String header) {
		
		//Handles the case that current OptionalHeadered does not have a header.
		if (!hasHeader()) {
			return false;
		}
		
		//Handles the case that current OptionalHeadered has a header.
		return getHeader().equals(header);
	}
	
	//method
	/**
	 * @param object
	 * @return true if current {@link OptionalHeadered} has the same header as the given object.
	 */
	default boolean hasSameHeaderAs(final OptionalHeadered object) {
		
		//Handles the case that the given object is null.
		if (object == null) {
			return false;
		}
		
		//Handles the case that the given object does not have a header.
		if (!object.hasHeader()) {
			return false;
		}
		
		//Handles the case that the given object has a header.
		return hasHeader(object.getHeader());
	}
}
