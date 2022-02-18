//package declaration
package ch.nolix.core.attributeapi.mandatoryattributeapi;

//interface
/**
 * A {@link Versioned} has a single version number.
 * 
 * @author Silvan Wyss
 * @date 2016-01-01
 */
public interface Versioned {
	
	//method declaration
	/**
	 * @return the version number of the current {@link Versioned}.
	 */
	int getVersion();
}
