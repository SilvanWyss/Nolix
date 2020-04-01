//package declaration
package ch.nolix.common.optionalAttributeAPI;

//interface
/**
 * A {@link OptionalNamable} is a {@link OptionalNamed} whose name can be set and removed programmatically.
 * 
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 30
 * @param <ON> The type of a {@link OptionalNamable}.
 */
public interface OptionalNamable<ON extends OptionalNamable<ON>> extends OptionalNamed {
	
	//method declaration
	/**
	 * Removes the name of the current {@link OptionalNamable}.
	 * 
	 * @return the current {@link OptionalNamable}.
	 */
	public abstract ON removeName();
	
	//method declaration
	/**
	 * Sets the name of the current {@link OptionalNamable}.
	 * 
	 * @param name
	 * @return the current {@link OptionalNamable}.
	 */
	public abstract ON setName(String name);
}
