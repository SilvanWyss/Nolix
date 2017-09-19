//package declaration
package ch.nolix.core.interfaces;

//interface
/**
 * An optional signable object is a signable object
 * whose info string can be removed programmatically.
 * 
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 30
 * @param <OS> The type of an optional signable object.
 */
public interface OptionalSignable<OS extends OptionalSignable<OS>>
extends Signable<OS> {
	
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
	public default boolean hasInfo(final String infoString) {
		
		//Handles the case if this optional signable object has no info string.
		if (!hasInfoString()) {
			return false;
		}
		
		//Handles the case if this optional signable object has an info string.
		return getInfoString().equals(infoString);
	}
}
