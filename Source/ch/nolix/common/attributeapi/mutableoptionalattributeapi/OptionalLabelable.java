//package declaration
package ch.nolix.common.attributeapi.mutableoptionalattributeapi;

import ch.nolix.common.attributeapi.optionalattributeapi.OptionalLabeled;

//interface
/**
 * A {@link OptionalLabelable} is a {@link OptionalLabeled} whose info string can be set and removed programmatically.
 * 
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 30
 * @param <OL> is the type of a {@link OptionalLabelable}.
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
