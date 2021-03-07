//package declaration
package ch.nolix.common.processapi;

import ch.nolix.common.programcontrol.processproperty.Purpose;

//interface
/**
 * A {@link PurposeRequestable} can requested for its purpose.
 * 
 * @author Silvan Wyss
 * @month 2019-09
 * @lines 30
 */
public interface PurposeRequestable {
	
	//method declaration
	/**
	 * @return the purpose of the current {@link PurposeRequestable}.
	 */
	Purpose getPurpose();
	
	//method declaration
	/**
	 * @return true if the current {@link PurposeRequestable} is functional.
	 */
	default boolean isFunctional() {
		return (getPurpose() == Purpose.FUNCTIONAL);
	}
	
	//method declaration
	/**
	 * @return true if the current {@link PurposeRequestable} is technical.
	 */
	default boolean isTechnical() {
		return (getPurpose() == Purpose.TECHNICAL);
	}
}
