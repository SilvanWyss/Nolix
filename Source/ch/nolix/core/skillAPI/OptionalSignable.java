//package declaration
package ch.nolix.core.skillAPI;

//interface
/**
 * An optional signable object is a signable object
 * whose info string can be removed programmatically.
 * 
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 40
 * @param <OS> The type of an optional signable object.
 */
public interface OptionalSignable<OS extends OptionalSignable<OS>>
extends Labelable<OS> {
	
	//abstract method
	/**
	 * @return true if this optional signable object has an info string.
	 */
	public abstract boolean hasInfoString();
	
	//default method
	/**
	 * @param infoString
	 * @return true if this optional signable object has the given info string.
	 */
	@Override
	public default boolean hasInfoString(final String infoString) {
		
		//Handles the case that this optional signable object does not have an info string.
		if (!hasInfoString()) {
			return false;
		}
		
		//Handles the case that this optional signable object has an info string.
		return getInfoString().equals(infoString);
	}
	
	//abstract method
	/**
	 * Removes the info string of this optional signable object.
	 * 
	 * @return this optional signable object.
	 */
	public abstract OS removeInfoString();
}
