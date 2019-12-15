//package declaration
package ch.nolix.common.attributeAPI;

//interface
/**
 * A {@link OptionalLabelable} can have an info string that can be set and removed programmatically.
 * 
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 60
 * @param <OL> The type of an {@link OptionalLabelable}.
 */
public interface OptionalLabelable<OL extends OptionalLabelable<OL>> {
	
	//method declaration
	/**
	 * @return the info string of the current {@link OptionalLabelable}.
	 * @throws Exception if the current {@link OptionalLabelable} does not have an info string.
	 */
	public abstract String getInfoString();
	
	//method declaration
	/**
	 * @return true if the current {@link OptionalLabelable} has an info string.
	 */
	public abstract boolean hasInfoString();
	
	//method
	/**
	 * @param infoString
	 * @return true if the current {@link OptionalLabelable} object has the given info string.
	 */
	public default boolean hasInfoString(final String infoString) {
		
		//Handles the case that the current OptionalLabelable does not have an info string.
		if (!hasInfoString()) {
			return false;
		}
		
		//Handles the case that the current OptionalLabelable has an info string.
		return getInfoString().equals(infoString);
	}
	
	//method declaration
	/**
	 * Removes the info string of the current {@link OptionalLabelable}.
	 * 
	 * @return the current {@link OptionalLabelable}.
	 */
	public abstract OL removeInfoString();
	
	//method declaration
	/**
	 * Sets the info string of the current {@link OptionalLabelable}.
	 * 
	 * @param infoString
	 * @return the current {@link OptionalLabelable}.
	 */
	public abstract OL setInfoString(String infoString);
}
