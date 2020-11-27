//package declaration
package ch.nolix.common.mutableOptionalAttributeAPI;

//own import
import ch.nolix.common.optionalAttributeAPI.OptionalLabeled;

//interface
/**
 * A {@link OptionalLabelable} is a {@link } whose info string can be set and removed programmatically.
 * 
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 30
 * @param <OL> The type of a {@link OptionalLabelable}.
 */
public interface OptionalLabelable<OL extends OptionalLabelable<OL>> extends OptionalLabeled {
	
	//method declaration
	/**
	 * Removes the info string of the current {@link OptionalLabelable}.
	 * 
	 * @return the current {@link OptionalLabelable}.
	 */
	OL removeInfoString();
	
	//method declaration
	/**
	 * Sets the info string of the current {@link OptionalLabelable}.
	 * 
	 * @param infoString
	 * @return the current {@link OptionalLabelable}.
	 */
	OL setInfoString(String infoString);
}
