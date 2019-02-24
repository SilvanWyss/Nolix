//package declaration
package ch.nolix.core.skillAPI;

//interface
/**
 * A {@link OptionalLabelable} is a {@link Labelable}
 * whose info string can be removed programmatically.
 * 
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 40
 * @param <OL> The type of an {@link OptionalLabelable}.
 */
public interface OptionalLabelable<OL extends OptionalLabelable<OL>>
extends Labelable<OL> {
	
	//abstract method
	/**
	 * @return true if the current {@link OptionalLabelable} has an info string.
	 */
	public abstract boolean hasInfoString();
	
	//default method
	/**
	 * @param infoString
	 * @return true if the current {@link OptionalLabelable} object has the given info string.
	 */
	@Override
	public default boolean hasInfoString(final String infoString) {
		
		//Handles the case that the current OptionalLabelable does not have an info string.
		if (!hasInfoString()) {
			return false;
		}
		
		//Handles the case that the current OptionalLabelable has an info string.
		return getInfoString().equals(infoString);
	}
	
	//abstract method
	/**
	 * Removes the info string of the current {@link OptionalLabelable}.
	 * 
	 * @return the current {@link OptionalLabelable}.
	 */
	public abstract OL removeInfoString();
}
