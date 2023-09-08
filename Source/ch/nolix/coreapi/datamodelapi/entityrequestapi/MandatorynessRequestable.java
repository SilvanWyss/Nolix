//package declaration
package ch.nolix.coreapi.datamodelapi.entityrequestapi;

//own imports
import ch.nolix.coreapi.programstructureapi.markerapi.AllowDefaultMethodsAsDesignPattern;

//interface
/**
 * A {@link MandatorynessRequestable} can be asked if it is mandatory.
 * 
 * @author Silvan Wyss
 * @date 2021-12-26
 */
@AllowDefaultMethodsAsDesignPattern
public interface MandatorynessRequestable {
	
	//method declaration
	/**
	 * @return true if the current {@link MandatorynessRequestable} is mandatory, false otherweise.
	 */
	boolean isMandatory();
	
	//method
	/**
	 * @return true if the current {@link MandatorynessRequestable} is optional, false otherweise.
	 */
	default boolean isOptional() {
		return !isMandatory();
	}
}
