//package declaration
package ch.nolix.core.attributeAPI;

//interface
/**
 * A {@link OptionalHeaderable} is a {@link Headerable} whose header can be removed programmatically.
 * 
 * @author Silvan Wyss
 * @month 2019-02
 * @lines 60
 * @param <OH> The type of a {@link OptionalHeaderable}.
 */
public interface OptionalHeaderable<OH extends OptionalHeaderable<OH>> extends Headerable<OH>{
	
	//abstract method
	/**
	 * @return true if the current {@link OptionalHeaderable} has a header.
	 */
	public abstract boolean hasHeader();
	
	//default method
	/**
	 * @param header
	 * @return true if the current {@link OptionalHeaderable} has the given header.
	 */
	@Override
	public default boolean hasHeader(final String header) {
		
		//Handles the case that current OptionalHeaderable does not have a header.
		if (!hasHeader()) {
			return false;
		}
		
		//Handles the case that current OptionalHeaderable has a header.
		return getHeader().equals(header);
	}
	
	//default method
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
	
	//abstract method
	/**
	 * Removes the header of current {@link OptionalHeaderable}.
	 * 
	 * @return the current {@link OptionalHeaderable}.
	 */
	public abstract OH removeHeader();
}
