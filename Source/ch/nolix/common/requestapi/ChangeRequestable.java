//package declaration
package ch.nolix.common.requestapi;

//interface
/**
 * A {@link ChangeRequestable} can be asked if it has uncommitted changes.
 * 
 * @author Silvan Wyss
 * @date 2021-02-26
 * @lines 20
 */
public interface ChangeRequestable {
	
	//method declaration
	/**
	 * @return true if the current {@link ChangeRequestable} has uncomitted changes.
	 */
	boolean hasChanges();
	
	//method
	/**
	 * @return true if the current {@link ChangeRequestable} does not have uncommitted changes. 
	 */
	default boolean isChangeFree() {
		return !hasChanges();
	}
}
