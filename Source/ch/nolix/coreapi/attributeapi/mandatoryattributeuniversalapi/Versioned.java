//package declaration
package ch.nolix.coreapi.attributeapi.mandatoryattributeuniversalapi;

import ch.nolix.coreapi.programstructureapi.markerapi.AllowDefaultMethodsAsDesignPattern;

//interface
/**
 * A {@link Versioned} has a single version number.
 * 
 * @author Silvan Wyss
 * @date 2016-01-01
 */
@AllowDefaultMethodsAsDesignPattern
public interface Versioned {
	
	//method declaration
	/**
	 * @return the version number of the current {@link Versioned}.
	 */
	int getVersion();
}
