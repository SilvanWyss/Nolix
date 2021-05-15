//package declaration
package ch.nolix.common.requestapi;

//interface
/**
 * A {@link ExpansionRequestable} can be asked if it is expanded or collapsed.
 * 
 * @author Silvan Wyss
 * @date 2020-10-02
 * @lines 20
 */
public interface ExpansionRequestable {
	
	//method
	/**
	 * @return true if the current {@link ExpansionRequestable} is collapsed.
	 */
	default boolean isCollapsed() {
		return !isExpanded();
	}
	
	//method declaration
	/**
	 * @return true if the current {@link ExpansionRequestable} is expanded.
	 */
	boolean isExpanded();
}
