//package declaration
package ch.nolix.common.processAPI;

import ch.nolix.common.processProperty.Purpose;

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
	public abstract Purpose getPurpose();
	
	//method declaration
	/**
	 * @return true if the current {@link PurposeRequestable} is functional.
	 */
	public default boolean isFunctional() {
		return (getPurpose() == Purpose.FUNCTIONAL);
	}
	
	//method declaration
	/**
	 * @return true if the current {@link PurposeRequestable} is technical.
	 */
	public default boolean isTechnical() {
		return (getPurpose() == Purpose.TECHNICAL);
	}
}
