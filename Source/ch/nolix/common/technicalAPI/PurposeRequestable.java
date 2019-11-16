//package declaration
package ch.nolix.common.technicalAPI;

import ch.nolix.common.processProperties.Purpose;

//interface
/**
 * A {@link PurposeRequestable} can requested for its purpose.
 * 
 * @author Silvan Wyss
 * @month 2019-09
 * @lines 30
 */
public interface PurposeRequestable {
	
	//abstract method
	/**
	 * @return the purpose of the current {@link PurposeRequestable}.
	 */
	public abstract Purpose getPurpose();
	
	//abstract method
	/**
	 * @return true if the current {@link PurposeRequestable} is functional.
	 */
	public default boolean isFunctional() {
		return (getPurpose() == Purpose.FUNCTIONAL);
	}
	
	//abstract method
	/**
	 * @return true if the current {@link PurposeRequestable} is technical.
	 */
	public default boolean isTechnical() {
		return (getPurpose() == Purpose.TECHNICAL);
	}
}
