//package declaration
package ch.nolix.common.attributeAPI;

//interface
/**
 * A {@link OptionalHeaderable} can have a header that can be set and removed programmatically.
 * 
 * @author Silvan Wyss
 * @month 2019-02
 * @lines 80
 * @param <OH> The type of a {@link OptionalHeaderable}.
 */
public interface OptionalHeaderable<OH extends OptionalHeaderable<OH>> {
	
	//method declaration
	/**
	 * @return the header of the current {@link OptionalHeaderable}.
	 * @throws Exception if the current {@link OptionalHeaderable} does not have a header.
	 */
	public abstract String getHeader();
	
	//method declaration
	/**
	 * @return true if the current {@link OptionalHeaderable} has a header.
	 */
	public abstract boolean hasHeader();
	
	//method
	/**
	 * @param header
	 * @return true if the current {@link OptionalHeaderable} has the given header.
	 */
	public default boolean hasHeader(final String header) {
		
		//Handles the case that current OptionalHeaderable does not have a header.
		if (!hasHeader()) {
			return false;
		}
		
		//Handles the case that current OptionalHeaderable has a header.
		return getHeader().equals(header);
	}
	
	//method
	/**
	 * @param object
	 * @return true if current {@link OptionalHeaderable} has the same header as the given object.
	 */
	public default boolean hasSameHeaderAs(final OptionalHeaderable<?> object) {
		
		//Handles the case that the given object is null.
		if (object == null) {
			return false;
		}
		
		//Handles the case that the given object does not have a header.
		if (!object.hasHeader()) {
			return false;
		}
		
		//Calls other method.
		return hasHeader(object.getHeader());
	}
	
	//method declaration
	/**
	 * Removes the header of current {@link OptionalHeaderable}.
	 * 
	 * @return the current {@link OptionalHeaderable}.
	 */
	public abstract OH removeHeader();
	
	//method declaration
	/**
	 * Sets the header of the current {@link OptionalHeaderable}.
	 * 
	 * @param header
	 * @return the current {@link OptionalHeaderable}.
	 */
	public abstract OH setHeader(String header);
}
