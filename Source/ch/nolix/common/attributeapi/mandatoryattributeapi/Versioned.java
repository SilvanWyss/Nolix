//package declaration
package ch.nolix.common.attributeapi.mandatoryattributeapi;

//interface
/**
 * A {@link Versioned} has a single version number.
 * 
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 10
 */
public interface Versioned {
	
	//method declaration
	/**
	 * @return the version number of the current {@link Versioned}.
	 */
	int getVersion();
}
